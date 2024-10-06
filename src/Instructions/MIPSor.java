package Instructions;

import utils.RegisterUtil;

public class MIPSor implements MIPSInstruction {

    private final int OPCODE = 0;
    private final int FNCODE = 37;

    private int rd;
    private int rs;
    private int rt;

    public MIPSor(String rd, String rs, String rt) {
        this.rd = RegisterUtil.toDecimal(rd);
        this.rs = RegisterUtil.toDecimal(rs);
        this.rt = RegisterUtil.toDecimal(rt);
    }

    @Override
    public String toHex() {

        int inst = 0;

        inst |= (FNCODE & 0x3F);
        inst |= (rd & 0x1F) << 11;
        inst |= (rt & 0x1F) << 16;
        inst |= (rs & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}