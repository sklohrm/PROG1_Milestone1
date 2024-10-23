import io.IOHandler;
import managers.MIPSInstructionManager;
import parsing.MIPSParser;

import java.util.List;

public class MainController {

    public static void run(String arg) {
        if (arg.contains(".asm")) {
            convertFile(arg);
        } else {
            convertInstruction(arg);
        }
    }

    private static void convertInstruction(String arg) {
        String[] input = MIPSParser.parseInstruction(arg);
        System.out.println(MIPSInstructionManager.getInstruction(input[0]).toHex(input));
    }

    private static void convertFile(String arg) {
        MIPSParser parser = new MIPSParser();

        List<String> input = IOHandler.readFromFile(arg);

        parser.parseASMFile(input);

//        System.out.println("Data");
//        for (String line : parser.data) {
//            System.out.println(line);
//        }
//        System.out.println("Text");
//        for (String line : parser.text) {
//            System.out.println(line);
//        }

        IOHandler.writeDataFile(parser.data);
        IOHandler.writeTextFile(parser.text);
    }

}
