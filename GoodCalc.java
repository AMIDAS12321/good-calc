package calcRA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GoodCalc {
    static Scanner s = new Scanner(System.in);
    static int number1, number2,number3, number4;
    static char operation;
    static String g;
    static String result;
    static int result1;
    static int result3;
    static int result2;

    enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10), 
    XL(40), L(50), XC(90), C(100), 
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
          .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
          .collect(Collectors.toList());
    }
}

    public static void main(String[] args) {
        System.out.println("введите выражение одной строкой, арабскими или римскими");
        String userInput1 = s.nextLine();
        String userInput2 = userInput1.trim();
        String userInput = userInput2.replaceAll(" ", "");
        
        
        if (!(userInput.matches("[IVXLCM+-/* ]+") || userInput.matches("[12345678910+-/* ]+"))) {
            throw new ArithmeticException("Оба числа должны быть введены в одинаковом формате (арабские или римские цифры). А так же введеные не верные математические операции");

        }
        char[] under_char = new char[100];
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
        String g=calc(under_charString);
        System.out.println(g);
        
            
            
        
    }

    private static String convertNumToRoman(int numArabian) {
        try {
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
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Ответ в римских цифрах не может быть меньше 0");
        }
    }

       
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result3 = 0;
    
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
    
        int i = 0;
    
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result3 += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
    
        if (romanNumeral.length() > 0) {
            return -1;
        }
    
        return result3;
    }


    public static String calc(String input) {
        
        String[] blok = input.split("[+-/*]");
        try {
            if (blok.length > 2) {
                throw new StringTooLongException();
                
            }
        } catch (StringTooLongException e) {
            throw new ArithmeticException("вы ввели больше 2 чисел");
            
        }
        String a = blok[0];
        String b = blok[1];
        String b2 = b.trim();
        number1 = romanToArabic(a);
        number2 = romanToArabic(b2);
        if (number1 > 10 || number2 > 10) {
            throw new ArithmeticException("вы ввели больше X или меньше 1");
        }
        if (number1 < 0 && number2 < 0) {
            number3 = Integer.parseInt(a);
            number4 = Integer.parseInt(b2);
            if (number3 < 0 || number4 < 0 && number3 > 10 || number4 > 10) {
                throw new ArithmeticException("вы ввели больше 10 или меньше 0");
            }
            result1 = calculated(number3, number4, operation);
        } else {
            result1 = calculated(number1, number2, operation);
        }
        
        if (result1==-1000 || result2==-1000 ) {
            
        } else {
            
            if (number1 < 0 && number2 < 0) {
                result="ваш ответ в арабских цифрвх: " + number3 + " " + operation + " " + number4 + " = " + result1;
            } else {
                if (result1==0) {
                    throw new ArithmeticException("ответ в римских цифрах не может ровняться 0");
                } else {
                    String resultRoman = convertNumToRoman(result1);
                    result="ваш ответ в римских цифрах: " + a + " " + operation + " " + b2 + " = " + resultRoman;
                }
            }
        }
        return result;
    }
    public static int calculated(int num1, int num2, char op) {
        int result4=-1000;
        switch (op) {
        case '+':
            if (num1 != 0 && num2 != 0) {
            result4 = num1 + num2;
            } else {
            throw new ArithmeticException("Нельзя нельзя использовать ноль при вводе");
            }
        break;
        case '-':
            if (num1 != 0 &&num2 != 0) {
            result4 = num1 - num2;
            } else {
            throw new ArithmeticException("Нельзя нельзя использовать ноль при вводе");
            }
        break;
        case '*':
            if (num1 != 0 && num2 != 0) {
            result4 = num1 * num2;
            } else {
            throw new ArithmeticException("Нельзя нельзя использовать ноль при вводе");
            }
        break;
        case '/':
            if (num1 != 0 && num2 != 0) {
                result4 = num1 / num2;
            } else {
            throw new ArithmeticException("Нельзя делить числа с 0");
            }
            break;
        default:
            throw new IllegalArgumentException("Не верный знак операции");
        }
        return result4;
       }
}
class StringTooLongException extends Exception {
    public StringTooLongException() {
        super("вы ввели больше 2 чисел");
    }
}
