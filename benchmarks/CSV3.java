package edu.rice.pliny.apitrans.examples;

import com.opencsv.CSVWriter;
import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;

public class CSV3 {
    String filename = "code_completion/src/test/resources/apitrans/csv3/created.csv";

    @Test
    public void write_csv() throws IOException {
        String[] entries = new String[]{"1", "2", "3", "4"};

        FileWriter fw = new FileWriter(filename);
        CSVWriter writer = new CSVWriter(fw);
        writer.writeNext(entries);
        writer.close();
    }

    @Test
    public void write_cs2() throws IOException {
        String[] entries = new String[]{"1", "2", "3", "4"};
        FileWriter fw = new FileWriter(filename);
        for(int i = 0; i < entries.length; ++i) {
            if(i > 0) {
                fw.write(",");
            }
            fw.write(entries[i]);
        }
        fw.close();
    }

    /*
    @Test
    public void read_mat() throws ParseException, IOException {
        int[][] mat = new int[ROW][COL];
        File f = new File(filename);
        FileReader reader = new FileReader(f);
        CSVReader csv_reader = new CSVReader(reader);
        for(int i = 0; i < ROW; ++i) {
            String[] fields = csv_reader.readNext();
            for(int j = 0; j < COL; ++j) {
                mat[i][j] = Integer.parseInt(fields[j]);
            }
        }
        assertArrayEquals(new int[]{1, 2, 3, 4}, mat[0]);
        assertArrayEquals(new int[]{2, 3, 4, 5}, mat[1]);
        assertArrayEquals(new int[]{3, 4, 5, 6}, mat[2]);
    }

    @Test
    public void read_mat2() throws ParseException, IOException {
        int[][] mat = new int[ROW][COL];
        File f = new File(filename);
        Scanner scanner = new Scanner(f);
        for(int i = 0; i < ROW; ++i) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            for(int j = 0; j < COL; ++j) {
                mat[i][j] = Integer.parseInt(fields[j]);
            }
        }
        assertArrayEquals(new int[]{1, 2, 3, 4}, mat[0]);
        assertArrayEquals(new int[]{2, 3, 4, 5}, mat[1]);
        assertArrayEquals(new int[]{3, 4, 5, 6}, mat[2]);
    }

    @Test
    public void read_mat3() throws ParseException, IOException {
        int[][] mat = new int[ROW][COL];
        File f = new File(filename);
        BufferedReader scanner = new BufferedReader(new FileReader(f));
        for(int i = 0; i < ROW; ++i) {
            String line = scanner.readLine();
            String[] fields = line.split(",");
            for(int j = 0; j < COL; ++j) {
                mat[i][j] = Integer.parseInt(fields[j]);
            }
        }
        assertArrayEquals(new int[]{1, 2, 3, 4}, mat[0]);
        assertArrayEquals(new int[]{2, 3, 4, 5}, mat[1]);
        assertArrayEquals(new int[]{3, 4, 5, 6}, mat[2]);
    }
    */
}
