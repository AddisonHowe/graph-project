import java.io.IOException;

/**
 * Created by addisonhowe on 12/20/16.
 */
public class Runner {

    private final static String SOURCE_PATH = "data/1907-08 Directors Complete Table.xlsx";
    private final static String OUTPUT_PATH = "outputMatrix.xls";

    public static void main(String[] args) throws IOException {
        MatrixGenerator mg = new MatrixGenerator(SOURCE_PATH, OUTPUT_PATH);
        mg.matrixToExcel();
    }
}

