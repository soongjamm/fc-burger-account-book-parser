package com.king.burger.accounting;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountDayFactory {

    public final static int YEAR_START = 2020;
    public final static String DATE_REGEX = "(1?[0-9])/([1-3]?[0-9])";
    public final static String CURRENCY_REGEX_TITLE = "-?(,?\\d{0,3}?)*원?";
    public final static String CURRENCY_REGEX_BREAKDOWN = "(,?\\d?\\d?\\d)?(,?\\d?\\d\\d){1,}+원?";
    public final static String TITLE_DELIMITER = "=";
    public static int yearNow = YEAR_START;


    public static AccountDay makeAccountDay(List<String> accountDayByLine) {
        AccountDay accountDay = new AccountDay();
        for (String line : accountDayByLine) {
            if (line.startsWith(TITLE_DELIMITER)) {
                setAccountDateAndBalance(accountDay, line);
                continue;
            }
            setBreakdown(accountDay, line);
        }
        return accountDay;
    }

    private static void setBreakdown(AccountDay accountDay, String line) {
        accountDay.addBreakdown(stringToBreakdown(line));
    }

    private static Breakdown stringToBreakdown(String line) {
        Breakdown.Type type = getBreakdownType(line);
        String amountStringWithComma = getAmountStringWithComma(line);
        String amountStringWithoutComma = amountStringWithComma.replace(",", "");
        String[] split = line.replace(type.getCharacter(), "").trim().split(amountStringWithComma);
        String content = split[0].trim();
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(amountStringWithoutComma));

        if (split.length == 2) {
            String memo = split[1].trim();
            return new Breakdown(type, content, amount, memo);
        } else {
            return new Breakdown(type, content, amount);
        }
    }

    private static Breakdown.Type getBreakdownType(String line) {
        if (line.startsWith(Breakdown.Type.SPENDING.getCharacter())) {
            return Breakdown.Type.SPENDING;
        } else {
            return Breakdown.Type.INCOME;
        }
    }

    private static String getAmountStringWithComma(String line) {
        Pattern pattern = Pattern.compile(CURRENCY_REGEX_BREAKDOWN);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "0";
    }

    private static void setAccountDateAndBalance(AccountDay accountDay, String line) {
        LocalDate date = parseDate(line);
        accountDay.setDate(date);
    }

    private static LocalDate parseDate(String line) {
        String[] split = line.replace(TITLE_DELIMITER, "").trim().split(" ");
        if (split[0].matches(DATE_REGEX)) { // 시작이 날짜 형식 이면
            String[] md = split[0].split("/");
            int month = Integer.parseInt(md[0]);
            int day = Integer.parseInt(md[1]);
            return LocalDate.of(yearNow, Month.of(month), day);
        }
        return null;
    }


}
