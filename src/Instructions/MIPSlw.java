package Instructions;

import io.IOHandler;
import utils.RegisterUtil;

public class MIPSlw implements MIPSInstruction {

    private final int OPCODE = 35;

    private int base;
    private int rt;
    private int offset;

    public MIPSlw(String rt, String offset) {
        String[] offsetBase = IOHandler.splitOffsetBase(offset);
        this.rt = RegisterUtil.toDecimal(rt);
        this.offset = Integer.decode(offsetBase[0]);
        this.base = RegisterUtil.toDecimal(offsetBase[1]);
    }

    @Override
    public String toHex() {

        int inst = 0;

        inst |= (offset & 0xFFFF);
        inst |= (rt & 0x1F) << 16;
        inst |= (base & 0x1F) << 21;
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}