package Instructions;

import utils.RegisterUtil;

public class MIPSj implements MIPSInstruction {

    private final int OPCODE = 2;

    private int instr_index;

    public MIPSj(String instr_index) {
        this.instr_index = Integer.decode(instr_index);
    }

    @Override
    public String toHex() {

        int inst = 0;

        inst |= (instr_index & 0x3FFFFFF);
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}