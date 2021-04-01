package com.king.burger.accounting.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountDay {

    public final static String DELIMITER = "=";

    private LocalDate date;
    private List<Details> detailsList;
    private AccountDay previous;
    private Money balance;

    public AccountDay(String line, List<Details> detailsList, AccountDay last) {
        this.date = parseDate(line);
        this.detailsList = detailsList;
        this.previous = last;
        this.balance = calculateBalance();
    }

    private LocalDate parseDate(String line) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/M/d");
        String[] parts = line.replace(DELIMITER, "").trim().split(" ");
        return DateParser.parse(pattern, parts[0]);
    }


    private Money calculateBalance() {
        if (previous == null) {
            return amountSum();
        } else {
            return previous.balance.plus(amountSum());
        }
    }

    private Money amountSum() {
        Money sum = Money.ZERO;
        for (Details details : detailsList) {
            sum = sum.plus(details.getAmount());
        }
        return sum;
    }
}
