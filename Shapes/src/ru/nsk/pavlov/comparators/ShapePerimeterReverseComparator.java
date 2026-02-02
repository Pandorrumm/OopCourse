package ru.nsk.pavlov.comparators;

import ru.nsk.pavlov.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterReverseComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}