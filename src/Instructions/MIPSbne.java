package Instructions;

import utils.RegisterUtil;

public class MIPSbne implements MIPSInstruction {

    private final int OPCODE = 5;

    private int rs;
    private int rt;
    private int offset;

    public MIPSbne(String rs, String rt, String offset) {
        this.rs = RegisterUtil.toDecimal(rs);
        this.rt = RegisterUtil.toDecimal(rt);
        this.offset = Integer.decode(offset);
    }

    @Override
    public String toHex() {

        int inst = 0;

        inst |= (offset & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (rs & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}