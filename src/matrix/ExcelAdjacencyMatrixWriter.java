package matrix;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by addisonhowe on 12/22/16.
 */
public class ExcelAdjacencyMatrixWriter {

    private AdjacencyMatrix adjMatrix;
    String outputPath;

    public ExcelAdjacencyMatrixWriter(AdjacencyMatrix adjMatrix, String outputPath) {
        this.adjMatrix = adjMatrix;
        this.outputPath = outputPath;
    }

    public void writeAdjMatrixToExcel() throws IOException {
        //create a new file
        FileOutputStream out = new FileOutputStream(outputPath);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        //create header row
        writeHeaderRow(sheet);
        //create body rows
        writeLowerTriangularBodyRows(sheet);
        //completeLowerTriangularMatrix(sheet);
        //close file
        wb.write(out);
        out.close();
    }

    private void writeHeaderRow(Sheet sheet) {
        System.out.println("Writing header row");
        Row row = sheet.createRow(0);
        for (int cellnum = 1; cellnum < adjMatrix.size(); cellnum++) {
            String cellValue = adjMatrix.getName(cellnum);
            Cell cell = row.createCell(cellnum);
            cell.setCellValue(cellValue);
        }
        System.out.println("Header row written successfully");
    }

    private void writeLowerTriangularBodyRows(Sheet sheet) {
        System.out.println("Writing lower triangular body rows");
        for (int r = 1; r < adjMatrix.size(); r++) {
            if (r % 20 == 0) System.out.println("Writing row " + r + " of " + (adjMatrix.size() - 1));
            Row row = sheet.createRow(r);
            //first cell should be the name
            String cellValue = adjMatrix.getName(r);
            Cell cell = row.createCell(0);
            cell.setCellValue(cellValue);
            //fill body cells with count
            for (int c = 1; c < r; c++) {
                int cellCount = adjMatrix.getValue(r, c);
                cell = row.createCell(c);
                cell.setCellValue(cellCount);
            }
        }
        System.out.println("Lower triangular body rows written successfully");
    }

    private void completeLowerTriangularMatrix(Sheet sheet) {
        System.out.println("Completing lower triangular matrix");
        for (int r = 1; r < adjMatrix.size(); r++) {
            if (r % 20 == 0) System.out.println("Writing row " + r + " of " + (adjMatrix.size() - 1));
            Row activeRow = sheet.getRow(r);
            for (int c = r; c < adjMatrix.size(); c++) {
                Cell cell = activeRow.createCell(c);
                int cellValue = (int) sheet.getRow(c).getCell(r).getNumericCellValue();
                cell.setCellValue(cellValue);
            }
        }
        System.out.println("Successfully completed lower triangular matrix");
    }

}
