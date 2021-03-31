package com.king.burger.accounting.domain;

public class Spending extends Details {
    public static String SIGN = "-";
    public Spending(String line) {
        super(line, SIGN);
    }

    @Override
    public long getAmount() {
        return -super.getAmount();
    }
}
