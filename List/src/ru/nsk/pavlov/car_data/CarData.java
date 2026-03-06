package ru.nsk.pavlov.car_data;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CarData carData = (CarData) obj;
        return Objects.equals(carData.name, name) && Objects.equals(price, carData.price);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = name != null ? name.hashCode() : 0;
        hash = prime * hash + price;

        return hash;
    }
}
