package Instructions;

import utils.RegisterUtil;

public class MIPSlui implements MIPSInstruction {

    private final int OPCODE = 15;

    @Override
    public String toHex(String[] instruction) {

        int rt = RegisterUtil.toDecimal(instruction[1]);
        int immediate = Integer.decode(instruction[2]);

        int inst = 0;

        inst |= (immediate & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}