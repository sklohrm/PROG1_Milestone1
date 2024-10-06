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
            case "lui" -> new MIPSlui(instruction[1], instruction[2]);
            case "lw" -> new MIPSlw(instruction[1], instruction[2]);
            case "or" -> new MIPSor(instruction[1], instruction[2], instruction[3]);
            case "ori" -> new MIPSori(instruction[1], instruction[2], instruction[3]);
            case "slt" -> new MIPSslt(instruction[1], instruction[2], instruction[3]);
            case "sub" -> new MIPSsub(instruction[1], instruction[2], instruction[3]);
            case "sw" -> new MIPSsw(instruction[1], instruction[2]);
            case "syscall" -> new MIPSsyscall();
            default -> throw new IllegalArgumentException("Unsupported operation: " + instruction[0]);
        };
    }
}
