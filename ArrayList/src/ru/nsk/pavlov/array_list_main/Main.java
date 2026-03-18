package ru.nsk.pavlov.array_list_main;

import ru.nsk.pavlov.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(5);

        list.add(55);
        list.add(0);
        list.add(10);
        list.add(25);
        list.add(1);

        ArrayList<Integer> addedList1 = new ArrayList<>(2);

        addedList1.add(30);
        addedList1.add(31);

        ArrayList<Integer> addedList2 = new ArrayList<>(2);

        addedList2.add(11);
        addedList2.add(12);

        System.out.println(list.size());
        System.out.println(list);

        System.out.println();
        System.out.println("Does the list contain 25: " + list.contains(25));

        System.out.println();
        System.out.println("Add new element 100: " + list.add(100));
        System.out.println(list);

        System.out.println();
        System.out.println("Add new element 200 by index 2");
        list.add(2, 200);
        System.out.println(list);

        System.out.println();
        System.out.println("Add collection " + addedList1);
        list.addAll(addedList1);
        System.out.println(list);

        System.out.println();
        System.out.println("Add collection by index 2 " + addedList2);
        list.addAll(2, addedList2);
        System.out.println(list);

        System.out.println();
        System.out.println("Remove element by index 4");
        list.remove(4);
        System.out.println(list);

        System.out.println();
        System.out.println("Set element 1000 by index 5: " + list.set(5, 1000));
        System.out.println(list);
    }
}
