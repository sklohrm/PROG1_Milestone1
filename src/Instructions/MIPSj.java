package Instructions;

public class MIPSj implements MIPSInstruction {

    private final int OPCODE = 2;

    @Override
    public String toHex(String[] instruction) {

        int instr_index = Integer.decode(instruction[1]);

        int inst = 0;

        inst |= (instr_index & 0x3FFFFFF);
        inst |= (OPCODE & 0x3F) << 26;

        return String.format("%08x", inst);
    }
}