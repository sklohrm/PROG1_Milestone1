package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {
    public static String[] cleanInput(String input) {
        input = input.replaceAll(",", "").toLowerCase();
        input = input.replace("(", " ").replace(")", " ").trim();
        return input.split("#")[0].split("\\s+");
    }

    // public static List<String> readInstructionsFromFile() {}
    // public static void writeInstructionsToFile(List<String>) {}

    public static List<String[]> readTestData(String inputFile) {

        List<String[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("../../production/PROG1_Milestone1/" + inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(cleanInput(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }


}
