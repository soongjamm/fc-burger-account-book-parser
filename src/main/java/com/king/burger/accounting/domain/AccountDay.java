package com.king.burger.accounting.domain;

import com.king.burger.accounting.factory.DetailsFactory;
import com.king.burger.accounting.utility.DateParser;
import com.king.burger.accounting.utility.Money;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountDay {

    public final static String DELIMITER = "=";

    private LocalDate date;
    private List<Details> detailsList;
    private AccountDay previous;
    private Money balance;

    public AccountDay(String line, List<String> detailsLines, AccountDay last) {
        this.date = parseDate(line);
        this.detailsList = DetailsFactory.create(detailsLines);
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

    @Override
    public String toString() {
        return "AccountDay{" +
                "\n\tdate=" + date +
                ",\n\tdetailsList=" + detailsList +
                ",\n\tprevious=" + (previous!=null) +
                ",\n\tbalance=" + balance +
                '}';
    }
}
