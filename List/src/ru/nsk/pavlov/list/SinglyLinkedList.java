package ru.nsk.pavlov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }

        return head.getData();
    }

    public E getByIndex(int index) {
        checkIndex(index);

        return getListItemByIndex(index).getData();
    }

    public E setByIndex(int index, E newData) {
        checkIndex(index);

        ListItem<E> item = getListItemByIndex(index);

        E data = item.getData();
        item.setData(newData);

        return data;
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previousItem = getListItemByIndex(index - 1);
        E data = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        count--;
        return data;
    }

    public boolean deleteByData(E data) {
        for (ListItem<E> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(currentItem.getData(), data)) {
                if (previousItem == null) {
                    head = currentItem.getNext();
                } else {
                    previousItem.setNext(currentItem.getNext());
                }

                count--;
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
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + count);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getListItemByIndex(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        count++;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    private ListItem<E> getListItemByIndex(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
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

        if (head == null) {
            return copyList;
        }

        copyList.head = new ListItem<>(head.getData());
        copyList.count = count;

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
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        ListItem<E> item = head;

        while (item != null) {
            stringBuilder
                    .append(item.getData())
                    .append(", ");

            item = item.getNext();
        }

        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (count - 1));
        }
    }
}