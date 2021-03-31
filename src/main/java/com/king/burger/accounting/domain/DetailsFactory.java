package com.king.burger.accounting.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DetailsFactory {

    public final static String CURRENCY_REGEX_DETAILS = "(,?\\d?\\d?\\d)?(,?\\d?\\d\\d){1,}+원?";

    public static List<Details> create(List<String> lines) {
        return lines.stream()
                .map(x -> stringToDetail(x))
                .collect(Collectors.toList())
                ;
    }

    private static Details stringToDetail(String line) {
        Details details;
        Details.Type type = getDetailsType(line);
        String amountStringWithComma = getAmountStringWithComma(line);
        String amountStringWithoutComma = amountStringWithComma.replace(",", "");
        String[] split = line.replace(type.getCharacter(), "").trim().split(amountStringWithComma);
        String content = split[0].trim();
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(amountStringWithoutComma));

        if (split.length == 2) {
            String memo = split[1].trim();
            details = new Details(type, content, amount, memo);
        } else {
            details = new Details(type, content, amount);
        }
        return details;
    }

    private static Details.Type getDetailsType(String line) {
        if (line.startsWith(Details.Type.SPENDING.getCharacter())) {
            return Details.Type.SPENDING;
        } else {
            return Details.Type.INCOME;
        }
    }

    private static String getAmountStringWithComma(String line) {
        Pattern pattern = Pattern.compile(CURRENCY_REGEX_DETAILS);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "0";
    }

}
