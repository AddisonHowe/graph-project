import java.util.Arrays;

/**
 * Created by addisonhowe on 12/21/16.
 */
public class Matrix {

    private int[][] matrix;
    private int size;

    public Matrix(int size) {
        this.size = size;
        this.matrix = new int[size][size];
    }

    public void put(int value, int row, int col) {
        matrix[row][col] = value;
    }

    public int get(int row, int col) {
        return matrix[row][col];
    }
}
