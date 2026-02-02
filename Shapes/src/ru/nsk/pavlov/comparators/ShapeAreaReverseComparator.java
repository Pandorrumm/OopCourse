package ru.nsk.pavlov.comparators;

import ru.nsk.pavlov.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaReverseComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}