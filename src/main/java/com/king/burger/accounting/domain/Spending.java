package com.king.burger.accounting.domain;

public class Spending extends Details {
    public Spending(String line) {
        super(line, "-");
    }

    @Override
    public long getAmount() {
        return -super.getAmount();
    }
}
