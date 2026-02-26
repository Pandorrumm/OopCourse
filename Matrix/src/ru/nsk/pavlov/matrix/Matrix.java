package ru.nsk.pavlov.matrix;

import ru.nsk.pavlov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("The value of the count of rows must be greater than 0. Now it is equal to " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("The value of the count of columns must be greater than 0. Now it is equal to " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The length of the array must be greater than 0. Now it is equal to " + array.length);
        }

        int size = 0;

        for (double[] row : array) {
            size = Math.max(size, row.length);
        }

        if (size == 0) {
            throw new IllegalArgumentException("All rows are empty");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(size, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("The length of the array vectors must be greater than 0. Now it is equal to " + vectors.length);
        }

        int size = 0;

        for (Vector vector : vectors) {
            size = Math.max(size, vector.getSize());
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            double[] components = new double[size];

            for (int j = 0; j < vectors[i].getSize(); j++) {
                components[j] = vectors[i].getComponentByIndex(j);
            }

            rows[i] = new Vector(components);
        }
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        if (rows.length == 0) {
            return 0;
        }

        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (rows.length - 1));
        }

        return rows[index];
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (rows.length - 1));
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: from 0 to " + (rows[0].getSize() - 1));
        }

        Vector columnVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            columnVector.setComponentByIndex(i, rows[i].getComponentByIndex(index));
        }

        return columnVector;
    }

    public void transpose() {
        Vector[] transposedVectors = new Vector[rows[0].getSize()];

        for (int i = 0; i < rows[0].getSize(); i++) {
            transposedVectors[i] = getColumn(i);
        }

        rows = transposedVectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double calculateDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalStateException("The matrix must be square. Current size: " + rows.length + "x" + rows[0].getSize());
        }

        return getDeterminant(this);
    }

    private double getDeterminant(Matrix matrix) {
        if (matrix.rows.length == 1) {
            return getRow(0).getComponentByIndex(0);
        }

        if (matrix.rows.length == 2) {
            return matrix.getRow(0).getComponentByIndex(0) * matrix.getRow(1).getComponentByIndex(1) -
                    matrix.getRow(0).getComponentByIndex(1) * matrix.getRow(1).getComponentByIndex(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrix.rows.length; i++) {
            determinant += Math.pow(-1, i) * matrix.getRow(0).getComponentByIndex(i) * getDeterminant(extractSubmatrix(matrix, i));
        }

        return determinant;
    }

    private Matrix extractSubmatrix(Matrix matrix, int columnIndex) {
        Vector[] submatrixRows = new Vector[matrix.rows.length - 1];

        int rowIndex = 0;

        for (int i = 1; i < matrix.rows.length; i++) {
            Vector newRow = new Vector(matrix.getRow(i).getSize() - 1);

            int componentIndex = 0;

            for (int j = 0; j < matrix.getRow(i).getSize(); j++) {
                if (j == columnIndex) {
                    continue;
                }

                newRow.setComponentByIndex(componentIndex, matrix.getRow(i).getComponentByIndex(j));
                componentIndex++;
            }

            submatrixRows[rowIndex] = newRow;
            rowIndex++;
        }

        return new Matrix(submatrixRows);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row.toString())
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("The size of the vector does not match the number of columns. Vector size: " + vector.getSize() + " rows " + rows[0].getSize());
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            result.setComponentByIndex(i, Vector.getDotProduct(rows[i], vector));
        }

        return new Vector(result);
    }

    public void add(Matrix matrix) {
        checkSizes(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSizes(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("The matrices must have the same dimensions for addition");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("The matrices must have the same dimensions for subtraction");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException("Incompatible matrix sizes for multiplication " + matrix1.rows[0].getSize() + " and " + matrix2.rows.length);
        }

        Vector[] resultVectors = new Vector[matrix1.rows.length];

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector rowVector = new Vector(matrix2.rows[0].getSize());

            for (int j = 0; j < matrix2.rows[0].getSize(); j++) {
                double sum = 0;

                for (int k = 0; k < matrix1.rows[0].getSize(); k++) {
                    sum += matrix1.rows[i].getComponentByIndex(k) * matrix2.rows[k].getComponentByIndex(j);
                }

                rowVector.setComponentByIndex(j, sum);
            }

            resultVectors[i] = rowVector;
        }

        return new Matrix(resultVectors);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) obj;
        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }

    private void checkSizes(Matrix matrix) {
        if (matrix.rows.length != rows.length || matrix.rows[0].getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("The matrices have incompatible sizes: " + matrix.rows.length + " and " + rows.length +
                    " оr " + matrix.rows[0].getSize() + " and " + rows[0].getSize());
        }
    }
}