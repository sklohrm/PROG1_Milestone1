package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {

    public static String[] cleanInstructionInput(String input) {
        input = input.replaceAll(",", "").toLowerCase();
        input = input.replace("(", " ").replace(")", " ").trim();
        return input.split("#")[0].split("\\s+");
    }

    public static String[] cleanDeclarationInput(String input) {
        return null;
    }

    public static List<String[]> cleanInstructionInputs(List<String> inputs) {
        List<String[]> output = new ArrayList<>();
        for (String input : inputs) {
            output.add(cleanInstructionInput(input));
        }
        return output;
    }

    public static List<String[]> cleanDeclarationInputs(List<String> inputs) {
        List<String[]> output = new ArrayList<>();
        for (String input : inputs) {
            output.add(cleanDeclarationInput(input));
        }
        return output;
    }

    public static List<List<String>> splitBySection(List<String> input) {
        List<String> data = new ArrayList<>();
        List<String> text = new ArrayList<>();
        output[0] = data;
        output[1] = text;

        boolean textSection = false;

        for (String s : input) {
            if (s.contains(".text")) {
                textSection = true;
                continue;
            }

            if (s.contains(".data")) {
                textSection = false;
                continue;
            }

            if (textSection) {
                text.add(s);
            } else {
                data.add(s);
            }

        }

        return output;
    }

    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("../../production/PROG1_Milestone1/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeTextFile(List<String[]> instructions) {}

    public static void writeDataFile(List<String[]> declarations) {}

    public static List<String[]> readTestData(String inputFile) {

        List<String[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("../../production/PROG1_Milestone1/" + inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(cleanInstructionInput(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }



}
