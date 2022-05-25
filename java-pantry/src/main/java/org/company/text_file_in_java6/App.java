package org.company.text_file_in_java6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        final String fileName = "data/test.txt";

        List<String> list = new App().readFileInJava6(fileName);

        for (String s:list) {
            System.out.println(s);
        }
    }

    private List<String> readFileInJava6(String fileName) {
        List<String> result = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(fileName).getFile()));   // here buffered-reader/file-reader opened
            try {
                String line;
                while ((line = br.readLine()) != null){
                    result.add(line);
                }
            } finally {
                br.close(); // closes both buffered-reader and file-reader too
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
