package Instructions;

import utils.RegisterUtil;

public class MIPSsw implements MIPSInstruction {

    private final int OPCODE = 43;

    @Override
    public String toHex(String[] instruction) {

        boolean offsetPresent = instruction.length == 4;

        int rt = RegisterUtil.toDecimal(instruction[1]);

        int offset = 0, base;

        if (offsetPresent) {
            offset = Integer.decode(instruction[2]);
            base = RegisterUtil.toDecimal(instruction[3]);
        } else {
            base = RegisterUtil.toDecimal(instruction[2]);
        }

        int inst = 0;

        inst |= (offset & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (base & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}