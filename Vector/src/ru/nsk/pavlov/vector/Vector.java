package ru.nsk.pavlov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Dimension of the array must be > 0. Dimension of the array : " + n);
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Dimension of the array must be > 0. Dimension of the array : " + n);
        }

        components = Arrays.copyOf(array, Math.min(n, array.length));
    }

    public double[] getComponents() {
        return components;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < components.length; i++) {
            if (i != components.length - 1) {
                stringBuilder.append(components[i]);
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(components[i]);
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public Vector addingVector(Vector vector) {
        if (components.length != vector.components.length) {
            throw new IllegalArgumentException("Vectors of different dimensions");
        }

        double[] result = new double[components.length];

        for (int i = 0; i < vector.components.length; i++) {
            result[i] = components[i] + vector.components[i];
        }

        return new Vector(result);
    }

    public Vector subtractionVector(Vector vector) {
        if (components.length != vector.components.length) {
            throw new IllegalArgumentException("Vectors of different dimensions");
        }

        double[] result = new double[components.length];

        for (int i = 0; i < vector.components.length; i++) {
            result[i] = components[i] - vector.components[i];
        }

        return new Vector(result);
    }

    public Vector multiplicationByScalar(double scalar) {
        double[] result = new double[components.length];

        for (int i = 0; i < components.length; i++) {
            result[i] = components[i] * scalar;
        }

        return new Vector(result);
    }

    public Vector reverseVector() {
        double[] result = new double[components.length];

        for (int i = 0; i < components.length; i++) {
            result[i] = components[i] * -1;
        }

        return new Vector(result);
    }

    public double getVectorLength() {
        double result = 0;

        for (double component : components) {
            result += component * component;
        }

        return Math.sqrt(result);
    }

    public double getComponentByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Dimension of the array must be >= 0");
        }

        if (index >= components.length) {
            throw new IllegalArgumentException("The index goes beyond the array");
        }
        return components[index];
    }

    public void setComponentByIndex(int index, double component) {
        components[index] = component;
    }
}