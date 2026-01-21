package ru.nsk.pavlov.range_main;

import ru.nsk.pavlov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(0, 10);
        Range range1 = new Range(5, 10);
        Range range2 = new Range(20, 30);
        Range range3 = new Range(0, 5);

        testIntersection(range, range1);
        testIntersection(range1, range2);
        testIntersection(range2, range3);
        testIntersection(range, range3);
    }

    public static void testIntersection(Range range1, Range range2) {
        Range intersection = range1.getIntersection(range2);
        System.out.println("Intersection" + printRange(range1) + " and" + printRange(range2) + " :" + (intersection != null ? printRange(intersection) : " null"));
    }

    public static String printRange(Range range) {
        return " (" + range.getFrom() + ", " + range.getTo() + ")";
    }
}
