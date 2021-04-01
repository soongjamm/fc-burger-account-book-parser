package com.king.burger.accounting.domain;

import java.util.LinkedList;

public class AccountBook {
    private LinkedList<AccountDay> accountDays;

    public AccountBook() {
        accountDays = new LinkedList<>();
    }

    public void add(AccountDay accountDay) {
        accountDays.add(accountDay);
    }

    public LinkedList<AccountDay> getAccountDays() {
        return accountDays;
    }

    public AccountDay getLast() {
        return accountDays.peekLast();
    }
}
