import java.io.IOException;

/**
 * Created by addisonhowe on 12/20/16.
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        MatrixGenerator mg = new MatrixGenerator();
        Matrix m = mg.getMatrix();
        mg.matrixToExcel();
        System.out.println(m);
        System.out.println("test");
    }
}

