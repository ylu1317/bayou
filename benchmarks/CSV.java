package edu.rice.pliny.apitrans.examples;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class CSV {
    String filename = "code_completion/src/test/resources/apitrans/csv/foo.csv";

    @Test
    public void read_csv() throws IOException {
        File f = new File(filename);
        FileReader reader = new FileReader(f);
        CSVReader csv_reader = new CSVReader(reader);
        String[] fields = csv_reader.readNext();
        assertArrayEquals(fields, new String[]{"1", "2", "3", "4"});
    }

    @Test
    public void read_csv2() throws IOException {
        File f = new File(filename);
        FileReader fr = new FileReader(f);
        BufferedReader scanner = new BufferedReader(fr);
        String line = scanner.readLine();
        String[] fields = line.split(",");
        assertArrayEquals(fields, new String[]{"1", "2", "3", "4"});
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
