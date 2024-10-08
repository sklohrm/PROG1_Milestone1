package Instructions;

import utils.RegisterUtil;

public class MIPSbne implements MIPSInstruction {

    private final int OPCODE = 5;

    @Override
    public String toHex(String[] instruction) {

        int rs = RegisterUtil.toDecimal(instruction[1]);
        int rt = RegisterUtil.toDecimal(instruction[2]);
        int offset = Integer.decode(instruction[3]);

        int inst = 0;

        inst |= (offset & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (rs & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}