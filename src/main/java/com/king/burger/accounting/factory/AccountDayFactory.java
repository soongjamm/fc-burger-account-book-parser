package com.king.burger.accounting.factory;


import com.king.burger.accounting.domain.AccountDay;

import java.util.List;

public class AccountDayFactory {
    public static AccountDay create(List<String> lines, AccountDay last) {
        List<String> detailsLines = lines.subList(0, lines.size() - 1);
        String accountDayLine = lines.get(lines.size() - 1);
        AccountDay accountDay = new AccountDay(accountDayLine, detailsLines, last);
        return accountDay;
    }
}
