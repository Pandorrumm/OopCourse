package ru.nsk.pavlov.list_main;

import ru.nsk.pavlov.car.Car;
import ru.nsk.pavlov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        Car car1Data = new Car("Ford", 100);
        Car car2Data = new Car("Nissan", 120);
        Car car3Data = new Car("Mazda", 200);
        Car car4Data = new Car("BMW", 1000);
        Car car5Data = new Car("Mustang", 400);
        Car car6Data = new Car("OKA", 50);
        Car car7Data = new Car("NIVA", 65);

        SinglyLinkedList<Car> carsDatas = new SinglyLinkedList<>();
        carsDatas.addFirst(car1Data);
        carsDatas.addFirst(car2Data);
        carsDatas.addFirst(car3Data);
        carsDatas.addFirst(car4Data);

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Size singlyLinkedList: " + carsDatas.getCount());

        System.out.println();
        System.out.println("First element data: " + carsDatas.getFirst().getName() + " - " + carsDatas.getFirst().getPrice());

        System.out.println();
        System.out.println("Get data by index 1: " + carsDatas.getByIndex(1).getName() + " - " + carsDatas.getByIndex(1).getPrice());

        System.out.println();
        System.out.println("Set data by index 1 " + car5Data.getName() + ", old data: " + carsDatas.setByIndex(1, car5Data));

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Delete element by index 2: " + carsDatas.deleteByIndex(2).getName());

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Inserting an item " + car6Data.getName() + " at beginning of the list");
        carsDatas.addFirst(car6Data);

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Inserting an item " + car7Data.getName() + " by index 3");
        carsDatas.insertByIndex(3, car7Data);

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Delete element by data " + car7Data.getName() + ": " + carsDatas.deleteByData(car7Data));

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Delete first element " + carsDatas.deleteFirst());

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Reverse list");
        carsDatas.reverse();

        System.out.println(carsDatas);

        System.out.println();
        System.out.println("Copy list");
        SinglyLinkedList<Car> copyCarsDatas = carsDatas.copy();

        System.out.println(copyCarsDatas);
    }
}
