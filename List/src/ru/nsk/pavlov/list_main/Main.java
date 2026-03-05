package ru.nsk.pavlov.list_main;

import ru.nsk.pavlov.car_data.CarData;
import ru.nsk.pavlov.list.ListItem;
import ru.nsk.pavlov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        CarData car1Data = new CarData("Ford", 100);
        CarData car2Data = new CarData("Nissan", 120);
        CarData car3Data = new CarData("Mazda", 200);

        SinglyLinkedList<CarData> carsDatas = new SinglyLinkedList<>(3);
        carsDatas.add(car1Data);
        carsDatas.add(car2Data);
        carsDatas.add(car3Data);

        System.out.println("SinglyLinkedList: ");

        for (ListItem<CarData> item = carsDatas.getHead(); item != null; item = item.getNext()) {
            System.out.println(item.getData().getName() + " - " + item.getData().getPrice());
        }

        System.out.println();
        System.out.println("Size singlyLinkedList: " + carsDatas.getSize());

        System.out.println();
        System.out.println("First element data: " + carsDatas.getDataFirstElement().getName() + " - " + carsDatas.getDataFirstElement().getPrice());
    }
}
