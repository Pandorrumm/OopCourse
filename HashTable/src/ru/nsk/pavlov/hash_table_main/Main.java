package ru.nsk.pavlov.hash_table_main;

import ru.nsk.pavlov.hash_table.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>(10);

        hashTable.add("Honda");
        hashTable.add("BMW");
        hashTable.add("Audi");
        hashTable.add("Mazda");
        hashTable.add("Civic");

        System.out.println("Size hashTable: " + hashTable.size());

        System.out.println();
        System.out.println(hashTable);

        System.out.println();
        System.out.println("Contains element Mazda: " + hashTable.contains("Mazda"));

        System.out.println();
        System.out.println("Remove element Mazda: " + hashTable.remove("Mazda"));

        System.out.println();
        System.out.println("Contains element Mazda: " + hashTable.contains("Mazda"));

        System.out.println();
        System.out.println(hashTable);
    }
}
