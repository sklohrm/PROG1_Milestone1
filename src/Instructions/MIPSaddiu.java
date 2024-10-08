package Instructions;

import utils.RegisterUtil;

public class MIPSaddiu implements MIPSInstruction {

    private final int OPCODE = 9;

    @Override
    public String toHex(String[] instruction) {

        int rt = RegisterUtil.toDecimal(instruction[1]);
        int rs = RegisterUtil.toDecimal(instruction[2]);
        int immediate = Integer.decode(instruction[3]);

        int inst = 0;

        inst |= (immediate & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (rs & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}
