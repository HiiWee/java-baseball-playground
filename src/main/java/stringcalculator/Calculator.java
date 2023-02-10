package stringcalculator;

import java.util.Arrays;

public class Calculator {
    public int calculate(final String formula) {
        String[] formulaElements = formula.split(" ");
        validate(formulaElements);

        return 0;
    }

    private void validate(final String[] formulaElements) {
        boolean result = Arrays.stream(formulaElements)
                .allMatch(element -> isNumber(element) || isOperator(element));
        if (!result) {
            throw new IllegalArgumentException("[ERROR] 수식을 정확하게 입력해야 합니다. (정수, 및 연산자(+, -, *, /");
        }
    }

    private boolean isOperator(final String element) {
        return element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/");
    }

    private boolean isNumber(final String element) {
        try {
            Integer.parseInt(element);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
