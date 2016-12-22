import java.io.IOException;

/**
 * Created by addisonhowe on 12/20/16.
 */
public class Runner {

    private final static String SOURCE_PATH = "data/1907-08 Directors Complete Table.xlsx";
    private final static String FULL_BANK_NODE_OUTPUT_PATH = "output/FullBankNodeAdjMatrix.xls";
    private final static String FULL_DIRECTOR_NODE_OUTPUT_PATH = "output/FullDirectorNodeAdjMatrix.xls";

    public static void main(String[] args) throws IOException {
        ExcelAdjacencyMatrixGenerator mg = new ExcelAdjacencyMatrixGenerator(SOURCE_PATH,
                FULL_BANK_NODE_OUTPUT_PATH, FULL_DIRECTOR_NODE_OUTPUT_PATH);
        mg.writeFullBankNodeAdjMatrixToExcel();
    }
}

