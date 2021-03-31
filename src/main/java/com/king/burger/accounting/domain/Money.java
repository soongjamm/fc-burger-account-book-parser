package com.king.burger.accounting.domain;

import java.math.BigDecimal;

public class Money extends Balance {

    private BigDecimal amount;

    public static Money ZERO = Money.wons(0);

    public Money(long amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(long amount) {
        return new Money(amount);
    }

    public Money plus(Money addend) {
        return new Money(this.amount.add(addend.amount));
    }

    @Override
    public String toString() {
        return "잔액 : " + amount.toBigInteger() + "원\n";
    }
}
