package com.king.burger.accounting.domain;


import java.util.List;

public class AccountDayFactory {

    public static AccountDay create(List<String> lines) {
        List<Breakdown> details = DetailsFactory.create(lines.subList(0, lines.size() - 1));
        AccountDay accountDay = new AccountDay(lines.get(lines.size()-1), details);
        return accountDay;
    }

}
