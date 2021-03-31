package com.king.burger.accounting.domain;

import java.math.BigDecimal;

public class Balance {

    private BigDecimal amount;

    public static Balance ZERO = Balance.wons(0);

    public Balance(long amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    Balance(BigDecimal amount) {
        this.amount = amount;
    }

    public static Balance wons(long amount) {
        return new Balance(amount);
    }

    public Balance plus(Balance addend) {
        return new Balance(this.amount.add(addend.amount));
    }

    @Override
    public String toString() {
        return "잔액 : " + amount.toBigInteger() + "원\n";
    }
}
