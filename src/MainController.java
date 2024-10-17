import io.IOHandler;

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
        String[] input = IOHandler.cleanInstructionInput(arg);
        System.out.println(MIPSInstructionManager.getInstruction(input[0]).toHex(input));
    }

    private static void convertFile(String arg) {
        List<String> input = IOHandler.readFromFile(arg);
        List<String>[] splitInput = IOHandler.splitBySection(input);
        List<String[]> cleanedInstructions = IOHandler.cleanInstructionInputs(splitInput[0]);
        List<String[]> cleanedDeclarations = IOHandler.cleanDeclarationInputs(splitInput[1]);
//        IOHandler.writeTextFile(cleanedInstructions);
//        IOHandler.writeTextFile(cleanedDeclarations);
    }

}
