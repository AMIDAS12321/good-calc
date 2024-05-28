package calcRA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GoodCalc {
    static Scanner s = new Scanner(System.in);
    static int number1, number2,number3, number4;
    static char operation;
    static int result;

    public static void main(String[] args) {
        System.out.println("введите выражение одной строкой, арабскими или римскими");
        String userInput = s.nextLine();
        if (!(userInput.matches("[IVXLCM+-/*]+") || userInput.matches("[12345678910+-/*]+"))) {
            System.out.println("Оба числа должны быть введены в одинаковом формате (арабские или римские цифры). А так же введеные не верные математические операции");
            return;
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
        if (number1 < 0 || number2 < 0) {
            result = 0;
        } else {
            result = calculated(number1, number2, operation);
            if (result==0 || result<0) {
                System.out.println("ответ ровняется 0 или или меньше 0");
            } else {
            String resultRoman = convertNumToRoman(result);
            System.out.println("ваш ответ в римских цифрах: " + a + " " + operation + " " + b2 + " = " + resultRoman); }
        }
        number3 = Integer.parseInt(a);
        number4 = Integer.parseInt(b2);
        if (number3 != 0 && number4 != 0 && number3 < 11 && number4 < 11) {
            result = calculated(number3, number4, operation);
            System.out.println(
                    "ваш ответ в арабских цифрвх: " + number3 + " " + operation + " " + number4 + " = " + result);
        } else {
            System.out.println("одно из чисел ровняется 0 или больше 10");
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
        final String s = roman[numArabian];
        return s;
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
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("исключение : " + e);
                    System.out.println("Разрешены только целые ненулевые параметры");
                    break;
                }
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
    

}