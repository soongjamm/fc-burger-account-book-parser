package com.king.burger.accounting.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountDay {

    public final static String DELIMITER = "=";

    private LocalDate date;
    private List<Breakdown> breakdowns;

    public AccountDay(String line, List<Breakdown> details) {
        this.date = parseDate(line);
        this.breakdowns = details;
    }

    private LocalDate parseDate(String line) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/M/d");
        String[] parts = line.replace(DELIMITER, "").trim().split(" ");
        return DateParser.parse(pattern, parts[0]);
    }

    @Override
    public String toString() {
        return "AccountDay{" +
                "date=" + date +
                ", breakdowns=" + breakdowns +
                '}';
    }
}
