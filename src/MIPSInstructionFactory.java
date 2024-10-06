import Instructions.*;

public class MIPSInstructionFactory {
    public static MIPSInstruction create(String[] instruction) {
        return switch (instruction[0].toLowerCase()) {
            case "add" -> new MIPSadd(instruction[1], instruction[2], instruction[3]);
            case "addiu" -> new MIPSaddiu(instruction[1], instruction[2], instruction[3]);
            case "and" -> new MIPSand(instruction[1], instruction[2], instruction[3]);
            case "andi" -> new MIPSandi(instruction[1], instruction[2], instruction[3]);
            case "beq" -> new MIPSbeq(instruction[1], instruction[2], instruction[3]);
            case "bne" -> new MIPSbne(instruction[1], instruction[2], instruction[3] );
            case "j" -> new MIPSj(instruction[1]);
            case "lui" -> new MIPSlui();
            case "lw" -> new MIPSlw();
            case "or" -> new MIPSor();
            case "ori" -> new MIPSori();
            case "slt" -> new MIPSslt();
            case "sub" -> new MIPSsub();
            case "sw" -> new MIPSsw();
            default -> throw new IllegalArgumentException("Unsupported operation: " + instruction[0]);
        };
    }
}
