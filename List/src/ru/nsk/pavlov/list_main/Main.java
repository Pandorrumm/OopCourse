package ru.nsk.pavlov.list_main;

import ru.nsk.pavlov.car_data.CarData;
import ru.nsk.pavlov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        CarData car1Data = new CarData("Ford", 100);
        CarData car2Data = new CarData("Nissan", 120);
        CarData car3Data = new CarData("Mazda", 200);
        CarData car4Data = new CarData("BMW", 1000);
        CarData car5Data = new CarData("Mustang", 400);
        CarData car6Data = new CarData("OKA", 50);
        CarData car7Data = new CarData("NIVA", 65);

        SinglyLinkedList<CarData> carsDatas = new SinglyLinkedList<>(4);
        carsDatas.add(car1Data);
        carsDatas.add(car2Data);
        carsDatas.add(car3Data);
        carsDatas.add(car4Data);

        carsDatas.print();

        System.out.println();
        System.out.println("Size singlyLinkedList: " + carsDatas.getSize());

        System.out.println();
        System.out.println("First element data: " + carsDatas.getDataFirstElement().getName() + " - " + carsDatas.getDataFirstElement().getPrice());

        System.out.println();
        System.out.println("Size singlyLinkedList: " + carsDatas.getSize());

        System.out.println();
        System.out.println("Get data by index 1: " + carsDatas.getDataByIndex(1).getName() + " - " + carsDatas.getDataByIndex(1).getPrice());

        System.out.println();
        System.out.println("Set data by index 1 " + car5Data.getName());
        carsDatas.setDataByIndex(1, car5Data);

        carsDatas.print();

        System.out.println();
        System.out.println("Delete element by index 2: " + carsDatas.deleteElementByIndex(2).getName());

        carsDatas.print();

        System.out.println();
        System.out.println("Inserting an item " + car6Data.getName() + " at beginning of the list");
        carsDatas.insertAtBegin(car6Data);

        carsDatas.print();

        System.out.println();
        System.out.println("Inserting an item " + car7Data.getName() + " by index 3");
        carsDatas.insertElementByIndex(3, car7Data);

        carsDatas.print();

        System.out.println();
        System.out.println("Delete element by data " + car7Data.getName() + ": " + carsDatas.deleteElementByData(car7Data));

        carsDatas.print();

        System.out.println();
        System.out.println("Delete first element " + carsDatas.deleteFirstElement());

        carsDatas.print();

        System.out.println();
        System.out.println("Reverse list");
        carsDatas.reverse();

        carsDatas.print();

        System.out.println();
        System.out.println("Copy list");
        SinglyLinkedList<CarData> copyCarsDatas = carsDatas.copy();

        copyCarsDatas.print();
    }
}
