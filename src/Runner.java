import matrix.LatentAdjacencyMatrix;
import matrix.ExcelAdjacencyMatrixWriter;
import objects.Firm;
import objects.Director;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/20/16.
 */
public class Runner {

    private final static String BANK_SRC_PATH = "data/1907-08 Bank Directors Table.xlsx";
    private final static String INSURANCE_SRC_PATH = "data/1907-08 Insurance Directors Table.xlsx";
    private final static String BANK_INSURANCE_UNION_SRC_PATH = "data/1907-08 Bank and Insurance Directors Table.xlsx";
    private final static String UTILITIES_SRC_PATH = "data/1907-08 Utilities Table.xlsx";

    private final static String BANK_AM_FULL_OUT_PATH = "output/Bank_Full_AM.xlsx";
    private final static String BANK_DIRECTOR_AM_FULL_OUT_PATH = "output/BankDirector_Full_AM.xlsx";

    private final static String INSURANCE_FULL_AM_OUT_PATH = "output/Insurance_Full_AM.xlsx";
    private final static String INSURANCE_DIRECTOR_FULL_AM_OUT_PATH = "output/InsuranceDirector_Full_AM.xlsx";

    private final static String BANK_INSURANCE_UNION_FULL_AM_OUT_PATH = "output/BankInsuranceUnion_Full_AM.xlsx";
    private final static String BANK_INSURANCE_UNION_DIRECTOR_FULL_AM_OUT_PATH = "output/BankInsuranceUnionDirector_Full_AM.xlsx";

    private final static String UTILITIES_FULL_AM_OUT_PATH = "output/Utilities_Full_AM.xlsx";
    private final static String UTILITIES_DIRECTOR_FULL_AM_OUT_PATH = "output/UtilitiesDirector_Full_AM.xlsx";

    private final static String BANK_DIRECTOR_ALIASES_OUT_PATH = "output/BankDirector_Aliases.xlsx";


    private static void writeFirmAndDirectorAdjMatricesFromSourcePath(String sourcePath, String outPathFirm,
                                                                         String outPathDirector) throws IOException {
        //Read excel file and generate lists of firms and directors.
        ExcelReader er = new ExcelReader(sourcePath);
        ArrayList<Firm> firmList = er.getFirms();
        ArrayList<Director> directorList = er.getDirectors();

        //Get full list of firms and create adj matrix, then write to path.
        LatentAdjacencyMatrix<Firm, Director> firmAdjMatrix = new LatentAdjacencyMatrix<Firm, Director>(firmList);
        ExcelAdjacencyMatrixWriter firmWriter = new ExcelAdjacencyMatrixWriter(firmAdjMatrix, outPathFirm);
        firmWriter.writeAdjMatrixToExcel();


        //Get full list of directors and create adj matrix, then write to path.
        LatentAdjacencyMatrix<Director, Firm> directorAdjMatrix = new LatentAdjacencyMatrix<Director, Firm>(directorList);
        ExcelAdjacencyMatrixWriter fullDirectorWriter = new ExcelAdjacencyMatrixWriter(directorAdjMatrix, outPathDirector);
        fullDirectorWriter.writeAdjMatrixToExcel();
    }

    private static void printAliases(String sourcePath) throws IOException {
        ExcelReader er = new ExcelReader(sourcePath);
        ArrayList<Director> directorList = er.getDirectors();
        for (Director d : directorList) {
            if (d.getAliases().size() > 1) {
                System.out.println(d);
                for (String alias : d.getAliases()) {
                    System.out.println("\t\t" + alias);
                }
            }
        }
    }

    private static void writeAliasesToExcelFromSourcePath(String sourcePath, String outputPath) throws IOException {
        ExcelReader er = new ExcelReader(sourcePath);
        ArrayList<Director> directorList = er.getDirectors();
        ExcelAliasWriter aliasWriter = new ExcelAliasWriter(directorList, outputPath);
        aliasWriter.writeAliasesToExcel();
    }




    public static void main(String[] args) throws IOException {


        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_SRC_PATH,
                BANK_AM_FULL_OUT_PATH, BANK_DIRECTOR_AM_FULL_OUT_PATH);

        writeFirmAndDirectorAdjMatricesFromSourcePath(INSURANCE_SRC_PATH,
                INSURANCE_FULL_AM_OUT_PATH, INSURANCE_DIRECTOR_FULL_AM_OUT_PATH);

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_INSURANCE_UNION_SRC_PATH,
                BANK_INSURANCE_UNION_FULL_AM_OUT_PATH, BANK_INSURANCE_UNION_DIRECTOR_FULL_AM_OUT_PATH);

        writeFirmAndDirectorAdjMatricesFromSourcePath(UTILITIES_SRC_PATH,
                UTILITIES_FULL_AM_OUT_PATH, UTILITIES_DIRECTOR_FULL_AM_OUT_PATH);


        writeAliasesToExcelFromSourcePath(BANK_SRC_PATH, BANK_DIRECTOR_ALIASES_OUT_PATH);
    }
}

