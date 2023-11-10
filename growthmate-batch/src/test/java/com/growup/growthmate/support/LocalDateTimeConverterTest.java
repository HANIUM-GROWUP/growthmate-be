package com.growup.growthmate.support;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateTimeConverterTest {


    @ParameterizedTest
    @ValueSource(strings = {"1998.01.30", "1998. 01. 30", "2000 .02 .01", "1998. 01. 30."})
    void 올바른_형식인지_검증한다(String value) {
        // when
        boolean actual = LocalDateTimeConverter.isConvertable(value);

        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1998.0130", "1998 01 30", "2000 02 .01"})
    void 올바르지_않은_형식인지_검증한다(String value) {
        // when
        boolean actual = LocalDateTimeConverter.isConvertable(value);

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1998.01.30", "1998. 01. 30", "1998 .01 .30"})
    void convert(String value) {
        // when
        LocalDateTime actual = LocalDateTimeConverter.convert(value);

        // then
        assertThat(actual).isEqualTo(LocalDateTime.of(1998, 1, 30, 0, 0));
    }
}
