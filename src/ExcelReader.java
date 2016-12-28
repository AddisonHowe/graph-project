
import objects.Firm;
import objects.Director;
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
        String firmName = getCellValue(row, 0);
        String firstName = getCellValue(row, 1);
        String middleName = getCellValue(row, 2);
        String lastName = getCellValue(row, 3);
        String suffix = getCellValue(row, 4);

        Firm firm = new Firm(firmName);
        //Check if the firm already exists.
        if (firmList.contains(firm)) {
            firm = firmList.get(firmList.indexOf(firm));
        } else {
            firmList.add(firm);
        }

        Director director = new Director(firstName, middleName, lastName, suffix);
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

    private String getCellValue(Row row, int c) {
        if (c == 1 && row.getCell(c, Row.CREATE_NULL_AS_BLANK).getStringCellValue().equals("")) {
            System.out.println("found");
        }
        return row.getCell(c, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
    }
}
