package com.king.burger.accounting.domain;

import com.king.burger.accounting.utility.Money;

public class Spending extends Details {
    public static String SIGN = "-";
    public Spending(String line) {
        super(line, SIGN);
    }

    @Override
    public Money getAmount() {
        return amount.multiply(-1L);
    }
}
