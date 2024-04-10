package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PeriodTest {
    public static final Period APRIL = new Period(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30));

    @DisplayName("기간은")
    @Nested
    class Describe_constructor {

        @DisplayName("시작일과 종료일을 갖는다.")
        @Test
        void create() {
            assertThatCode(() -> new Period(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 2)))
                    .doesNotThrowAnyException();
        }

        @DisplayName("종료일 이후의 시작일을 가질 수 없다.")
        @Test
        void start_after_end() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Period(LocalDate.of(2024, 4, 2), LocalDate.of(2024, 4, 1)));
        }
    }

    @DisplayName("isAfterStartDate는 조회 대상 일자가 기간의 시작일자 이상인지를 확인한다.")
    @Test
    void is_after_start_date() {
        assertThat(new Period(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 2)).isAfterStartDate(LocalDate.of(2024, 5, 1)))
                .isTrue();

    }
}
