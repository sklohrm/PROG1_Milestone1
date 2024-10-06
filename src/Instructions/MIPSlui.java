package Instructions;

import utils.RegisterUtil;

public class MIPSlui implements MIPSInstruction {

    private final int OPCODE = 15;

    private int rt;
    private int immediate;

    public MIPSlui(String rt, String immediate) {
        this.rt = RegisterUtil.toDecimal(rt);
        this.immediate = Integer.decode(immediate);
    }

    @Override
    public String toHex() {

        int inst = 0;

        inst |= (immediate & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}