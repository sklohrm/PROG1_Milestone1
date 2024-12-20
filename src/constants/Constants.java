package constants;

import java.util.Map;

public class Constants {

    public static final int TEXT_SEG_START = 0x00400000;

    public static final int DATA_SEG_START = 0x10010000;

    public static final String[] REGISTERS = {
            "$zero",
            "$at",
            "$v0", "$v1",
            "$a0", "$a1", "$a2", "$a3",
            "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7",
            "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7",
            "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"
    };

    public static final String[] INSTRUCTIONS = {
            "add", "addiu", "and", "andi", "beq", "bne", "j", "lui",
            "lw", "or", "ori", "slt", "sub", "sw", "syscall"
    };

    public static final String[] PSEUDOINSTRUCTIONS = {
        "li", "la", "blt", "move"
    };

    public static final String[] VARIABLE_TYPES = {
        ".byte", ".half", ".word", ".dword", ".float", ".double", ".asciiz", ".space"
    };

    public static final Map<String, String> BACKSLASH_CONVENTIONS = Map.of(
            "a", "07",
            "b", "08",
            "f", "0c",
            "n", "0a",
            "r", "0d",
            "t", "09",
            "\\", "5c",
            "\"", "22",
            "'", "27"
    );

    public enum LineType {
        DATA, DECLARATION, INSTRUCTION, LABEL, PSEUDOINSTRUCTION, TEXT, COMMENT
    }



//            Masking
//            6 bits: 0x3F (or 63 in decimal)
//            5 bits: 0x1F (or 31 in decimal)
//            16 bits: 0xFFFF (or 65535 in decimal)
//            26 bits: 0x3FFFFFF (or 67108863 in decimal)

    //Convention Meaning
    //\a Alert (0x07)
    //\b Backspace (0x08)
    //\f Form feed (0x0c)
    //\n Newline (0x0a)
    //\r Carriage return (0x0d)
    //\t horizontal tab (0x09)
    //\v Vertical feed (0x0b)
    //\\ Backslash (0x5c)
    //\" Quotation mark (0x22)
    //\’ Single quote (0x27)
    //\000 Character whose octal value is 000.
    //\Xnn Character whose hexadecimal value is nn

}
