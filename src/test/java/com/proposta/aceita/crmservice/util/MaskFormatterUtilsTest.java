package com.proposta.aceita.crmservice.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MaskFormatterUtilsTest {

    @Test
    void toZipCode() {
        final var zipCodeWithHyphen = "127234-653";
        final var zipCodeWithoutHyphen = "127234653";

        assertThat(MaskFormatterUtils.toZipCode(zipCodeWithoutHyphen)).isEqualTo(zipCodeWithHyphen);
    }

    @Test
    void toCpf() {
        final var cpfWithDotAndHyphen = "127.234.343-65";
        final var cpfWithoutDotAndHyphen = "12723434365";

        assertThat(MaskFormatterUtils.toCpf(cpfWithoutDotAndHyphen)).isEqualTo(cpfWithDotAndHyphen);
    }

    @Test
    void removeNonLiteralCharactersForZipCode() {
        final var zipCodeWithHyphen = "127234-653";
        final var zipCodeWithHyphen2 = "127-234653";
        final var zipCodeWithHyphen3 = "127-234-653";
        final var zipCodeWithoutHyphen = "127234653";

        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(zipCodeWithHyphen)).isEqualTo(zipCodeWithoutHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(zipCodeWithHyphen2)).isEqualTo(zipCodeWithoutHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(zipCodeWithHyphen3)).isEqualTo(zipCodeWithoutHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(zipCodeWithoutHyphen)).isEqualTo(zipCodeWithoutHyphen);
    }

    @Test
    void removeNonLiteralCharactersForCpf() {
        final var cpfWithDotAndHyphen = "127.234.343-65";
        final var cpfWithDot = "127.234.34365";
        final var cpfWithFirstDot = "127.23434365";
        final var cpfWithSecondDot = "127234.34365";
        final var cpfWithHyphen = "127234343-65";
        final var cpfWithoutDotAndHyphen = "12723434365";

        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(cpfWithDotAndHyphen)).isEqualTo(cpfWithoutDotAndHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(cpfWithDot)).isEqualTo(cpfWithoutDotAndHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(cpfWithFirstDot)).isEqualTo(cpfWithoutDotAndHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(cpfWithSecondDot)).isEqualTo(cpfWithoutDotAndHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(cpfWithHyphen)).isEqualTo(cpfWithoutDotAndHyphen);
        assertThat(MaskFormatterUtils.removeNonLiteralCharacters(cpfWithoutDotAndHyphen)).isEqualTo(cpfWithoutDotAndHyphen);
    }
}