package ru.nsk.pavlov.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public int getCount() {
        return count;
    }

    public ListItem<E> getHead() {
        return head;
    }

    public void add(E item) {
        head = new ListItem<>(item, head);
        count++;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }

        return head.getData();
    }

    public E getByIndex(int index) {
        checkIndexCorrectness(index);

        return getListItemByIndex(index).getData();
    }

    public void setByIndex(int index, E newData) {
        checkIndexCorrectness(index);

        getListItemByIndex(index).setData(newData);
    }

    public E deleteByIndex(int index) {
        checkIndexCorrectness(index);

        ListItem<E> item = getListItemByIndex(index - 1);
        E data = item.getNext().getData();
        item.setNext(item.getNext().getNext());

        count--;

        return data;
    }

    public boolean deleteByData(E data) {
        for (ListItem<E> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentItem.getData().equals(data)) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());

                    count--;
                }

                return true;
            }
        }

        return false;
    }

    public E deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }

        E data = head.getData();
        head = head.getNext();

        count--;

        return data;
    }

    public void insertByIndex(int index, E data) {
        if (index == 0) {
            addFirst(data);
        }

        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + count);
        }

        ListItem<E> item = getListItemByIndex(index - 1);
        ListItem<E> newItem = new ListItem<>(data, item.getNext());
        item.setNext(newItem);

        count++;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public ListItem<E> getListItemByIndex(int index) {
        int i = 0;

        for (ListItem<E> item = head; item != null; item = item.getNext()) {
            if (i == index) {
                return item;
            }

            i++;
        }

        return null;
    }

    public void reverse() {
        ListItem<E> previousItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copyList = new SinglyLinkedList<>();

        copyList.head = new ListItem<E>(head.getData());

        ListItem<E> currentOriginalItem = head.getNext();
        ListItem<E> currentCopyItem = copyList.head;

        while (currentOriginalItem != null) {
            currentCopyItem.setNext(new ListItem<>(currentOriginalItem.getData()));
            currentCopyItem = currentCopyItem.getNext();
            currentOriginalItem = currentOriginalItem.getNext();
        }

        return copyList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (ListItem<E> item = head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData())
                    .append("\n");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.toString();
    }

    private void checkIndexCorrectness(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (count - 1));
        }
    }
}
