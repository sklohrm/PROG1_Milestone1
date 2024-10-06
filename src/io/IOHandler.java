package io;

public class IOHandler {
    public static String[] cleanInput(String input) {
        input = input.replaceAll(",", "");
        return input.split("#")[0].split("\\s+");
    }

    public static String[] splitOffsetBase(String input) {
        input = input.replace("(", " ").replace(")", " ").trim();
        return input.split("\\s+");
    }
}
