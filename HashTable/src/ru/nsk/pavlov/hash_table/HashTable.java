package ru.nsk.pavlov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private LinkedList<E>[] buckets;
    private int size;
    private int modCount;
    private int threshold;

    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Got: " + initialCapacity);
        }

        //noinspection unchecked
        buckets = (LinkedList<E>[]) new LinkedList[initialCapacity];

        threshold = (int) (initialCapacity * DEFAULT_LOAD_FACTOR);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        LinkedList<E> bucket = buckets[index];

        return bucket != null && bucket.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        }

        Object[] result = new Object[size];

        int i = 0;

        for (LinkedList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                for (E element : bucket) {
                    result[i] = element;
                    i++;
                }
            }
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a, "Destination array cannot be null.");

        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        int i = 0;

        for (LinkedList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                for (E element : bucket) {
                    //noinspection unchecked
                    a[i] = (T) element;
                    i++;
                }
            }
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        LinkedList<E> bucket = buckets[index];

        if (bucket == null) {
            bucket = new LinkedList<>();
            buckets[index] = bucket;
        } else if (bucket.contains(e)) {
            return false;
        }

        bucket.add(e);

        size++;
        modCount++;

        if (size >= threshold) {
            increaseCapacity();
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        LinkedList<E> bucket = buckets[index];

        if (bucket != null && bucket.remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c, "Collection cannot be null");

        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c, "Collection cannot be null");

        boolean isModified = false;

        for (E e : c) {
            if (add(e)) {
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c, "Collection cannot be null");

        boolean isModified = false;

        for (Object obj : c) {
            if (remove(obj)) {
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c, "Collection cannot be null");

        boolean isModified = false;

        for (LinkedList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                int originalSize = bucket.size();

                if (bucket.retainAll(c)) {
                    size -= originalSize - bucket.size(); // обновляем общий размер
                    isModified = true;
                }
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (LinkedList<E> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }

        size = 0;
        modCount++;
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode()) % buckets.length;
    }

    private void increaseCapacity() {
        int newCapacity = buckets.length * 2;
        LinkedList<E>[] oldBuckets = buckets;

        //noinspection unchecked
        buckets = (LinkedList<E>[]) new LinkedList[newCapacity];
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
        size = 0;

        for (LinkedList<E> bucket : oldBuckets) {
            if (bucket != null) {
                addAll(bucket);
            }
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        boolean isFirstBucket = true;

        for (LinkedList<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                if (!isFirstBucket) {
                    stringBuilder.append(", ");
                }

                stringBuilder.append('[');

                boolean isFirstElement = true;

                for (E element : bucket) {
                    if (!isFirstElement) {
                        stringBuilder.append(", ");
                    }

                    stringBuilder.append(element);

                    isFirstElement = false;
                }

                stringBuilder.append(']');

                isFirstBucket = false;
            }
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private class HashTableIterator implements Iterator<E> {
        private int bucketIndex;
        private Iterator<E> currentIterator;
        private int remainingElementsCount = size;
        private final int expectedModCount = modCount;

        public HashTableIterator() {
            findNextBucket();
        }

        private void findNextBucket() {
            while (bucketIndex < buckets.length) {
                if (buckets[bucketIndex] != null && !buckets[bucketIndex].isEmpty()) {
                    currentIterator = buckets[bucketIndex].iterator();
                    return;
                }

                bucketIndex++;
            }

            currentIterator = null;
        }

        @Override
        public boolean hasNext() {
            return remainingElementsCount > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements.");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Collection was modified during iteration");
            }

            if (currentIterator == null || !currentIterator.hasNext()) {
                bucketIndex++;

                findNextBucket();

                if (currentIterator == null) {
                    throw new NoSuchElementException("No more elements.");
                }
            }

            remainingElementsCount--;

            return currentIterator.next();
        }
    }
}
