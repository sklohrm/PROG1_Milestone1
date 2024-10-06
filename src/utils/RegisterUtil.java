package utils;

import constants.Constants;

public class RegisterUtil {

    public static int toDecimal(String register) {
        for (int i = 0; i < Constants.registers.length; i++) {
            if (register.equals(Constants.registers[i])) {
                System.out.println(i);
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid register: " + register);
    }

}
