package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    @DisplayName("\"1,2\"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트")
    void splitTest_MoreThanTwoElement() {
        // given
        String numbersWithComma = "1,2";

        // when
        String[] splitNumbers = numbersWithComma.split(",");

        // then
        Assertions.assertAll(() -> {
            assertThat(splitNumbers).containsExactly("1", "2");
            assertThat(splitNumbers).contains("2", "1");
        });
    }

    @Test
    @DisplayName("\"1\"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트")
    void splitTest_OneElement() {
        // given
        String number = "1";

        // when
        String[] splitNumber = number.split(",");

        // then
        Assertions.assertAll(() -> {
            assertThat(splitNumber).containsExactly("1");
            assertThat(splitNumber).contains("1");
        });
    }

    @Test
    @DisplayName("\"(1,2)\" 에서 substring()을 활용해 ()을 제거하고 \"1,2\"를 반환")
    void substring() {
        // given
        String numbersWithBracket = "(1,2)";

        // when
        int start = numbersWithBracket.indexOf("1");
        int end = numbersWithBracket.indexOf(")");
        String numbers = numbersWithBracket.substring(start, end);

        // then
        assertThat(numbers).isEqualTo("1,2");
    }

    @ParameterizedTest
    @CsvSource({"0,a", "1,b", "2,c"})
    @DisplayName("charAt 메소드를 통해 특정 위치의 문자 가져오기")
    void charAt(int index, char actual) {
        // given
        String input = "abc";

        // when
        char extractedByInput = input.charAt(index);

        // then
        assertThat(extractedByInput).isEqualTo(actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3 ,4})
    @DisplayName("charAt 메소드 실행시 인덱스 벗어나는 예외 테스트")
    void charAt_failWithException(int index) {
        // given
        String input = "abc";

        // when
        // then
        assertThatThrownBy(() -> input.charAt(index)).isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: " + index);
    }
}
