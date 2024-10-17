import Instructions.*;

import java.util.HashMap;

public class MIPSInstructionManager {

    private static final HashMap<String, MIPSInstruction> MIPS_INSTRUCTION_MAP = new HashMap<>() {{
        put("add", new MIPSadd());
        put("addiu", new MIPSaddiu());
        put("and", new MIPSand());
        put("andi", new MIPSandi());
        put("beq", new MIPSbeq());
        put("bne", new MIPSbne());
        put("j", new MIPSj());
        put("lui", new MIPSlui());
        put("lw", new MIPSlw());
        put("or", new MIPSor());
        put("ori", new MIPSori());
        put("slt", new MIPSslt());
        put("sub", new MIPSsub());
        put("sw", new MIPSsw());
        put("syscall", new MIPSsyscall());
    }};

    public static MIPSInstruction getInstruction(String operation) {
        return MIPS_INSTRUCTION_MAP.get(operation);
    }

    public static MIPSInstruction create(String[] instruction) {
        return switch (instruction[0].toLowerCase()) {
            case "add" -> new MIPSadd();
            case "addiu" -> new MIPSaddiu();
            case "and" -> new MIPSand();
            case "andi" -> new MIPSandi();
            case "beq" -> new MIPSbeq();
            case "bne" -> new MIPSbne();
            case "j" -> new MIPSj();
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
