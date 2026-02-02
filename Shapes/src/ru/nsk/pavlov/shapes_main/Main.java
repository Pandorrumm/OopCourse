package ru.nsk.pavlov.shapes_main;

import ru.nsk.pavlov.comparators.ShapeAreaReverseComparator;
import ru.nsk.pavlov.comparators.ShapePerimeterReverseComparator;
import ru.nsk.pavlov.shapes.*;

import java.util.Arrays;

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

        sortByAreaDescending(shapes);

        System.out.println("The shape with the maximum area:");
        System.out.println(shapes[0]);

        sortByPerimeterDescending(shapes);

        System.out.println();
        System.out.println("The figure with the second largest perimeter:");
        System.out.println(shapes[1]);
    }

    public static void sortByAreaDescending(Shape[] shapes) {
        Arrays.sort(shapes, new ShapeAreaReverseComparator());
    }

    public static void sortByPerimeterDescending(Shape[] shapes) {
        Arrays.sort(shapes, new ShapePerimeterReverseComparator());
    }
}
