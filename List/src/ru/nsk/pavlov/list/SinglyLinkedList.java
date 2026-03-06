package ru.nsk.pavlov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private final int count;

    public SinglyLinkedList(int count) {
        this.count = count;
    }

    public ListItem<T> getHead() {
        return head;
    }

    public void add(T item) {
        head = new ListItem<>(item, head);
    }

    public int getSize() {
        return count;
    }

    public T getDataFirstElement() {
        return head.getData();
    }

    public T getDataByIndex(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (count - 1));
        }

        int currentIndex = 0;
        T data = null;

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (currentIndex == index) {
                data = item.getData();
            }

            currentIndex++;
        }

        return data;
    }

    public void setDataByIndex(int index, T newData) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (count - 1));
        }

        int currentIndex = 0;

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (currentIndex == index) {
                item.setData(newData);
            }

            currentIndex++;
        }
    }

    public T deleteElementByIndex(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (count - 1));
        }

        int currentIndex = 0;
        T data = null;

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (currentIndex == index - 1) {
                data = item.getNext().getData();
                item.setNext(item.getNext().getNext());

                break;
            }

            currentIndex++;
        }

        return data;
    }

    public boolean deleteElementByData(T data) {
        int currentIndex = 0;

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (item.getData() == data) {
                deleteElementByIndex(currentIndex--);
                return true;
            }

            currentIndex++;
        }

        return false;
    }

    public T deleteFirstElement() {
        ListItem<T> item = head;
        head = head.getNext();

        return item.getData();
    }

    public void insertElementByIndex(int index, T data) {
        if (index == 0) {
            insertAtBegin(data);
        }

        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (count - 1));
        }

        int currentIndex = 0;

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (currentIndex == index - 1) {
                ListItem<T> newItem = new ListItem<>(data);
                newItem.setNext(item.getNext());
                item.setNext(newItem);
            }

            currentIndex++;
        }
    }

    public void insertAtBegin(T data) {
        ListItem<T> item = new ListItem<>(data);
        item.setNext(head);
        head = item;
    }

    public void reverse() {
        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList copyList = new SinglyLinkedList(count);

        copyList.head = new ListItem<T>(this.head.getData());

        ListItem<T> currentOriginalItem = this.head.getNext();
        ListItem currentCopyItem = copyList.head;

        while (currentOriginalItem != null) {
            currentCopyItem.setNext(new ListItem<>(currentOriginalItem.getData()));
            currentCopyItem = currentCopyItem.getNext();
            currentOriginalItem = currentOriginalItem.getNext();
        }

        return copyList;
    }

    public void print() {
        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            System.out.println(item.getData());
        }
    }
}
