package org.company.text_file_encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class App {
    public static void main(final String[] args) {
        final String fileName = "data/encoding.txt";
        final App app = new App();

        System.out.println("Read file with \"cp1251\" encoding");
        List<String> list = app.usingInputStreamReader(fileName);

        for (String s : list) {
            System.out.println(s);
        }

        // printing charset
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String s:map.keySet()){
            System.out.println(s);
        }

        // using Files to set Charset
        list = app.usingFiles(fileName);

        for (String s:list){
            System.out.println(s);
        }
    }

    /**
     * Using try with resources
     *
     * @param fileName
     * @return
     */
    private List<String> usingInputStreamReader(final String fileName) {
        List<String> result = new LinkedList<>();

        // try with resources
        try (
                InputStream fileInputStream = Files.newInputStream(Paths.get(getClass().getClassLoader().getResource(fileName).getFile()));
                InputStreamReader fileReader = new InputStreamReader( fileInputStream, "cp1251");   // or Charset.forName("windows-1251") or Charset.forName("cp1251");
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

    private List<String> usingFiles(final String fileName) {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).getFile());
            return Files.readAllLines(path, Charset.forName("windows-1251"));
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return Collections.emptyList();
    }
}
