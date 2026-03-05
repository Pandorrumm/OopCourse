package ru.nsk.pavlov.list;

import ru.nsk.pavlov.car_data.CarData;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList(int count) {
        this.count = count;
    }

    public ListItem<T> getHead() {
        return head;
    }
//    public SinglyLinkedList(ListItem<T> head, int count) {
//        this.head = head;
//        this.count = count;
//    }

    public void add(T item){
        head = new ListItem<>(item, head);
    }

    public void addToEnd(T item){
        ListItem<T> newItem = new ListItem<>(item);
    }

    public int getSize() {
        return count;
    }

    public T getDataFirstElement() {
        return head.getData();
    }


//    public T getDataByIndex(int index){
//
//    }
}
