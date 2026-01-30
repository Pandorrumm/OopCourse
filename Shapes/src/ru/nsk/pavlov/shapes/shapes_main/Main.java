package ru.nsk.pavlov.shapes.shapes_main;

import ru.nsk.pavlov.shapes.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(100);
        Square square2 = new Square(13);
        Triangle triangle1 = new Triangle(1, 1, 2, 3, 3, 1);
        Triangle triangle2 = new Triangle(2, 2, 4, 6, 6, 2);
        Rectangle rectangle1 = new Rectangle(10, 5);
        Rectangle rectangle2 = new Rectangle(15, 4);
        Circle circle1 = new Circle(10);
        Circle circle2 = new Circle(13);

        Shape[] shapes = {square1, triangle1, rectangle1, circle1, square2, triangle2, rectangle2, circle2};

        sortByArea(shapes);

        System.out.println("The maximum area of the shape: " + shapes[0].getTitle());
        System.out.println(shapes[0]);

        sortByPerimeter(shapes);

        System.out.println();
        System.out.println("The figure with the second largest perimeter: " + shapes[1].getTitle());
        System.out.println(shapes[1]);

    }

    public static void sortByArea(Shape[] shapes) {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return Double.compare(shape2.getArea(), shape1.getArea());
            }
        });
    }

    public static void sortByPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
            }
        });
    }
}
