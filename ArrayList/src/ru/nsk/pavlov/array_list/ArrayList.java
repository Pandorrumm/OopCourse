package ru.nsk.pavlov.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + size);
        }

        items = (E[]) new Object[initialCapacity];

        size = 0;
        modCount = 0;
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
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E element) {
        if (size == items.length) {
            increaseCapacity(items.length * 2);
        }

        items[size + 1] = element;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                remove(i);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

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
    public void add(int index, E element) {
        checkIndex(index);

        if (size >= items.length) {
            increaseCapacity(items.length * 2);
        }

        items[size] = element;

        size++;
        modCount++;
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
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (items.length - 1));
        }
    }

    private void increaseCapacity(int newCapacity) {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private class MyListIterator implements Iterator<E> {
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
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + minCapacity);
        }

        if (minCapacity <= items.length) {
            return;
        }

        modCount++;

        int newCapacity = Math.max(minCapacity, items.length * 2);
        increaseCapacity(newCapacity);
    }

    public void trimToSize() {
        if (size < items.length) {
            modCount++;

            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            sb.append(items[i]).append(", ");
        }

        sb.append(items[size - 1]).append("]");

        return sb.toString();
    }
}
