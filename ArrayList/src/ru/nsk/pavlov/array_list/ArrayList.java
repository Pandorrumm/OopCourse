package ru.nsk.pavlov.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a, "Destination array cannot be null.");

        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + size);
        }

        if (size == items.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;

        size++;
        modCount++;
    }

    @Override
    public boolean add(E element) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = element;

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        requireNonNull(c);

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        requireNonNull(c);

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + size);
        }

        if (c.isEmpty()) {
            return false;
        }

        int newElementCount = c.toArray().length;

        if (size + newElementCount > items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + newElementCount, size - index);

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, items, index, newElementCount);

        size += newElementCount;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        requireNonNull(c);

        boolean isModified = false;
        int writeIndex = 0;

        for (int i = 0; i < size; i++) {
            E element = items[i];

            if (!c.contains(element)) {
                items[writeIndex] = element;
                writeIndex++;
            } else {
                isModified = true;
            }
        }

        if (isModified) {
            for (int i = writeIndex; i < size; i++) {
                items[i] = null;
            }

            size = writeIndex;
            modCount++;
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        requireNonNull(c);

        boolean isModified = false;
        int writeIndex = 0;

        for (int i = 0; i < size; i++) {
            E element = items[i];

            if (c.contains(element)) {
                items[writeIndex] = element;
                writeIndex++;
            } else {
                isModified = true;
            }
        }

        if (isModified) {
            for (int i = writeIndex; i < size; i++) {
                items[i] = null;
            }

            size = writeIndex;
            modCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size, null);

        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = items[index];
        items[index] = element;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removeElement = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;

        size--;
        modCount++;

        return removeElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public java.util.ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public java.util.ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (size - 1));
        }
    }

    private void requireNonNull(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private class ListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the collection");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Collection was modified during iteration");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            increaseCapacity();
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        int lastIndex = size - 1;

        for (int i = 0; i < lastIndex; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.append(items[lastIndex]).append(']');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        ArrayList<E> arrayList = (ArrayList<E>) obj;

        if (size != arrayList.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], arrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (int i = 0; i < size; i++) {
            hash = prime * hash + Objects.hashCode(items[i]);
        }

        return hash;
    }
}