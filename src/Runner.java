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

    private final static String BANK_SRC = "data/1907-08 Bank Table.xlsx";
    private final static String INSURANCE_SRC = "data/1907-08 Insurance Table.xlsx";
    private final static String UTILITIES_SRC = "data/1907-08 Utilities Table.xlsx";
    private final static String BANK_INSURANCE_SRC = "data/1907-08 Bank & Insurance Table.xlsx";
    private final static String INSURANCE_UTILITIES_SRC = "data/1907-08 Insurance & Utilities Table.xlsx";
    private final static String BANK_INSURANCE_UTILITIES_SRC = "data/1907-08 Bank & Insurance & Utilities Table.xlsx";


    private final static String BANK_AM_OUT = "output/Bank AM.xlsx";
    private final static String BANK_DIRECTOR_AM_OUT = "output/Bank Director AM.xlsx";

    private final static String INSURANCE_AM_OUT = "output/Insurance AM.xlsx";
    private final static String INSURANCE_DIRECTOR_AM_OUT = "output/Insurance Director AM.xlsx";

    private final static String UTILITIES_AM_OUT = "output/Utilities AM.xlsx";
    private final static String UTILITIES_DIRECTOR_AM_OUT = "output/Utilities Director AM.xlsx";

    private final static String BANK_INSURANCE_AM_OUT = "output/Bank & Insurance AM.xlsx";
    private final static String BANK_INSURANCE_DIRECTOR_AM_OUT = "output/Bank & Insurance Director AM.xlsx";

    private final static String INSURANCE_UTILITIES_AM_OUT = "output/Insurance & Utilities AM.xlsx";
    private final static String INSURANCE_UTILITIES_DIRECTOR_AM_OUT = "output/Insurance & Utilities Director AM.xlsx";

    private final static String BANK_INSURANCE_UTILITIES_AM_OUT = "output/Bank & Insurance & Utilities AM.xlsx";
    private final static String BANK_INSURANCE_UTILITIES_DIRECTOR_AM_OUT = "output/Bank & Insurance & Utilities Director AM.xlsx";



    private final static String BANK_DIRECTOR_ALIASES_OUT = "output/Bank Director Aliases.xlsx";


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

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_SRC,
                BANK_AM_OUT, BANK_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(INSURANCE_SRC,
                INSURANCE_AM_OUT, INSURANCE_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_INSURANCE_SRC,
                BANK_INSURANCE_AM_OUT, BANK_INSURANCE_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(UTILITIES_SRC,
                UTILITIES_AM_OUT, UTILITIES_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(INSURANCE_UTILITIES_SRC,
                INSURANCE_UTILITIES_AM_OUT, INSURANCE_UTILITIES_DIRECTOR_AM_OUT);


        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_INSURANCE_UTILITIES_SRC,
                BANK_INSURANCE_UTILITIES_AM_OUT, BANK_INSURANCE_UTILITIES_DIRECTOR_AM_OUT);


        writeAliasesToExcelFromSourcePath(BANK_SRC, BANK_DIRECTOR_ALIASES_OUT);
    }
}

