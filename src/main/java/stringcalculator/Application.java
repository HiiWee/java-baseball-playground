package stringcalculator;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        System.out.print("수식을 입력하세요: ");
        int result = calculator.inputFormula(scanner.nextLine());
        System.out.printf("계산결과 : %d\n", result);
    }
}
