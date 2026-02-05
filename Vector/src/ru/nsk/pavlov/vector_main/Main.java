package ru.nsk.pavlov.vector_main;

import ru.nsk.pavlov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 3, 5});
        Vector vector2 = new Vector(new double[]{2, 0, 1, 0, 0});
        Vector vector3 = new Vector(new double[]{1, 1, 1});
        Vector vector4 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector5 = new Vector(new double[]{2, 2, 2, 2, 2});

        System.out.println("Vector length " + vector4 + " : " + vector4.getVectorLength());

        System.out.println();
        System.out.println("Add " + vector2 + " to " + vector1);
        vector1.add(vector2);
        System.out.println("Sum: " + vector1);

        System.out.println();
        System.out.println("Adding vectors " + vector1 + " and " + vector3 + ": " + Vector.getAdditionVectors(vector1, vector3));

        System.out.println();
        System.out.println("Subtract vector " + vector3 + " from vector " + vector2);
        vector2.subtract(vector3);
        System.out.println("Difference: " + vector2);

        System.out.println();
        System.out.println("Subtraction vectors " + vector2 + " and " + vector3 + ": " + Vector.getDifferenceVectors(vector2, vector3));

        System.out.println();
        System.out.println("Multiply vector " + vector5 + " by a scalar " + 2);
        vector5.multiplyByScalar(2);
        System.out.println("The result of multiplying a vector by a scalar: " + vector5);

        System.out.println();
        System.out.println("Scalar product of vectors " + vector1 + " and " + vector5 + ": " + Vector.getDotProduct(vector1, vector5));

        System.out.println();
        System.out.println("Reverse vector " + vector4);
        vector4.reverse();
        System.out.println("Vector " + vector4 + " reversal");

        System.out.println();
        System.out.println("Getting a component vector " + vector1 + " by index " + 2 + ": " + vector1.getComponentByIndex(4));

        System.out.println();
        System.out.println("Assign a component " + 100 + " to vector " + vector3 + " by index " + 0 + ": ");
        vector3.setComponentByIndex(0, 100);
        System.out.println(vector3);
    }
}
