package ru.nsk.pavlov.range_main;

import ru.nsk.pavlov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 10);
        Range range3 = new Range(20, 30);
        Range range4 = new Range(0, 5);
        Range range5 = new Range(0, 20);
        Range range6 = new Range(10, 40);

        System.out.println();
        System.out.println("Testing Intersection");
        testIntersection(range1, range2);
        testIntersection(range2, range3);
        testIntersection(range3, range4);
        testIntersection(range1, range4);

        System.out.println();
        System.out.println("Testing Union");
        testUnion(range1, range2);
        testUnion(range2, range3);
        testUnion(range3, range4);
        testUnion(range1, range4);

        System.out.println();
        System.out.println("Testing Difference");
        testDifference(range1, range2);
        testDifference(range1, range4);
        testDifference(range2, range5);
        testDifference(range6, range3);
    }

    public static void testIntersection(Range range1, Range range2) {
        Range intersection = range1.getIntersection(range2);
        System.out.println("Intersection " + range1 + " and " + range2 + " : " + (intersection != null ? intersection.toString() : " null"));
    }

    public static void testUnion(Range range1, Range range2) {
        Range[] union = range1.getUnion(range2);

        if (union.length == 1) {
            System.out.println("Unification " + range1 + " and " + range2 + " - First range: " + union[0].toString());
        } else if (union.length == 2) {
            System.out.println("Unification " + range1 + " and " + range2 + " - Two range: " + union[0].toString() + ", " + union[1].toString());
        }
    }

    public static void testDifference(Range range1, Range range2) {
        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.println("Difference " + range1 + " and " + range2 + " - There are no ranges");
        } else if (difference.length == 1) {
            System.out.println("Difference " + range1 + " and " + range2 + " - First range: " + difference[0].toString());
        } else if (difference.length == 2) {
            System.out.println("Difference " + range1 + " and " + range2 + " - Two range: " + difference[0].toString() + ", " + difference[1].toString());
        }
    }
}
