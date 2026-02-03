package ru.nsk.pavlov.vector_main;

import ru.nsk.pavlov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 3, 5});
        Vector vector2 = new Vector(new double[]{2, 0, 1});

        System.out.println(vector1);
        System.out.println(vector1.addingVector(vector2));
        System.out.println(vector1.subtractionVector(vector2));
        System.out.println(vector1.multiplicationByScalar(2));

        System.out.println(vector1.reverseVector());

        System.out.println(vector1.getVectorLength());

        System.out.println(vector1.getComponentByIndex(2));

        vector1.setComponentByIndex(0, 5);
        System.out.println(vector1);
    }
}
