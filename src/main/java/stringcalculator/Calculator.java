package stringcalculator;

import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    private final Stack<Character> operators = new Stack<>();
    private final Stack<Integer> operands = new Stack<>();

    public int calculate(final String formula) {
        String[] formulaElements = formula.split(" ");
        validate(formulaElements);

        return 0;
    }

    private void validate(final String[] formulaElements) {
        boolean elementCheck = Arrays.stream(formulaElements)
                .allMatch(element -> isNumber(element) || isOperator(element));
        boolean positionCheck = checkPositionOfElement(formulaElements);
        if (!(elementCheck && positionCheck)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMULA);
        }
    }

    private boolean checkPositionOfElement(final String[] formulaElements) {
        boolean result = true;
        for (int i = 0; i < formulaElements.length; i++) {
            if (i % 2 == 0) {
                result &= isNumber(formulaElements[i]);
                continue;
            }
            result &= isOperator(formulaElements[i]);
        }
        return result;
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
