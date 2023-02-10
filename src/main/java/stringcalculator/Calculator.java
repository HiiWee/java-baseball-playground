package stringcalculator;

import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    private static final int MINIMUM_FORMULA_LENGTH = 3;

    private final Stack<String> operators = new Stack<>();
    private final Stack<Integer> operands = new Stack<>();

    public int inputFormula(final String formula) {
        String[] formulaElements = formula.split(" ");
        validate(formulaElements);
        return calculateElements(formulaElements);
    }

    private int calculateElements(final String[] formulaElements) {
        for (String element : formulaElements) {
            if (isNumber(element)) {
                operands.push(Integer.parseInt(element));
                calculateUnitFormula();
                continue;
            }
            operators.push(element);
        }
        return operands.pop();
    }

    private void calculateUnitFormula() {
        if (operands.size() < 2) {
            return;
        }
        operands.push(calculate(operands.pop(), operands.pop(), operators.pop()));

    }

    private int calculate(final int rightOperand, final int leftOperand, final String operator) {
        if (operator.equals("+")) {
            return leftOperand + rightOperand;
        }
        if (operator.equals("-")) {
            return leftOperand - rightOperand;
        }
        if (operator.equals("*")) {
            return leftOperand * rightOperand;
        }
        return leftOperand / rightOperand;
    }

    private void validate(final String[] formulaElements) {
        boolean elementCheck = Arrays.stream(formulaElements)
                .allMatch(element -> isNumber(element)
                        || isOperator(element));
        boolean positionCheck = checkPositionOfElement(formulaElements);

        if (!(elementCheck && positionCheck && formulaElements.length >= MINIMUM_FORMULA_LENGTH)) {
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
