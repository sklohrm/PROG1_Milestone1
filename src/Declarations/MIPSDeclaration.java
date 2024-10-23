package Declarations;

public abstract class MIPSDeclaration {

    public String label, type, value;

    abstract String[] toHex();
}
