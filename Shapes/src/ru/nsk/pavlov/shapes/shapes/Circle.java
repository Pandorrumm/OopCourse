package ru.nsk.pavlov.shapes.shapes;

import java.util.Objects;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getTitle() {
        return "Circle";
    }

    @Override
    public String toString() {
        return "Width: " + getWidth() + System.lineSeparator() + "Height: " + getHeight() + System.lineSeparator() + "Perimeter: " + getPerimeter() + System.lineSeparator() + "Area: " + getArea();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Circle circle = (Circle) obj;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}