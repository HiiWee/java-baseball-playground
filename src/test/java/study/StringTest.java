package study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
