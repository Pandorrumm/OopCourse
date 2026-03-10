package ru.nsk.pavlov.car;

import java.util.Objects;

public class Car {
    private String name;
    private int price;

    public Car(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Car carData = (Car) obj;
        return Objects.equals(carData.name, name) && Objects.equals(price, carData.price);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + (name != null ? name.hashCode() : 0);
        hash = prime * hash + price;

        return hash;
    }
}
