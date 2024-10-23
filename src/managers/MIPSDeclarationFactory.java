package managers;

import Declarations.MIPSDeclaration;
import Declarations.MIPSasciiz;

import javax.naming.OperationNotSupportedException;

public class MIPSDeclarationFactory {

    // ".byte", ".half", ".word", ".dword", ".float", ".double", ".asciiz", ".space"

    public static MIPSDeclaration create(String[] declaration) {
        return switch (declaration[1]) {
            case ".asciiz" -> new MIPSasciiz(declaration);
            case ".byte", ".half", ".word", ".dword", ".float", ".double", ".space" -> throw new IllegalArgumentException(declaration[1] + " is not yet supported");
            default -> throw new IllegalArgumentException("Unknown type: " + declaration[1]);
        };
    }


}
