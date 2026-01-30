package ru.nsk.pavlov.shapes.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String getTitle() {
        return "Square";
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

        Square square = (Square) obj;
        return Double.compare(square.sideLength, sideLength) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(sideLength);
    }
}