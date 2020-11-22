package com.proposta.aceita.crmservice.util;

import com.proposta.aceita.crmservice.exceptions.InterestException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class MaskFormatterUtils {

    private static final String ZIP_CODE_MASK = "#####-###";
    private static final String CPF_MASK = "###.###.###-##";

    public static String toZipCode(String zipCode) {
        try {
            return maskValue(zipCode, ZIP_CODE_MASK);
        } catch (ParseException e) {
            throw new InterestException("Could not save zipCode value. Please try again using a right value");
        }
    }

    public static String toCpf(String cpf) {
        try {
            return maskValue(cpf, CPF_MASK);
        } catch (ParseException e) {
            throw new InterestException("Could not save cpf value. Please try again using a right value");
        }
    }

    private static String maskValue(String value, String mask) throws ParseException {

        final var maskFormatter = new MaskFormatter(mask);

        maskFormatter.setValueContainsLiteralCharacters(false);

        var zipCodeFormatted = maskFormatter.valueToString(removeNonLiteralCharacters(value)).trim();

        if (zipCodeFormatted.endsWith("-")) {
            zipCodeFormatted = zipCodeFormatted.substring(0, zipCodeFormatted.length() - 1);
        }

        return zipCodeFormatted;
    }

    public static String removeNonLiteralCharacters(String value) {
        return value.replaceAll("\\.|,|\\/|\\-", "")
                .replaceAll("\\s+-\\s+|-\\s+|\\s+-", "")
                .replaceAll("-{2,}", "")
                .replaceAll("\\-$|\\.$|'$|&$|\\($|\\)$|\\+$|\\/$", "")
                .replaceAll("'|&|\\(|\\)|\\+", "")
                .replaceAll("\\s+", "");
    }
}
