package alias;

import objects.Director;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/28/16.
 */
public class ExcelAliasWriter {

    private ArrayList<Director> directorList;
    private String outputPath;

    public ExcelAliasWriter(ArrayList<Director> directorList, String outputPath) {
        this.directorList = directorList;
        this.outputPath = outputPath;
    }

    public void writeAliasesToExcel() throws IOException {
        System.out.println("Writing aliases to file " + outputPath);
        FileOutputStream out = new FileOutputStream(outputPath);
        SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sheet = wb.createSheet();
        writeRows(sheet);
        wb.write(out);
        out.close();
        System.out.println("Aliases successfully written to " + outputPath);
    }

    private void writeRows(Sheet sheet) {
        ArrayList<Director> filteredDirectors = getDirectorsWithMultipleNames(directorList);
        for (int r = 0; r < filteredDirectors.size(); r++) {
            Row row = sheet.createRow(r);
            Director director = filteredDirectors.get(r);
            for (int c = 0; c < director.getAliases().size(); c++) {
                Cell cell = row.createCell(c);
                String alias = director.getAliases().get(c);
                cell.setCellValue(alias);
            }
        }
    }

    private ArrayList<Director> getDirectorsWithMultipleNames(ArrayList<Director> directorList) {
        ArrayList<Director> filteredDirectors = new ArrayList<Director>();
        for (Director d : directorList) {
            if (d.getAliases().size() > 1) {
                filteredDirectors.add(d);
            }
        }
        return filteredDirectors;
    }
}
