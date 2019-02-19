package matrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

/**
 * Created by addisonhowe on 12/22/16.
 */
public class CSVAdjacencyMatrixWriter {

    private LatentAdjacencyMatrix adjMatrix;
    private String outputPath;

    public CSVAdjacencyMatrixWriter(LatentAdjacencyMatrix adjMatrix, String outputPath) {
        this.adjMatrix = adjMatrix;
        this.outputPath = outputPath;
    }

    public void writeAdjMatrixToCSV() throws IOException {
        System.out.println("*** Starting write to file: " + outputPath + " ***");
        File file = new File(outputPath);
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        writeHeaderRow(writer);
        writeBodyRows(writer);
        writer.close();
        System.out.println("*** File " + outputPath + " successfully written ***\n");
    }

    private void writeHeaderRow(CSVWriter writer) {
        System.out.println("\t Writing header row");
        String[] header = new String[adjMatrix.size()];
        header[0] = "";
        for (int cellnum = 1; cellnum < adjMatrix.size(); cellnum++) {
            String cellValue = adjMatrix.getName(cellnum);
            header[cellnum] = cellValue;
        }
        writer.writeNext(header);
        System.out.println("\t Header row written successfully");
    }

    private void writeBodyRows(CSVWriter writer) {
        System.out.println("\t Writing body rows");
        for (int r = 1; r < adjMatrix.size(); r++) {
            if (r % 100 == 0) System.out.println("\t\t Writing row " + r + " of " + (adjMatrix.size() - 1));
            String[] row = new String[adjMatrix.size()];
            //first cell should be the name
            String cellValue = adjMatrix.getName(r);
            row[0] = cellValue;
            //fill body cells with count
            for (int c = 1; c < adjMatrix.size(); c++) {
                int cellCount = adjMatrix.getValue(r, c);
                row[c] = Integer.toString(cellCount);
            }
            writer.writeNext(row);
        }
        System.out.println("\t Body rows written successfully");
    }
}
