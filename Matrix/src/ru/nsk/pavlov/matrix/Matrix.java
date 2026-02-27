package ru.nsk.pavlov.matrix;

import ru.nsk.pavlov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Count of rows must be greater than 0. Now it is equal to " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Count of columns must be greater than 0. Now it is equal to " + columnsCount);
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

        int maxColumnCount = 0;

        for (double[] row : array) {
            maxColumnCount = Math.max(maxColumnCount, row.length);
        }

        if (maxColumnCount == 0) {
            throw new IllegalArgumentException("All rows are empty");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxColumnCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("The length of the array vectors must be greater than 0. Now it is equal to " + vectors.length);
        }

        int maxColumnCount = 0;

        for (Vector vector : vectors) {
            maxColumnCount = Math.max(maxColumnCount, vector.getSize());
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            Vector resultVector = new Vector(maxColumnCount);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                resultVector.setComponentByIndex(j, vectors[i].getComponentByIndex(j));
            }

            rows[i] = resultVector;
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
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

        if (row.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Row size mismatch. Expected size: " + rows[0].getSize() + " Now it is equal to " + row.getSize());
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
            return rows[0].getComponentByIndex(0);
        }

        if (matrix.rows.length == 2) {
            return matrix.rows[0].getComponentByIndex(0) * matrix.rows[1].getComponentByIndex(1) -
                    matrix.rows[0].getComponentByIndex(1) * matrix.rows[1].getComponentByIndex(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrix.rows.length; i++) {
            determinant += Math.pow(-1, i) * matrix.rows[0].getComponentByIndex(i) * getDeterminant(getExtractSubmatrix(matrix, i));
        }

        return determinant;
    }

    private static Matrix getExtractSubmatrix(Matrix matrix, int columnIndex) {
        int rowsCount = matrix.rows.length;

        Vector[] submatrixRows = new Vector[rowsCount - 1];

        for (int i = 1; i < rowsCount; i++) {
            Vector newRow = new Vector(matrix.rows[i].getSize() - 1);

            int componentIndex = 0;

            for (int j = 0; j < matrix.rows[i].getSize(); j++) {
                if (j == columnIndex) {
                    continue;
                }

                newRow.setComponentByIndex(componentIndex, matrix.rows[i].getComponentByIndex(j));
                componentIndex++;
            }

            submatrixRows[i - 1] = newRow;
        }

        return new Matrix(submatrixRows);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row)
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
        checkDimensionCompatibility(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkDimensionCompatibility(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkDimensionCompatibility(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkDimensionCompatibility(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int matrix1RowsCount = matrix1.rows.length;
        int matrix1ColumnsCount = matrix1.rows[0].getSize();
        int matrix2RowsCount = matrix2.rows.length;
        int matrix2ColumnsCount = matrix2.rows[0].getSize();

        if (matrix1ColumnsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("Incompatible matrix sizes for multiplication " + matrix1ColumnsCount + " and " + matrix2RowsCount);
        }

        Vector[] resultVectors = new Vector[matrix1RowsCount];

        for (int i = 0; i < matrix1RowsCount; i++) {
            Vector rowVector = new Vector(matrix2ColumnsCount);

            for (int j = 0; j < matrix2ColumnsCount; j++) {
                double sum = 0;

                for (int k = 0; k < matrix1ColumnsCount; k++) {
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

    private static void checkDimensionCompatibility(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.rows[0].getSize() != matrix2.rows[0].getSize()) {
            throw new IllegalArgumentException("The matrices have incompatible sizes: " + matrix1.rows.length + " and " + matrix2.rows.length +
                    " оr " + matrix1.rows[0].getSize() + " and " + matrix2.rows[0].getSize());
        }
    }
}