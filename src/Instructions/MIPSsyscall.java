package Instructions;

public class MIPSsyscall implements MIPSInstruction {

    private final int OPCODE = 0;
    private final int FNCODE = 12;

    @Override
    public String toHex(String[] instruction) {

        int inst = 0;

        inst |= (FNCODE & 0x3F);
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}