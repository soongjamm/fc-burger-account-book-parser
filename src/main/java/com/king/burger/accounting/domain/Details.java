package com.king.burger.accounting.domain;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Details {

    public final static String CURRENCY_REGEX_DETAILS = "(,?\\d?\\d?\\d)?(,?\\d?\\d\\d){1,}+원?";

    protected String content;
    protected Money amount;
    protected String memo;
    protected String sign;

    public Details(String line, String sign) {
        this.sign = sign;
        setDetails(line);
    }

    @SneakyThrows
    public void setDetails(String line) {
        NumberFormat numberFormat = DecimalFormat.getInstance();
        String amount = extractAmount(line);
        String[] split = line.replace(sign, "").split(amount);

        this.content = split[0].trim();
        this.amount = Money.wons(numberFormat.parse(amount).longValue());
        this.memo = split.length > 1 ? split[1] : "";
    }

    private String extractAmount(String line) {
        Pattern pattern = Pattern.compile(CURRENCY_REGEX_DETAILS);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "0";
    }

    public Money getAmount() {
        return amount;
    }
}
