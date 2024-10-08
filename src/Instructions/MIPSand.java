package Instructions;

import utils.RegisterUtil;

public class MIPSand implements MIPSInstruction {

    private final int OPCODE = 0;
    private final int FNCODE = 36;

    @Override
    public String toHex(String[] instruction) {

        int rd = RegisterUtil.toDecimal(instruction[1]);
        int rs = RegisterUtil.toDecimal(instruction[2]);
        int rt = RegisterUtil.toDecimal(instruction[3]);

        int inst = 0;

        inst |= (FNCODE & 0x3F);
        inst |= (rd & 0x1F) << 11;
        inst |= (rt & 0x1F) << 16;
        inst |= (rs & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}
