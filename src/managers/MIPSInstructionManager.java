package managers;

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
        return MIPSInstructionManager.MIPS_INSTRUCTION_MAP.get(operation);
    }



}
