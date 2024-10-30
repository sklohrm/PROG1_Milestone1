package Declarations;

public class MIPSasciiz implements MIPSDeclaration{

    @Override
    public String toHex(String[] declaration) {
        StringBuilder output = new StringBuilder();
        int count = 3;
        for (int i = 0; i < declaration[2].length(); ++i) {
            output.append(Integer.toHexString((int) declaration[2].charAt(i)));
        }
        return "";
    }
}
