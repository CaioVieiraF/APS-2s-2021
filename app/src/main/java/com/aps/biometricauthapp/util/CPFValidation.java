package com.aps.biometricauthapp.util;

public class CPFValidation {

    public boolean validateCPF(String cpf) {
        int lastDigit = Integer.parseInt(""+cpf.charAt(10));
        int beforeLastDigit = Integer.parseInt(""+cpf.charAt(9));

        return validateSize(cpf) || validateRepetition(cpf) || validateDigit(cpf,beforeLastDigit, false) || validateDigit(cpf,lastDigit, true);
    }

    public static boolean validateSize(String cpf) {
        return cpf.length() != 11;
    }

    public static boolean validateRepetition(String cpf) {
        char val = cpf.charAt(0);
        boolean result = true;
        for (char i: cpf.toCharArray()) {
            if (i != val){
                break;
            } else {
                result = false;
            }
        }
        return result;
    }

    public static boolean validateDigit(String cpf, int digit, boolean last) {
        int valueOne = 0;
        int inter = last ? 11 : 10;
        for(int i = inter, j = 0; i>= 2; i--, j++){
            valueOne += i * cpf.charAt(j);
        }

        int valueTwo = (valueOne * 10) % 11;
        int valueThree = valueTwo;
        if (valueTwo >= 10) {
            valueThree = 0;
        }
        return valueThree == digit;
    }
}
