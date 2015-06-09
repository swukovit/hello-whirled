package files;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.Assert.*;


public class TopNWordsTest {

    @Test
    public void testFindTopNWords() throws Exception {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("D:\\GitHubClones\\hello-whirled\\examples\\test\\src\\files\\TopNWordsSrc.txt")));

            TopNWords tnw = new TopNWords();
            String[] topWords = tnw.findTopNWords(s,3);
            System.out.println(topWords[0]);
            System.out.println(topWords[1]);
            System.out.println(topWords[2]);
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    @Test
    public void testCountWords() throws Exception {

    }

    @Test
    public void testCreateHashMap() throws Exception {

    }

    @Test
    public void testFindTopNWords1() throws Exception {

    }
}
