package com.king.burger.accounting.domain;


import java.util.List;

public class AccountDayFactory {
    public static AccountDay create(List<String> lines) {
        List<String> detailsLines = lines.subList(0, lines.size() - 1);
        String accountDayLine = lines.get(lines.size() - 1);
        List<Details> details = DetailsFactory.create(detailsLines);

        AccountDay accountDay = new AccountDay(accountDayLine, details);
        return accountDay;
    }
}
