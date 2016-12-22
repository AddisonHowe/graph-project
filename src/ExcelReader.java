
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL;

/**
 * Created by addisonhowe on 12/20/16.
 */

public class ExcelReader {

    private String excelFilePath;
    private ArrayList<String> bankNameList = new ArrayList<String>();
    private ArrayList<Bank> bankList = new ArrayList<Bank>();
    private ArrayList<Director> directorList = new ArrayList<Director>();

    public ExcelReader(String excelFilePath) {
        this.excelFilePath = excelFilePath;
        readExcel();
    }

    public ArrayList<Director> getDirectors() {
        return directorList;
    }

    public ArrayList<Bank> getBanks() {
        return bankList;
    }

    private void readExcel() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet excelSheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = excelSheet.iterator();
            rowIterator.next(); //ignore header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                readRow(row);
            }
            excelFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readRow(Row row) {
        //Iterator<Cell> cellIterator = row.cellIterator();
        //Cell cell = cellIterator.next();
        //String bankName = cell.getStringCellValue();
        //cell = cellIterator.next();
        //String firstName = cell.getStringCellValue();
        //cell = cellIterator.next();
        //String middleName = cell.getStringCellValue();
        //cell = cellIterator.next();
        //String lastName = cell.getStringCellValue();
        //cell = cellIterator.next();
        //String suffix = cell.getStringCellValue();
        String bankName = getCellValue(row, 0);
        String firstName = getCellValue(row, 1);
        String middleName = getCellValue(row, 2);
        String lastName = getCellValue(row, 3);
        String suffix = getCellValue(row, 4);

        Bank bank;
        if (bankNameList.contains(bankName)) {
            bank = new Bank(bankName);
            bank = bankList.get(bankList.indexOf(bank));
        } else {
            bank = new Bank(bankName);
            bankNameList.add(bankName);
            bankList.add(bank);
        }

        Director director = new Director(firstName, middleName, lastName, suffix);
        if (directorList.contains(director)) {
            Director inListDirector = directorList.get(directorList.indexOf(director));
            inListDirector.addBank(bank);
            bank.addDirector(inListDirector);
        } else {
            directorList.add(director);
            director.addBank(bank);
            bank.addDirector(director);
        }
    }

    private String getCellValue(Row row, int i) {
        return row.getCell(i, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
    }
}
