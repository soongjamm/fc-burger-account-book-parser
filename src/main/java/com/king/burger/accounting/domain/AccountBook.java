package com.king.burger.accounting.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class AccountBook {
    private LinkedHashMap<AccountDay, Balance> accountDays;

    public AccountBook() {
        accountDays = new LinkedHashMap<>();
    }

    public void add(AccountDay accountDay) {
        accountDays.put(accountDay, Money.ZERO);
    }

    private void reCalculateBalance() {
        Money accumulate = Money.ZERO;
        for (AccountDay accountDay : accountDays.keySet()) {
            accumulate = accumulate.plus(accountDay.amountSum());
            accountDays.replace(accountDay, accumulate);
        }
    }

    public LinkedHashMap<AccountDay, Balance> getAccountDays() {
        reCalculateBalance();
        return accountDays;
    }

    public void print() {
        for (Map.Entry<AccountDay, Balance> entry : getAccountDays().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
