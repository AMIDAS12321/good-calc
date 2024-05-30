package calcRA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GoodCalc {
    static Scanner s = new Scanner(System.in);
    static int number1, number2,number3, number4;
    static char operation;
    static int result1;
    static int result2;

    public static void main(String[] args) {
        System.out.println("введите выражение одной строкой, арабскими или римскими");
        String userInput = s.nextLine();
        if (!(userInput.matches("[IVXLCM+-/* ]+") || userInput.matches("[12345678910+-/* ]+"))) {
            throw new ArithmeticException("Оба числа должны быть введены в одинаковом формате (арабские или римские цифры). А так же введеные не верные математические операции");

        }
        char[] under_char = new char[10];
        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);
            if (under_char[i] == '-') {
                operation = '-';
            }
            if (under_char[i] == '+') {
                operation = '+';
            }
            if (under_char[i] == '*') {
                operation = '*';
            }
            if (under_char[i] == '/') {
                operation = '/';
            }
        }
        String under_charString = String.valueOf(under_char);
        String[] blok = under_charString.split("[+-/*]");
        String a = blok[0];
        String b = blok[1];
        String b2 = b.trim();
        number1 = romanToNumber(a);
        number2 = romanToNumber(b2);
        if (number1 < 0 && number2 < 0) {
            number3 = Integer.parseInt(a);
            number4 = Integer.parseInt(b2);
            result2 = calculated(number3, number4, operation);
        } else {
            result1 = calculated(number1, number2, operation);
        }
        
        if (result1==-1000 || result2==-1000 ) {
            
        } else {
            
            if (number1 < 0 && number2 < 0) {
                System.out.println("ваш ответ в арабских цифрвх: " + number3 + " " + operation + " " + number4 + " = " + result2);
            } else {
                if (result1==0) {
                    throw new ArithmeticException("ответ в римских цифрах не может ровняться 0");
                } else {
                    String resultRoman = convertNumToRoman(result1);
                    System.out.println("ваш ответ в римских цифрах: " + a + " " + operation + " " + b2 + " = " + resultRoman);
                }
            }
        }
        
        
            
            
        
    }

    private static String convertNumToRoman(int numArabian) {
        String[] roman = { "O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
                "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII",
                "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String sc = roman[numArabian];
        return sc;
    }

    /**
     *
     * @param roman
     * @return
     */
    private static int romanToNumber(String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
                default:
                    return -1;
            }
        } catch (InputMismatchException e ) {
            throw new InputMismatchException("Неверный формат данных");
        } 
    }

    public static int calculated(int num1, int num2, char op) {
        int result=-1000;
        switch (op) {
        case '+':
            if (num1 != 0 && num2 != 0) {
            result = num1 + num2;
            } else {
            throw new ArithmeticException("Нельзя нельзя использовать ноль при вводе");
            }
        break;
        case '-':
            if (num1 != 0 &&num2 != 0) {
            result = num1 - num2;
            } else {
            throw new ArithmeticException("Нельзя нельзя использовать ноль при вводе");
            }
        break;
        case '*':
            if (num1 != 0 && num2 != 0) {
            result = num1 * num2;
            } else {
            throw new ArithmeticException("Нельзя нельзя использовать ноль при вводе");
            }
        break;
        case '/':
            if (num1 != 0 && num2 != 0) {
                result = num1 / num2;
            } else {
            throw new ArithmeticException("Нельзя делить числа с 0");
            }
            break;
        default:
            throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
       }

}
