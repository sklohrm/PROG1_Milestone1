public class IOHandler {
    public static String[] cleanInput(String input) {
        input = input.replaceAll(",", "");
        return input.split("#")[0].split("\\s+");
    }
}
