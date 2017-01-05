package excelmerger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by addisonhowe on 1/5/17.
 */
public class FileMerger {

    public static void mergeFile(String[] fileSrcPaths, String outputPath) throws IOException {
        System.out.println("*** Starting merge to file: " + outputPath + " ***");
        FileOutputStream out = new FileOutputStream(outputPath);
        SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sheet = wb.createSheet();

        writeHeaderRow(sheet);

        int rowCounter = 1;

        for (String srcPath : fileSrcPaths) {
            rowCounter = readWriteFileToSheet(srcPath, sheet, rowCounter);
        }

        wb.write(out);
        out.close();
        System.out.println("*** Merge successful ***\n");
    }

    private static void writeHeaderRow(Sheet sheet) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Firm");
        row.createCell(1).setCellValue("First");
        row.createCell(2).setCellValue("Middle");
        row.createCell(3).setCellValue("Last");
        row.createCell(4).setCellValue("Suffix");
    }

    private static int readWriteFileToSheet(String srcPath, Sheet writeSheet, int rowCounter) throws IOException {
        FileInputStream inputFile = new FileInputStream(new File(srcPath));
        XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
        XSSFSheet readSheet = workbook.getSheetAt(0);
        Iterator<Row> readRowIterator = readSheet.iterator();
        readRowIterator.next(); //ignore header row
        //Read rows and process each
        while (readRowIterator.hasNext()) {
            Row readRow = readRowIterator.next();
            Row writeRow = writeSheet.createRow(rowCounter);
            readWriteRow(readRow, writeRow);
            rowCounter++;
        }
        workbook.close();
        inputFile.close();
        return rowCounter;
    }

    private static void readWriteRow(Row readRow, Row writeRow) {
        for (int i = 0; i < 5; i++) {
            writeRow.createCell(i).setCellValue(getCellValue(readRow, i));
        }
    }

    private static String getCellValue(Row row, int c) {
        return row.getCell(c, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
    }
}
