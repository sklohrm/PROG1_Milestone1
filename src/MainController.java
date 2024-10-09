import io.IOHandler;

public class MainController {

    public static void run(String arg) {
        if (arg.contains(".asm")) {
            convertFile(arg);
        } else {
            convertInstruction(arg);
        }
    }

    private static void convertInstruction(String arg) {
        String[] input = IOHandler.cleanInput(arg);
        System.out.println(MIPSInstructionManager.getInstruction(input[0]).toHex(input));
    }

    private static void convertFile(String arg) {

    }

}
