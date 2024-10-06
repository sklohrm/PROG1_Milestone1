package Instructions;

import utils.RegisterUtil;

public class MIPSandi implements MIPSInstruction {

    private final int OPCODE = 12;

    private int rs;
    private int rt;
    private int immediate;

    public MIPSandi(String rt, String rs, String immediate) {
        this.rt = RegisterUtil.toDecimal(rt);
        this.rs = RegisterUtil.toDecimal(rs);
        this.immediate = Integer.decode(immediate);
    }

    @Override
    public String toHex() {

        int inst = 0;

        inst |= (immediate & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (rs & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}