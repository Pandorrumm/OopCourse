package ru.nsk.pavlov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size of the vector components must be greater than 0. Now it is equal to " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The size of the vector components must be greater than 0. Now it is equal to " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size of the vector components must be greater than 0. Now it is equal to " + size);
        }

        components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        String separator = "";

        for (double component : components) {
            stringBuilder.append(separator);
            stringBuilder.append(component);
            separator = ", ";
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        int size = Math.max(components.length, vector.components.length);

        if (size > components.length) {
            components = Arrays.copyOf(components, size);
        }

        double[] otherComponents = size > vector.components.length ? Arrays.copyOf(vector.components, size) : vector.components;

        for (int i = 0; i < size; i++) {
            components[i] = components[i] + otherComponents[i];
        }
    }

    public void subtract(Vector vector) {
        int size = Math.max(components.length, vector.components.length);

        if (size > components.length) {
            components = Arrays.copyOf(components, size);
        }

        double[] otherComponents = size > vector.components.length ? Arrays.copyOf(vector.components, size) : vector.components;

        for (int i = 0; i < size; i++) {
            components[i] = components[i] - otherComponents[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < components.length; i++) {
            components[i] = -components[i];
        }
    }

    public double getVectorLength() {
        double sumOfSquares = 0;

        for (double component : components) {
            sumOfSquares += component * component;
        }

        return Math.sqrt(sumOfSquares);
    }

    public double getComponentByIndex(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (components.length - 1));
        }

        return components[index];
    }

    public void setComponentByIndex(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (components.length - 1));
        }

        components[index] = component;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getAdditionVectors(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);
        return result;
    }

    public static Vector getDifferenceVectors(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);
        return result;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minLength = Math.min(vector1.components.length, vector2.components.length);

        double result = 0;

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}