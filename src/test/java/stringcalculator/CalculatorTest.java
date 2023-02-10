package stringcalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    static void init() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({"1 + 2 - 3", "1 * 3 / 2", "-1 + 2 * 3"})
    void validateFormula_success(String formula) {
        // then
        Assertions.assertThatNoException()
                .isThrownBy(() -> calculator.calculate(formula));
    }

    @ParameterizedTest
    @CsvSource({"1 . 2 - 3", "a + 3 / 4", "22 & 3"})
    void validateFormula_fail(String formula) {
        // then
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.calculate(formula))
                .withMessageContaining(ErrorMessage.INVALID_FORMULA);

    }
}