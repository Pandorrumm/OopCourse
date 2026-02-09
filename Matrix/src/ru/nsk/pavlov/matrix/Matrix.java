package ru.nsk.pavlov.matrix;

import ru.nsk.pavlov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private final Vector[] rows;
    private final int rowsNumber;
    private final int columnsNumber;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber <= 0) {
            rowsNumber = 1;
        }

        if (columnsNumber <= 0) {
            columnsNumber = 1;
        }

        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;

        rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Matrix other) {
        rowsNumber = other.rowsNumber;
        columnsNumber = other.columnsNumber;

        rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(other.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            rowsNumber = 1;
            columnsNumber = 1;

            rows = new Vector[1];
            rows[0] = new Vector(1);

            return;
        }

        rowsNumber = array.length;
        columnsNumber = array[0].length;

        rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            double[] rowValues;

            if (array[i].length != columnsNumber) {
                rowValues = new double[columnsNumber];
            } else {
                rowValues = Arrays.copyOf(array[i], columnsNumber);
            }

            rows[i] = new Vector(rowValues);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            rowsNumber = 1;
            columnsNumber = 1;

            rows = new Vector[1];
            rows[0] = new Vector(1);

            return;
        }

        rowsNumber = vectors.length;
        columnsNumber = vectors[0].getSize();

        for (int i = 0; i < rowsNumber; i++) {
            if (vectors[i].getSize() != columnsNumber) {
                vectors[i] = new Vector(columnsNumber);
            }
        }

        rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(vectors[i]);
        }
    }
}
