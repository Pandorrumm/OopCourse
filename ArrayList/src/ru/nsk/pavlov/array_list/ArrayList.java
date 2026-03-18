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

        //noinspection unchecked
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size, Object[].class);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Destination array cannot be null.");
        }

        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        for (int i = 0; i < size; i++) {
            //noinspection unchecked
            a[i] = (T) items[i];
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        ensureCapacity(size + 1);

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;

        size++;
        modCount++;
    }

    @Override
    public boolean add(E element) {
        if (size >= items.length) {
            increaseCapacity(items.length * 2);
        }

        items[size] = element;

        size++;
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
        checkCollection(c);

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkCollection(c);

        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkCollection(c);

        checkIndex(index);

        if (c.isEmpty()) {
            return false;
        }

        int insertIndex = index;

        for (E element : c) {
            add(insertIndex, element);
            insertIndex++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCollection(c);

        boolean isDone = false;

        while (iterator().hasNext()) {
            if (c.contains(iterator().next())) {
                iterator().remove();

                isDone = true;
            }
        }

        return isDone;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCollection(c);

        boolean isDone = false;

        while (iterator().hasNext()) {
            if (!c.contains(iterator().next())) {
                iterator().remove();

                isDone = true;
            }
        }

        return isDone;
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
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
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

    private void checkCollection(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
    }

    private void increaseCapacity(int newCapacity) {
        items = Arrays.copyOf(items, newCapacity);
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

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.append(items[size - 1]).append("]");

        return stringBuilder.toString();
    }
}