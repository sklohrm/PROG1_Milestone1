import io.IOHandler;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Comment these two line and uncomment testSingleInput to run test file.
        String[] input = IOHandler.cleanInput(args[0]);
        System.out.println(MIPSInstructionManager.getInstruction(input[0]).toHex(input));

//        testSingleInput("test_instructions.txt");

    }

    public static void testSingleInput(String inputFile) {

        List<String[]> inputs = IOHandler.readTestData(inputFile);
        int errors = 0;

        for (String[] input : inputs) {

            String answer = input[0];
            String[] instruction = Arrays.copyOfRange(input, 1, input.length);
            String output = MIPSInstructionManager.getInstruction(instruction[0]).toHex(instruction);

            if (!answer.equals(output)) {
                errors++;
                System.out.print("Error: ");
            }

            System.out.println(answer + ": " + output);
        }
        System.out.println("Errors: " + errors);
    }
}
