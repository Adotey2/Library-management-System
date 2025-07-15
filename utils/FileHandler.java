package utils;

import java.io.*;
import java.util.*;

public class FileHandler {
    public static List<String> readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) lines.add(line);
        br.close();
        return lines;
    }

    public static void writeFile(String filename, List<String> lines) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (String line : lines) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}