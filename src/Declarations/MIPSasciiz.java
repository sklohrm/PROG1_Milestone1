package Declarations;

public class MIPSasciiz extends MIPSDeclaration{

    public MIPSasciiz(String[] declaration) {
        //TODO Check that declaration array is correct length
        this.label = declaration[0];
        this.type = declaration[1];
        this.value = declaration[2];
    }

    @Override
    public String[] toHex() {
        String[] output = new String[value.length()];
        return output;
    }

}
