package com.king.burger.accounting.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountDay {

    public final static String DELIMITER = "=";

    private LocalDate date;
    private List<Details> detailsList;

    public AccountDay(String line, List<Details> detailsList) {
        this.date = parseDate(line);
        this.detailsList = detailsList;
    }

    private LocalDate parseDate(String line) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/M/d");
        String[] parts = line.replace(DELIMITER, "").trim().split(" ");
        return DateParser.parse(pattern, parts[0]);
    }

    public Money amountSum() {
        return Money.wons((long) detailsList.stream()
                .mapToDouble(details -> details.getAmount())
                .sum())
                ;
    }
}
