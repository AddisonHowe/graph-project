package excelhandler;

import objects.Firm;
import objects.Director;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by addisonhowe on 12/20/16.
 * Requires the path to an excel file.
 * Excel file is assumed to have String values in the first five columns:
 * FirmName | FirstName | MiddleName | LastName | Suffix
 * Generates a list of the Firms and Directors as defined by the
 * input excel file.
 */

public class ExcelReader {

    private static final int FIRM_COL = 0;
    private static final int FIRST_COL = 1;
    private static final int MIDDLE_COL = 2;
    private static final int LAST_COL = 3;
    private static final int SUFFIX_COL = 4;
    private static final int UID_COL = 5;

    private String excelFilePath;
    private ArrayList<Firm> firmList = new ArrayList<Firm>();
    private ArrayList<Director> directorList = new ArrayList<Director>();

    public ExcelReader(String excelFilePath) throws IOException {
        this.excelFilePath = excelFilePath;
        readExcel();
    }

    public ArrayList<Director> getDirectors() {
        return directorList;
    }

    public ArrayList<Firm> getFirms() {
        return firmList;
    }

    private void readExcel() throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet excelSheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = excelSheet.iterator();
        rowIterator.next(); //ignore header row
        //Read rows and process each using the readRow method
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            readRow(row);
        }
        excelFile.close();
    }

    private void readRow(Row row) {
        //Process the first five cells of the row
        String firmName = getCellValue(row, FIRM_COL);
        String firstName = getCellValue(row, FIRST_COL);
        String middleName = getCellValue(row, MIDDLE_COL);
        String lastName = getCellValue(row, LAST_COL);
        String suffix = getCellValue(row, SUFFIX_COL);
        String uID = getCellValue(row, UID_COL);

        Firm firm = new Firm(firmName);
        //Check if the firm already exists.
        if (firmList.contains(firm)) {
            firm = firmList.get(firmList.indexOf(firm));
        } else {
            firmList.add(firm);
        }

        Director director = new Director(firstName, middleName, lastName, suffix, uID);
        //Check if the director already exists.
        if (directorList.contains(director)) {
            director = directorList.get(directorList.indexOf(director));
            director.addAlias(firstName, middleName, lastName, suffix);
        } else {
            directorList.add(director);
        }
        //Associate the firm and director
        director.addFirm(firm);
        firm.addDirector(director);
    }

    private String getCellValue(Row row, int col) {
        Cell cell = row.getCell(col, Row.CREATE_NULL_AS_BLANK);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}
