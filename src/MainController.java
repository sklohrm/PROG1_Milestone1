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

        IOHandler.writeDataFile(parser.data, arg.replace(".asm", ""));
        IOHandler.writeTextFile(parser.text, arg.replace(".asm", ""));
    }

}
