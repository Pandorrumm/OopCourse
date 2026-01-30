package ru.nsk.pavlov.shapes.shapes;

import java.util.Objects;

public class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String getTitle() {
        return "Rectangle";
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

        Rectangle rectangle = (Rectangle) obj;
        return Double.compare(rectangle.length, length) == 0 &&
                Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Double.hashCode(length), Double.hashCode(width));
    }
}
