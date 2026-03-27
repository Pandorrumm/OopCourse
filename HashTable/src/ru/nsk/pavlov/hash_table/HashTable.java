package ru.nsk.pavlov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private LinkedList<E>[] table;
    private int size;
    private int modCount;

    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int threshold;

    public HashTable(int initialCapacity) {
        //noinspection unchecked
        table = (LinkedList<E>[]) new LinkedList[initialCapacity];

        for (int i = 0; i < initialCapacity; i++) {
            table[i] = new LinkedList<>();
        }

        size = 0;

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

        LinkedList<E> bucket = table[index];

        return bucket != null && bucket.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] tempArray = Arrays.stream(table)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toArray();

        return Arrays.copyOf(tempArray, tempArray.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a, "Destination array cannot be null.");

        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        int index = 0;

        for (LinkedList<E> bucket : table) {
            if (bucket != null && !bucket.isEmpty()) {
                Object[] bucketArray = bucket.toArray();

                int bucketSize = bucketArray.length;

                //noinspection SuspiciousSystemArraycopy
                System.arraycopy(bucketArray, 0, a, index, bucketSize);
                index += bucketSize;
            }
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (size >= threshold) {
            increaseCapacity();
        }

        int index = getIndex(e);

        LinkedList<E> bucket = getBucket(index);

        if (bucket.contains(e)) {
            return false;
        }

        bucket.add(e);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        LinkedList<E> bucket = table[index];

        if (bucket != null && bucket.remove(o)) {
            size--;
            modCount++;

            if (bucket.isEmpty()) {
                table[index] = null;
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        requireNonNull(c);

        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        requireNonNull(c);

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
        requireNonNull(c);

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
        requireNonNull(c);

        boolean isModified = false;

        HashTable<E> saveTable = new HashTable<>(table.length);

        for (Object obj : c) {
            if (contains(obj)) {
                @SuppressWarnings("unchecked") E element = (E) obj;
                saveTable.add(element);
            }
        }

        if (saveTable.size() != size) {
            clear();
            addAll(saveTable);

            isModified = true;
        }

        return isModified;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);

        size = 0;
    }

    private int getIndex(Object key) {
        if (key == null) {
            return 0;
        }

        return Math.abs(key.hashCode()) % table.length;
    }

    private void requireNonNull(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
    }

    private LinkedList<E> getBucket(int index) {
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        return table[index];
    }

    private void increaseCapacity() {
        int newCapacity = table.length * 2;
        LinkedList<E>[] oldTable = table;

        //noinspection unchecked
        table = (LinkedList<E>[]) new LinkedList[newCapacity];
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
        size = 0;

        for (LinkedList<E> bucket : oldTable) {
            if (bucket != null) {
                this.addAll(bucket);
            }
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (LinkedList<E> bucket : table) {
            stringBuilder.append(" [");

            if (bucket != null && !bucket.isEmpty()) {
                boolean first = true;

                for (E element : bucket) {
                    if (!first) {
                        stringBuilder.append(", ");
                    }

                    stringBuilder.append(element);

                    first = false;
                }
            }

            stringBuilder.append("] ");
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    private class HashTableIterator implements Iterator<E> {
        private int bucketIndex = 0;
        private Iterator<E> currentIterator = null;
        private int remainingElementsCount = size;
        private final int expectedModCount = modCount;

        public HashTableIterator() {
            findNextBucket();
        }

        private void findNextBucket() {
            while (bucketIndex < table.length && remainingElementsCount > 0) {
                if (table[bucketIndex] != null && !table[bucketIndex].isEmpty()) {
                    currentIterator = table[bucketIndex].iterator();
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

            E element = currentIterator.next();

            remainingElementsCount--;

            return element;
        }

        @Override
        public void remove() {
            if (currentIterator == null) {
                throw new IllegalStateException("No element to remove");
            }

            currentIterator.remove();

            size--;
            remainingElementsCount--;
        }
    }
}
