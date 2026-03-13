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

        System.out.println(list.size());
        System.out.println(list);
    }
}
