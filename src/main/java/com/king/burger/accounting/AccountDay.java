package com.king.burger.accounting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
@Entity
public class AccountDay {
    public final static int YEAR_START = 2020;
    public final static String DATE_REGEX = "(1?[0-9])/([1-3]?[0-9])";
    public final static String CURRENCY_REGEX_TITLE = "-?(,?\\d{0,3}?)*원?";
    public final static String CURRENCY_REGEX_BREAKDOWN = "(,?\\d?\\d?\\d)?(,?\\d?\\d\\d){1,}+원?";
    public final static String TITLE_DELIMITER = "=";

    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Breakdown> breakdowns;
    private BigDecimal balance;
    public int yearNow = YEAR_START;

    public AccountDay(String[] breakdowns) {
        this.breakdowns = new ArrayList<>();
        for (String line : breakdowns) {
            if (line.startsWith(TITLE_DELIMITER)) {
                setAccountDayAndBalance(line);
                continue;
            }
            setBreakdown(line);
        }
    }

    private void setBreakdown(String line) {
        Breakdown.Type type = null;
        String[] split;
        String amountStringWithComma = getAmountString(line);
        String amountString = amountStringWithComma.replace(",", "");
        if (line.startsWith(Breakdown.Type.SPENDING.getCharacter())) {
            type = Breakdown.Type.SPENDING;
        } else if (line.startsWith(Breakdown.Type.INCOME.getCharacter())
                || true) {
            type = Breakdown.Type.INCOME;
        }
        split = line.replace(type.getCharacter(), "").trim().split(amountStringWithComma);
        String content = split[0].trim();
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(amountString));
        if (split.length==2) {
            String memo = split[1].trim();
            this.breakdowns.add(new Breakdown(type, content, amount, memo));
        } else {
            this.breakdowns.add(new Breakdown(type, content, amount));
        }
    }

    private String getAmountString(String line) {
        Pattern pattern = Pattern.compile(CURRENCY_REGEX_BREAKDOWN);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(0);
        }
            return "0";
    }

    private void setAccountDayAndBalance(String line) {
        String[] split = line.replace(TITLE_DELIMITER, "").trim().split(" ");
        if (split[0].matches(DATE_REGEX)) { // 시작이 날짜 형식 이면
            String[] md = split[0].split("/");
            int month = Integer.parseInt(md[0]);
            int day = Integer.parseInt(md[1]);
            this.date = LocalDate.of(yearNow, Month.of(month), day);
            split[0] = split[1]; // 중복 if 안만들기 위해 인덱스 0으로 이동 시킴
        }
        String balanceString = split[0].replaceAll("[^-\\d]*", "");
        balance = BigDecimal.valueOf(Long.parseLong(balanceString));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n내역\n");
        sb.append("결산일: " + (date == null ? "입력되지 않았음" :date.toString()) + "\n");
        sb.append("잔액: " + balance.toString() + "\n");
        for (Breakdown breakdown : breakdowns) {
            sb.append(breakdown + "\n");
        }
        return sb.toString();
    }
}
