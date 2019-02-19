import alias.ExcelAliasWriter;
import excelhandler.ExcelReader;
import excelhandler.FileMerger;
import matrix.CSVAdjacencyMatrixWriter;
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

    // PRIMARY TABLES
    // 1906
    private final static String SRC_1906_BANKS = "data/primary/src_1906_banks.xlsx";
    private final static String SRC_1906_UTILS = "data/primary/src_1906_utils.xlsx";
    // 1907-08
    private final static String SRC_1907_BANKS = "data/primary/src_1907-08_banks.xlsx";
    private final static String SRC_1907_INSUR = "data/primary/src_1907-08_insur.xlsx";
    private final static String SRC_1907_MANUF = "data/primary/src_1907-08_manuf.xlsx";
    private final static String SRC_1907_UTILS = "data/primary/src_1907-08_utils.xlsx";
    // 1909-10
    private final static String SRC_1909_BANKS = "data/primary/src_1909-10_banks.xlsx";
    private final static String SRC_1909_UTILS = "data/primary/src_1909-10_utils.xlsx";
    // 1911-12
    private final static String SRC_1911_BANKS = "data/primary/src_1911-12_banks.xlsx";
    private final static String SRC_1911_UTILS = "data/primary/src_1911-12_utils.xlsx";
    // 1913-14
    private final static String SRC_1913_BANKS = "data/primary/src_1913-14_banks.xlsx";
    private final static String SRC_1913_UTILS = "data/primary/src_1913-14_utils.xlsx";
    // 1940
    private final static String SRC_1940_DATA = "data/primary/src_1940_data.xlsx";

    // MERGED TABLES
    // 1906
    private final static String SRC_1906_BANKS_UTILS = "data/merged/src_1906_banks-utils.xlsx";
    private final static String[] SRC_1906_BANKS_UTILS_ARRAY = new String[] {SRC_1906_BANKS, SRC_1906_UTILS};
    // 1907-08
    private final static String SRC_1907_BANKS_INSUR = "data/merged/src_1907-08_banks-insur.xlsx";
    private final static String[] SRC_1907_BANKS_INSUR_ARRAY = new String[] {SRC_1907_BANKS, SRC_1907_INSUR};
    private final static String SRC_1907_BANKS_MANUF = "data/merged/src_1907-08_banks-manuf.xlsx";
    private final static String[] SRC_1907_BANKS_MANUF_ARRAY = new String[] {SRC_1907_BANKS, SRC_1907_MANUF};
    private final static String SRC_1907_BANKS_UTILS = "data/merged/src_1907-08_banks-utils.xlsx";
    private final static String[] SRC_1907_BANKS_UTILS_ARRAY = new String[] {SRC_1907_BANKS, SRC_1907_UTILS};
    private final static String SRC_1907_COMPLETE = "data/merged/src_1907-08_complete.xlsx";
    private final static String[] SRC_1907_COMPLETE_ARRAY = new String[] {SRC_1907_BANKS, SRC_1907_INSUR, SRC_1907_MANUF, SRC_1907_UTILS};
    // 1909-10
    private final static String SRC_1909_BANKS_UTILS = "data/merged/src_1909-10_banks-utils.xlsx";
    private final static String[] SRC_1909_BANKS_UTILS_ARRAY = new String[] {SRC_1909_BANKS, SRC_1909_UTILS};
    // 1911-12
    private final static String SRC_1911_BANKS_UTILS = "data/merged/src_1911-12_banks-utils.xlsx";
    private final static String[] SRC_1911_BANKS_UTILS_ARRAY = new String[] {SRC_1911_BANKS, SRC_1911_UTILS};
    // 1913-14
    private final static String SRC_1913_BANKS_UTILS = "data/merged/src_1913-14_banks-utils.xlsx";
    private final static String[] SRC_1913_BANKS_UTILS_ARRAY = new String[] {SRC_1913_BANKS, SRC_1913_UTILS};

    // ADJACENCY MATRICES
    // 1906
    private final static String FIRM_1906_BANKS_AM = "output/firm matrices/firm_1906_banks.xlsx";
    private final static String DIRE_1906_BANKS_AM = "output/director matrices/director_1906_banks.xlsx";
    private final static String FIRM_1906_UTILS_AM = "output/firm matrices/firm_1906_utils.xlsx";
    private final static String DIRE_1906_UTILS_AM = "output/director matrices/director_1906_utils.xlsx";
    private final static String FIRM_1906_BANKS_UTILS_AM = "output/firm matrices/firm_1906_banks-utils.xlsx";
    private final static String DIRE_1906_BANKS_UTILS_AM = "output/director matrices/director_1906_banks-utils.xlsx";
    // 1907-08
    private final static String FIRM_1907_BANKS_AM = "output/firm matrices/firm_1907-08_banks.xlsx";
    private final static String DIRE_1907_BANKS_AM = "output/director matrices/director_1907-08_banks.xlsx";
    private final static String FIRM_1907_INSUR_AM = "output/firm matrices/firm_1907-08_insur.xlsx";
    private final static String DIRE_1907_INSUR_AM = "output/director matrices/director_1907-08_insur.xlsx";
    private final static String FIRM_1907_MANUF_AM = "output/firm matrices/firm_1907-08_manuf.xlsx";
    private final static String DIRE_1907_MANUF_AM = "output/director matrices/director_1907-08_manuf.xlsx";
    private final static String FIRM_1907_UTILS_AM = "output/firm matrices/firm_1907-08_utils.xlsx";
    private final static String DIRE_1907_UTILS_AM = "output/director matrices/director_1907-08_utils.xlsx";
    private final static String FIRM_1907_BANKS_INSUR_AM = "output/firm matrices/firm_1907-08_banks-insur.xlsx";
    private final static String DIRE_1907_BANKS_INSUR_AM = "output/director matrices/director_1907-08_banks-insur.xlsx";
    private final static String FIRM_1907_BANKS_MANUF_AM = "output/firm matrices/firm_1907-08_banks-manuf.xlsx";
    private final static String DIRE_1907_BANKS_MANUF_AM = "output/director matrices/director_1907-08_banks-manuf.xlsx";
    private final static String FIRM_1907_BANKS_UTILS_AM = "output/firm matrices/firm_1907-08_banks-utils.xlsx";
    private final static String DIRE_1907_BANKS_UTILS_AM = "output/director matrices/director_1907-08_banks-utils.xlsx";
    private final static String FIRM_1907_COMPLETE_AM = "output/firm matrices/firm_1907-08_complete.xlsx";
    private final static String DIRE_1907_COMPLETE_AM = "output/director matrices/director_1907-08_complete.xlsx";
    // 1909-10
    private final static String FIRM_1909_BANKS_AM = "output/firm matrices/firm_1909-10_banks.xlsx";
    private final static String DIRE_1909_BANKS_AM = "output/director matrices/director_1909-10_banks.xlsx";
    private final static String FIRM_1909_UTILS_AM = "output/firm matrices/firm_1909-10_utils.xlsx";
    private final static String DIRE_1909_UTILS_AM = "output/director matrices/director_1909-10_utils.xlsx";
    private final static String FIRM_1909_BANKS_UTILS_AM = "output/firm matrices/firm_1909-10_banks-utils.xlsx";
    private final static String DIRE_1909_BANKS_UTILS_AM = "output/director matrices/director_1909-10_banks-utils.xlsx";
    // 1911-12
    private final static String FIRM_1911_BANKS_AM = "output/firm matrices/firm_1911-12_banks.xlsx";
    private final static String DIRE_1911_BANKS_AM = "output/director matrices/director_1911-12_banks.xlsx";
    private final static String FIRM_1911_UTILS_AM = "output/firm matrices/firm_1911-12_utils.xlsx";
    private final static String DIRE_1911_UTILS_AM = "output/director matrices/director_1911-12_utils.xlsx";
    private final static String FIRM_1911_BANKS_UTILS_AM = "output/firm matrices/firm_1911-12_banks-utils.xlsx";
    private final static String DIRE_1911_BANKS_UTILS_AM = "output/director matrices/director_1911-12_banks-utils.xlsx";
    // 1913-14
    private final static String FIRM_1913_BANKS_AM = "output/firm matrices/firm_1913-14_banks.xlsx";
    private final static String DIRE_1913_BANKS_AM = "output/director matrices/director_1913-14_banks.xlsx";
    private final static String FIRM_1913_UTILS_AM = "output/firm matrices/firm_1913-14_utils.xlsx";
    private final static String DIRE_1913_UTILS_AM = "output/director matrices/director_1913-14_utils.xlsx";
    private final static String FIRM_1913_BANKS_UTILS_AM = "output/firm matrices/firm_1913-14_banks-utils.xlsx";
    private final static String DIRE_1913_BANKS_UTILS_AM = "output/director matrices/director_1913-14_banks-utils.xlsx";
    // 1940 CSV
    private final static String FIRM_1940_DATA_AM = "output/firm matrices/firm_1940_data.csv";
    private final static String DIRE_1940_DATA_AM = "output/director matrices/director_1940_data.csv";

    // ALIAS TABLES
    // 1906
    private final static String ALIASES_1906_BANKS = "output/alias tables/aliases_1906_banks.xlsx";
    private final static String ALIASES_1906_BANKS_UTILS = "output/alias tables/aliases_1906_banks-utils.xlsx";
    // 1907-08
    private final static String ALIASES_1907_COMPLETE = "output/alias tables/aliases_1907-08_complete.xlsx";
    private final static String ALIASES_1907_BANKS_UTILS = "output/alias tables/aliases_1907-08_banks-utils.xlsx";
    // 1909-10
    private final static String ALIASES_1909_BANKS_UTILS = "output/alias tables/aliases_1909-10_banks-utils.xlsx";
    // 1911-12
    private final static String ALIASES_1911_BANKS_UTILS = "output/alias tables/aliases_1911-12_banks-utils.xlsx";
    // 1913-14
    private final static String ALIASES_1913_BANKS_UTILS = "output/alias tables/aliases_1913-14_banks-utils.xlsx";
    // 1940
    private final static String ALIASES_1940_DATA = "output/alias tables/aliases_1940_data.xlsx";

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

    private static void writeAliasesToExcelFromSourcePath(String sourcePath, String outputPath) throws IOException {
        ExcelReader er = new ExcelReader(sourcePath);
        ArrayList<Director> directorList = er.getDirectors();
        ExcelAliasWriter aliasWriter = new ExcelAliasWriter(directorList, outputPath);
        aliasWriter.writeAliasesToExcel();
    }

    private static void writeCSVFirmAndDirectorAdjMatricesFromSourcePath(String sourcePath, String outPathFirm,
                                                                      String outPathDirector) throws IOException {
        //Read excel file and generate lists of firms and directors.
        ExcelReader er = new ExcelReader(sourcePath);
        ArrayList<Firm> firmList = er.getFirms();
        ArrayList<Director> directorList = er.getDirectors();

        //Get full list of firms and create adj matrix, then write to path.
        LatentAdjacencyMatrix<Firm, Director> firmAdjMatrix = new LatentAdjacencyMatrix<Firm, Director>(firmList);
        CSVAdjacencyMatrixWriter firmWriter = new CSVAdjacencyMatrixWriter(firmAdjMatrix, outPathFirm);
        firmWriter.writeAdjMatrixToCSV();


        //Get full list of directors and create adj matrix, then write to path.
        LatentAdjacencyMatrix<Director, Firm> directorAdjMatrix = new LatentAdjacencyMatrix<Director, Firm>(directorList);
        CSVAdjacencyMatrixWriter fullDirectorWriter = new CSVAdjacencyMatrixWriter(directorAdjMatrix, outPathDirector);
        fullDirectorWriter.writeAdjMatrixToCSV();
    }


    public static void main(String[] args) throws IOException {

        // Write primary matrices
        // 1906
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1906_BANKS, FIRM_1906_BANKS_AM, DIRE_1906_BANKS_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1906_UTILS, FIRM_1906_UTILS_AM, DIRE_1906_UTILS_AM);
        // 1907-08
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_BANKS, FIRM_1907_BANKS_AM, DIRE_1907_BANKS_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_INSUR, FIRM_1907_INSUR_AM, DIRE_1907_INSUR_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_MANUF, FIRM_1907_MANUF_AM, DIRE_1907_MANUF_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_UTILS, FIRM_1907_UTILS_AM, DIRE_1907_UTILS_AM);
        // 1909-10
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1909_BANKS, FIRM_1909_BANKS_AM, DIRE_1909_BANKS_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1909_UTILS, FIRM_1909_UTILS_AM, DIRE_1909_UTILS_AM);
        // 1911-12
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1911_BANKS, FIRM_1911_BANKS_AM, DIRE_1911_BANKS_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1911_UTILS, FIRM_1911_UTILS_AM, DIRE_1911_UTILS_AM);
        // 1913-14
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1913_BANKS, FIRM_1913_BANKS_AM, DIRE_1913_BANKS_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1913_UTILS, FIRM_1913_UTILS_AM, DIRE_1913_UTILS_AM);
        // 1940 CSV
        writeCSVFirmAndDirectorAdjMatricesFromSourcePath(SRC_1940_DATA, FIRM_1940_DATA_AM, DIRE_1940_DATA_AM);

        // Write merged matrices
        // 1906
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1906_BANKS_UTILS, FIRM_1906_BANKS_UTILS_AM, DIRE_1906_BANKS_UTILS_AM);
        // 1907-08
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_BANKS_INSUR, FIRM_1907_BANKS_INSUR_AM, DIRE_1907_BANKS_INSUR_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_BANKS_MANUF, FIRM_1907_BANKS_MANUF_AM, DIRE_1907_BANKS_MANUF_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_BANKS_UTILS, FIRM_1907_BANKS_UTILS_AM, DIRE_1907_BANKS_UTILS_AM);
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1907_COMPLETE, FIRM_1907_COMPLETE_AM, DIRE_1907_COMPLETE_AM);
        // 1909-10
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1909_BANKS_UTILS, FIRM_1909_BANKS_UTILS_AM, DIRE_1909_BANKS_UTILS_AM);
        // 1911-12
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1911_BANKS_UTILS, FIRM_1911_BANKS_UTILS_AM, DIRE_1911_BANKS_UTILS_AM);
        // 1913-14
        //writeFirmAndDirectorAdjMatricesFromSourcePath(SRC_1913_BANKS_UTILS, FIRM_1913_BANKS_UTILS_AM, DIRE_1913_BANKS_UTILS_AM);

        // Write aliases
        // 1906
        //writeAliasesToExcelFromSourcePath(SRC_1906_BANKS, ALIASES_1906_BANKS);
        //writeAliasesToExcelFromSourcePath(SRC_1906_BANKS_UTILS, ALIASES_1906_BANKS_UTILS);
        // 1907-08
        //writeAliasesToExcelFromSourcePath(SRC_1907_BANKS_UTILS, ALIASES_1907_BANKS_UTILS);
        //writeAliasesToExcelFromSourcePath(SRC_1907_COMPLETE, ALIASES_1907_COMPLETE);
        // 1909-10
        //writeAliasesToExcelFromSourcePath(SRC_1909_BANKS_UTILS, ALIASES_1909_BANKS_UTILS);
        // 1911-12
        //writeAliasesToExcelFromSourcePath(SRC_1911_BANKS_UTILS, ALIASES_1911_BANKS_UTILS);
        // 1913-14
        //writeAliasesToExcelFromSourcePath(SRC_1913_BANKS_UTILS, ALIASES_1913_BANKS_UTILS);
        // 1940 CSV
        //writeAliasesToExcelFromSourcePath(SRC_1940_DATA, ALIASES_1940_DATA);
    }
}

