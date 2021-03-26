package com.king.burger.accounting;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Getter
public class AccountDay {
    private final static int YEAR_START = 2020;
    private final static String dateRegex = "(1?[0-9])/([1-3]?[0-9])";
    private final static String currencyRegex = "(-?)((,?)\\d{0,3}?)*";

    private long id;
    private LocalDate date;
    private List<Breakdown> breakdowns;
    private BigDecimal balance;

    public AccountDay(String[] breakdowns) {
        for (String line : breakdowns) {
            if (line.startsWith("=")) {
                String[] split = line.replaceAll("=", "").trim().split(" ");
                if (split[0].matches(dateRegex)) { // 날짜 형식 이면
                    String[] md = split[0].split("/");
                    // TODO : int year YEAR_START; 나중에 마지막날을 static 참조해서 바꾸도록 해야함
                    int month = Integer.parseInt(md[0]);
                    int day = Integer.parseInt(md[1]);
                    this.date = LocalDate.of(YEAR_START, Month.of(month), day);

                    split[0] = split[1];
                }

                if (split[0].matches(currencyRegex) || split[0].endsWith("원")){ // 맨 앞이 날짜 형식이 아니면 돈이 온다.
                    balance = BigDecimal.valueOf(Long.parseLong(split[0].replaceAll("[^-\\d]*", "")));
                }
            }
        }
    }


}
