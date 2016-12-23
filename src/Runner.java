import matrix.AdjacencyMatrix;
import matrix.ExcelAdjacencyMatrixWriter;
import objects.Bank;
import objects.Director;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/20/16.
 */
public class Runner {

    private final static String SOURCE_PATH = "data/1907-08 Directors Complete Table.xlsx";
    private final static String FULL_BANK_NODE_OUTPUT_PATH = "output/FullBankNodeAdjMatrix.xlsx";
    private final static String FULL_DIRECTOR_NODE_OUTPUT_PATH = "output/FullDirectorNodeAdjMatrix.xlsx";

    public static void main(String[] args) throws IOException {

        //Read excel file and generate lists of banks and directors.
        //Note that these lists may have the potential to be filtered.
        ExcelReader reader = new ExcelReader(SOURCE_PATH);

        //Get full list of banks and create adj matrix, then write to path.
        ArrayList<Bank> bankList = reader.getBanks();
        AdjacencyMatrix<Bank> fullBankAdjMatrix = new AdjacencyMatrix<Bank>(bankList);
        ExcelAdjacencyMatrixWriter fullBankWriter =
                new ExcelAdjacencyMatrixWriter(fullBankAdjMatrix, FULL_BANK_NODE_OUTPUT_PATH);
        fullBankWriter.writeAdjMatrixToExcel();


        //Get full list of directors and create adj matrix, then write to path.
        ArrayList<Director> directorList = reader.getDirectors();
        AdjacencyMatrix<Director> fullDirectorAdjMatrix = new AdjacencyMatrix<Director>(directorList);
        ExcelAdjacencyMatrixWriter fullDirectorWriter =
                new ExcelAdjacencyMatrixWriter(fullDirectorAdjMatrix, FULL_DIRECTOR_NODE_OUTPUT_PATH);
        fullDirectorWriter.writeAdjMatrixToExcel();

    }
}

