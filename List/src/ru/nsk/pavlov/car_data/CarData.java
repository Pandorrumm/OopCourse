package ru.nsk.pavlov.car_data;

import java.util.Arrays;

public class CarData {
    private String name;
    private Integer price;

    public CarData(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice();
    }
}
