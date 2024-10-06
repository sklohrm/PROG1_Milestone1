package constants;

public class Constants {
    public static String[] registers = {
            "$zero",
            "$at",
            "$v0", "$v1",
            "$a0", "$a1", "$a2", "$a3",
            "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7",
            "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7",
            "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"
    };

//            Masking
//            6 bits: 0x3F (or 63 in decimal)
//            5 bits: 0x1F (or 31 in decimal)
//            16 bits: 0xFFFF (or 65535 in decimal)
//            26 bits: 0x3FFFFFF (or 67108863 in decimal)
}
