
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
 * Generates an adjacency matrix based on the associations between directors and banks.
 * Writes this matrix to an excel file along with the names of the banks in column/row 0.
 */

public class ExcelAdjacencyMatrixGenerator {

    private String sourcePath;
    private String bankNodeOutputPath;
    private String directorNodeOutputPath;
    private ArrayList<Bank> banks;
    private ArrayList<Director> directors;

    public ExcelAdjacencyMatrixGenerator(String sourcePath, String bankNodeOP, String directorNodeOP) {
        this.sourcePath = sourcePath;
        this.bankNodeOutputPath = bankNodeOP;
        this.directorNodeOutputPath = directorNodeOP;
        ExcelReader er = new ExcelReader(sourcePath);
        this.banks = er.getBanks();
        this.directors = er.getDirectors();
    }

    public void writeFullBankNodeAdjMatrixToExcel() throws IOException {
        writeBankNodeAdjMatrixToExcel(banks, bankNodeOutputPath);
    }

    private void writeBankNodeAdjMatrixToExcel(ArrayList<Bank> bankList, String outputPath) throws IOException {
        Matrix matrix = generateBankNodeAdjMatrix(bankList);
        TreeSet<Bank> bankSet = new TreeSet<Bank>(bankList);
        // create a new file
        FileOutputStream out = new FileOutputStream(outputPath);
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row;
        Cell cell;
        String cellValue;
        int cellCount;

        //create header row
        row = sheet.createRow(0);
        int cellnum = 1;
        for (Bank b : bankSet) {
            //fill header row with names
            cellValue = b.getName();
            cell = row.createCell(cellnum);
            cell.setCellValue(cellValue);
            cellnum++;
        }
        //Create body rows
        for (int r = 1; r < matrix.getSize(); r++) {
            row = sheet.createRow(r);
            //first cell should be the name
            cellValue = bankSet.pollFirst().getName();
            cell = row.createCell(0);
            cell.setCellValue(cellValue);

            //fill body cells with count
            for (int c = 1; c < matrix.getSize(); c++) {
                cellCount = matrix.get(r, c);
                cell = row.createCell(c);
                cell.setCellValue(cellCount);
            }
        }
        wb.write(out);
        out.close();
    }

    private <I, E> TreeMap getMap(TreeSet<E> treeSet) {
        TreeMap<Integer, E> treeMap = new TreeMap<Integer, E>();
        int i =1;
        for (E e : treeSet) {
            treeMap.put(i, e);
            i++;
        }
        return treeMap;
    }

    private Matrix generateBankNodeAdjMatrix(ArrayList<Bank> bankList) {
        TreeSet<Bank> bankSet = new TreeSet<Bank>(bankList);
        TreeMap<Integer, Bank> bankMap = getMap(bankSet);
        int size = bankList.size() + 1;
        Matrix matrix = new Matrix(size);
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < i; j++) {
                int value = bankMap.get(i).getNumberOfCommonDirectors(bankMap.get(j));
                matrix.put(value, i, j);
                matrix.put(value, j, i);
            }
        }
        return matrix;
    }
}
