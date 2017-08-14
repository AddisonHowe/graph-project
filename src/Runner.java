import alias.ExcelAliasWriter;
import excelhandler.ExcelReader;
import excelhandler.FileMerger;
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

    //PRIMARY TABLES
    private final static String BANK_SRC = "data/primary/1907-08 Bank Table.xlsx";
    private final static String INSURANCE_SRC = "data/primary/1907-08 Insurance Table.xlsx";
    private final static String MANUFACTURING_SRC = "data/primary/1907-08 Manufacturing Table.xlsx";
    private final static String UTILITIES_SRC = "data/primary/1907-08 Utilities Table.xlsx";
    private final static String NYCH1862_SRC = "data/primary/1862 NYCH Directors.xlsx";
    private final static String NYCH1859_SRC = "data/primary/1859 NYCH Directors.xlsx";

    //MERGED TABLES
    private final static String BANK_INSURANCE_SRC = "data/merged/1907-08 Bank & Insurance Table.xlsx";
    private final static String[] BANK_INSURANCE_SRC_ARRAY = new String[] {BANK_SRC, INSURANCE_SRC};

    private final static String BANK_MANUFACTURING_SRC = "data/merged/1907-08 Bank & Manufacturing Table.xlsx";
    private final static String[] BANK_MANUFACTURING_SRC_ARRAY = new String[] {BANK_SRC, MANUFACTURING_SRC};

    private final static String BANK_UTILITIES_SRC = "data/merged/1907-08 Bank & Utilities Table.xlsx";
    private final static String[] BANK_UTILITIES_SRC_ARRAY = new String[] {BANK_SRC, UTILITIES_SRC};

    //COMPLETE TABLE
    private final static String COMPLETE_SRC = "data/merged/1907-08 Complete Table.xlsx";
    private final static String[] COMPLETE_SRC_ARRAY = new String[] {BANK_SRC, INSURANCE_SRC, MANUFACTURING_SRC, UTILITIES_SRC};

    //ADJACENCY MATRICES
    private final static String BANK_AM_OUT = "output/firm matrices/primary/Bank AM.xlsx";
    private final static String BANK_DIRECTOR_AM_OUT = "output/director matrices/primary/Bank Director AM.xlsx";

    private final static String INSURANCE_AM_OUT = "output/firm matrices/primary/Insurance AM.xlsx";
    private final static String INSURANCE_DIRECTOR_AM_OUT = "output/director matrices/primary/Insurance Director AM.xlsx";

    private final static String MANUFACTURING_AM_OUT = "output/firm matrices/primary/Manufacturing AM.xlsx";
    private final static String MANUFACTURING_DIRECTOR_AM_OUT = "output/director matrices/primary/Manufacturing Director AM.xlsx";

    private final static String UTILITIES_AM_OUT = "output/firm matrices/primary/Utilities AM.xlsx";
    private final static String UTILITIES_DIRECTOR_AM_OUT = "output/director matrices/primary/Utilities Director AM.xlsx";

    private final static String NYCH1862_AM_OUT = "output/firm matrices/primary/NYCH 1862 AM.xlsx";
    private final static String NYCH1862_DIRECTOR_AM_OUT = "output/director matrices/primary/NYCH 1862 Director AM.xlsx";

    private final static String NYCH1859_AM_OUT = "output/firm matrices/primary/NYCH 1859 AM.xlsx";
    private final static String NYCH1859_DIRECTOR_AM_OUT = "output/director matrices/primary/NYCH 1859 Director AM.xlsx";

    private final static String BANK_INSURANCE_AM_OUT = "output/firm matrices/bank merges/Bank & Insurance AM.xlsx";
    private final static String BANK_INSURANCE_DIRECTOR_AM_OUT = "output/director matrices/bank merges/Bank & Insurance Director AM.xlsx";

    private final static String BANK_MANUFACTURING_AM_OUT = "output/firm matrices/bank merges/Bank & Manufacturing AM.xlsx";
    private final static String BANK_MANUFACTURING_DIRECTOR_AM_OUT = "output/director matrices/bank merges/Bank & Manufacturing Director AM.xlsx";

    private final static String BANK_UTILITIES_AM_OUT = "output/firm matrices/bank merges/Bank & Utilities AM.xlsx";
    private final static String BANK_UTILITIES_DIRECTOR_AM_OUT = "output/director matrices/bank merges/Bank & Utilities Director AM.xlsx";

    private final static String COMPLETE_AM_OUT = "output/firm matrices/complete/Complete AM.xlsx";
    private final static String COMPLETE_DIRECTOR_AM_OUT = "output/director matrices/complete/Complete Director AM.xlsx";

    //ALIAS TABLES
    private final static String BANK_DIRECTOR_ALIASES_OUT = "output/alias tables/Bank Director Aliases.xlsx";
    private final static String COMPLETE_DIRECTOR_ALIASES_OUT = "output/alias tables/Complete Aliases.xlsx";
    private final static String NYCH1862_DIRECTOR_ALIASES_OUT = "output/alias tables/NYCH 1862 Director Aliases.xlsx";
    private final static String NYCH1859_DIRECTOR_ALIASES_OUT = "output/alias tables/NYCH 1859 Director Aliases.xlsx";
    private final static String BANK_UTILITIES_ALIASES_OUT = "output/alias tables/Bank & Utilities Aliases.xlsx";

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

    private static void mergeSourceData() throws IOException {
        FileMerger.mergeFile(BANK_INSURANCE_SRC_ARRAY, BANK_INSURANCE_SRC);
        FileMerger.mergeFile(BANK_MANUFACTURING_SRC_ARRAY, BANK_MANUFACTURING_SRC);
        FileMerger.mergeFile(BANK_UTILITIES_SRC_ARRAY, BANK_UTILITIES_SRC);
        FileMerger.mergeFile(COMPLETE_SRC_ARRAY, COMPLETE_SRC);
    }

    private static void writeAliasesToExcelFromSourcePath(String sourcePath, String outputPath) throws IOException {
        ExcelReader er = new ExcelReader(sourcePath);
        ArrayList<Director> directorList = er.getDirectors();
        ExcelAliasWriter aliasWriter = new ExcelAliasWriter(directorList, outputPath);
        aliasWriter.writeAliasesToExcel();
    }



    public static void main(String[] args) throws IOException {

        //Create merged tables
        mergeSourceData();

        //Write primary matrices

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_SRC, BANK_AM_OUT, BANK_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(INSURANCE_SRC, INSURANCE_AM_OUT, INSURANCE_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(MANUFACTURING_SRC, MANUFACTURING_AM_OUT, MANUFACTURING_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(UTILITIES_SRC, UTILITIES_AM_OUT, UTILITIES_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(NYCH1862_SRC, NYCH1862_AM_OUT, NYCH1862_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(NYCH1859_SRC, NYCH1859_AM_OUT, NYCH1859_DIRECTOR_AM_OUT);


        //Write bank merge matrices

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_INSURANCE_SRC, BANK_INSURANCE_AM_OUT, BANK_INSURANCE_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_UTILITIES_SRC, BANK_UTILITIES_AM_OUT, BANK_UTILITIES_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(BANK_MANUFACTURING_SRC, BANK_MANUFACTURING_AM_OUT, BANK_MANUFACTURING_DIRECTOR_AM_OUT);

        writeFirmAndDirectorAdjMatricesFromSourcePath(COMPLETE_SRC, COMPLETE_AM_OUT, COMPLETE_DIRECTOR_AM_OUT);


        //Write aliases
        writeAliasesToExcelFromSourcePath(BANK_SRC, BANK_DIRECTOR_ALIASES_OUT);
        writeAliasesToExcelFromSourcePath(COMPLETE_SRC, COMPLETE_DIRECTOR_ALIASES_OUT);
        writeAliasesToExcelFromSourcePath(NYCH1862_SRC, NYCH1862_DIRECTOR_ALIASES_OUT);
        writeAliasesToExcelFromSourcePath(NYCH1859_SRC, NYCH1859_DIRECTOR_ALIASES_OUT);
        writeAliasesToExcelFromSourcePath(BANK_UTILITIES_SRC, BANK_UTILITIES_ALIASES_OUT);
    }
}

