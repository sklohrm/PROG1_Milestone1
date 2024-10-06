import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] input = IOHandler.cleanInput(args[0]);
        System.out.println(MIPSInstructionFactory.create(input).toHex());
    }

}
