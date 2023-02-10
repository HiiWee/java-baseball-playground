package stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

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
        assertThatNoException()
                .isThrownBy(() -> calculator.inputFormula(formula));
    }

    @ParameterizedTest
    @CsvSource({"1 . 2 - 3", "a + 3 / 4", "22 & 3"})
    void validateFormula_fail(String formula) {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.inputFormula(formula))
                .withMessageContaining(ErrorMessage.INVALID_FORMULA);
    }

    @ParameterizedTest
    @CsvSource({"+ 2 - 3", "1 + 2 - -"})
    void validateFormulaPosition_fail(String formula) {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.inputFormula(formula))
                .withMessageContaining(ErrorMessage.INVALID_FORMULA);
    }

    @ParameterizedTest
    @CsvSource({"1", "2 +"})
    void validateFormulaLength_fail(String formula) {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.inputFormula(formula))
                .withMessageContaining(ErrorMessage.INVALID_FORMULA);
    }

    @ParameterizedTest
    @CsvSource({"1 + 2 / 3,1", "2 + 3 * 5,25", "30 + 1 - 50,-19", "20 / 3 + 20 * 3,78"})
    void inputFormula(String formula, int expectedResult) {
        // when
        int actualResult = calculator.inputFormula(formula);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}