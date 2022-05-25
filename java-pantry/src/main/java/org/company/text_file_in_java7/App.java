package org.company.text_file_in_java7;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(final String[] args) {
        final String fileName = "data/test.txt";
        final App app = new App();

        System.out.println("Using try with resources:");
        List<String> list = app.readFileInJava7(fileName);

        for (String s : list) {
            System.out.println(s);
        }

        list = app.readFileInJava7_2(fileName);
        System.out.println("\nUsing Files:");
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * Using try with resources
     *
     * @param fileName
     * @return
     */
    private List<String> readFileInJava7(final String fileName) {
        List<String> result = new LinkedList();

        // try with resources
        try (Reader fileReader = new FileReader(getClass().getClassLoader().getResource(fileName).getFile());
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return result;
    }

    /**
     * Using Files class
     *
     * @param fileName
     * @return
     */
    private List<String> readFileInJava7_2(final String fileName) {
        try {
            return Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(fileName).getFile()));
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return Collections.emptyList();
    }
}
