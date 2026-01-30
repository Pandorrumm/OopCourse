package ru.nsk.pavlov.shapes.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double sideALength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double sideBLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double sideCLength = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

        double semiPerimeter = (sideALength + sideBLength + sideCLength) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - sideALength) * (semiPerimeter - sideBLength) * (semiPerimeter - sideCLength));
    }

    @Override
    public double getPerimeter() {
        double sideALength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double sideBLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double sideCLength = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

        return sideALength + sideBLength + sideCLength;
    }

    @Override
    public String getTitle() {
        return "Triangle";
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

        Triangle triangle = (Triangle) obj;
        return Double.compare(triangle.x1, x1) == 0 &&
                Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 &&
                Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Double.hashCode(x1), Double.hashCode(y1),
                Double.hashCode(x2), Double.hashCode(y2),
                Double.hashCode(x3), Double.hashCode(y3));
    }
}