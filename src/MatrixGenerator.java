
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by addisonhowe on 12/20/16.
 */

public class MatrixGenerator {

    private final String EXCEL_FILE_PATH =
            "/Users/addisonhowe/Documents/1907 Graph Project/Java Component/graph-project/data/1907-08 Directors Complete Table.xlsx";
    private Matrix matrix;
    private int size;
    private TreeSet<Bank> sortedBanks;
    private TreeMap<Integer, Bank> bankMap;

    public MatrixGenerator() {
        ExcelReader er = new ExcelReader(EXCEL_FILE_PATH);
        ArrayList<Bank> banks = er.getBanks();
        ArrayList<Director> directors = er.getDirectors();
        size = banks.size() + 1;
        matrix = new Matrix(size);
        sortedBanks = new TreeSet<Bank>(banks);
        bankMap = new TreeMap<Integer, Bank>();
        int i = 1;
        for (Bank b : sortedBanks) {
            bankMap.put(i, b);
            i++;
        }
        fillMatrix(matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    private void fillMatrix(Matrix matrix) {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < i; j++) {
                int value = getCommonDirectorsForBanks(bankMap.get(i), bankMap.get(j));
                matrix.put(value, i, j);
                matrix.put(value, j, i);
            }
        }
        for (int i = 1; i < size; i++) {
            matrix.put(bankMap.get(i).getSize(), i, i);
        }
    }

    private int getCommonDirectorsForBanks(Bank b1, Bank b2) {
        int total = 0;
        for (Director d1 : b1.getDirectors()) {
            if (b2.getDirectors().contains(d1)) {
                total += 1;
            }
        }
        return total;
    }

    public void matrixToExcel() throws IOException {
        // create a new file
        FileOutputStream out = new FileOutputStream("outputMatrix.xls");
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row;
        Cell cell;
        String cellValue;
        int cellCount;

        row = sheet.createRow(0); //create header row
        for (int cellnum = 1; cellnum < size; cellnum++) { //fill header row with names
            cellValue = bankMap.get(cellnum).getName();
            cell = row.createCell(cellnum);
            cell.setCellValue(cellValue);
        }

        //Create body rows
        for (int rownum = 1; rownum < size; rownum++) {
            row = sheet.createRow(rownum);
            //first cell should be the name
            cellValue = bankMap.get(rownum).getName();
            cell = row.createCell(0);
            cell.setCellValue(cellValue);

            //fill body cells with count
            for (int cellnum = 1; cellnum < size; cellnum++) {
                cellCount = matrix.get(rownum, cellnum);
                cell = row.createCell(cellnum);
                cell.setCellValue(cellCount);
            }
        }
        wb.write(out);
        out.close();
    }
}
