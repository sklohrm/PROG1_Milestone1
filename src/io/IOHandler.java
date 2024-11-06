package io;

import parsing.MIPSParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {

    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader("../../production/PROG1_Milestone1/" + fileName))) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeTextFile(List<String> instructions, String fileName) {
        fileName = fileName + ".text";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : instructions) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeDataFile(List<String> declarations, String fileName) {
        fileName = fileName + ".data";
        System.out.println(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : declarations) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readTestData(String inputFile) {

        List<String[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("../../production/PROG1_Milestone1/" + inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(MIPSParser.parseInstruction(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }



}
