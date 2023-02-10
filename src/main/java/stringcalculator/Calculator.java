package stringcalculator;

import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    private final Stack<Character> operators = new Stack<>();
    private final Stack<Integer> operands = new Stack<>();

    public int calculate(final String formula) {
        String[] formulaElements = formula.split(" ");
        validate(formulaElements);
        for (int i = 0; i < formulaElements.length; i++) {

        }
        return 0;
    }

    private void validate(final String[] formulaElements) {
        boolean result = Arrays.stream(formulaElements)
                .allMatch(element -> isNumber(element) || isOperator(element));
        if (!result) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMULA);
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
