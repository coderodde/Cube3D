package net.coderodde.lib3d;

/**
 * This class represents a matrix.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6
 */
public class Matrix {

    /**
     * The actual elements.
     */
    private final double[][] matrix;

    /**
     * Constructs a zero matrix with <code>rows</code> rows and
     * <code>columns</code> columns.
     * 
     * @param rows    the amount of rows in the matrix.
     * @param columns the amount of columns in the new matrix.
     */
    public Matrix(int rows, int columns) {
        this.matrix = new double[rows][];

        for (int i = 0; i < rows; ++i) {
            this.matrix[i] = new double[columns];
        }
    }

    /**
     * Constructs a zero square matrix with <code>dimension</code> rows and
     * columns.
     * 
     * @param dimension the dimension of this square matrix.
     */
    public Matrix(int dimension) {
        this(dimension, dimension);
    }

    /**
     * Multiplies this matrix by vertex <code>v</code> and returns the result,
     * which is a vertex too.
     * 
     * @param  v the vertex to multiply by this matrix.
     * @return the result vertex.
     */
    public Vertex product(Vertex v) {
        double[] vector = new double[]{ v.x, v.y, v.z };
        double[] vec = new double[vector.length];

        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                vec[row] += vector[col] * matrix[row][col];
            }
        }

        return new Vertex(vec[0], vec[1], vec[2]);
    }

    /**
     * Returns the matrix for rotating around <tt>x</tt>-axis.
     * 
     * @param  angle the rotation angle in radians.
     * @return the rotation matrix.
     */
    public static Matrix getXRotationMatrix(double angle) {
        final Matrix ret = new Matrix(3);

        ret.matrix[0][0] = 1;
        ret.matrix[0][1] = 0;
        ret.matrix[0][2] = 0;
        ret.matrix[1][0] = 0;
        ret.matrix[2][0] = 0;

        ret.matrix[1][1] = Math.cos(angle);
        ret.matrix[1][2] = -Math.sin(angle);
        ret.matrix[2][1] = Math.sin(angle);
        ret.matrix[2][2] = Math.cos(angle);

        return ret;
    }

    /**
     * Returns the matrix for rotating around <tt>y</tt>-axis.
     * 
     * @param  angle the rotation angle in radians.
     * @return the rotation matrix.
     */
    public static Matrix getYRotationMatrix(double angle) {
        final Matrix ret = new Matrix(3);

        ret.matrix[0][1] = 0;
        ret.matrix[1][0] = 0;
        ret.matrix[1][2] = 0;
        ret.matrix[2][1] = 0;
        ret.matrix[1][1] = 1;

        ret.matrix[0][0] = +Math.cos(angle);
        ret.matrix[0][2] = +Math.sin(angle);
        ret.matrix[2][0] = -Math.sin(angle);
        ret.matrix[2][2] = +Math.cos(angle);

        return ret;
    }

    /**
     * Returns the matrix for rotating around <tt>z</tt>-axis.
     * 
     * @param  angle the rotation angle in radians.
     * @return the rotation matrix.
     */
    public static Matrix getZRotationMatrix(double angle) {
        final Matrix ret = new Matrix(3);

        ret.matrix[0][2] = 0;
        ret.matrix[1][2] = 0;
        ret.matrix[2][2] = 1;
        ret.matrix[2][1] = 0;
        ret.matrix[2][0] = 0;

        ret.matrix[0][0] =  Math.cos(angle);
        ret.matrix[0][1] = -Math.sin(angle);
        ret.matrix[1][0] =  Math.sin(angle);
        ret.matrix[1][1] =  Math.cos(angle);

        return ret;
    }
}