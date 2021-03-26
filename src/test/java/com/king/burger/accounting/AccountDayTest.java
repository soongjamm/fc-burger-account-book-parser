package com.king.burger.accounting;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AccountDayTest {

    @Test
    public void 결산일_파싱_테스트() {
        AccountDay day1 = new AccountDay(new String[]{"=====3/24 82,000===="});
        assertEquals("2020-03-24", day1.getDate().toString());
        assertEquals(82000, day1.getBalance().intValue());

        AccountDay day2 = new AccountDay(new String[]{"=====3/27 -38,000===="});
        assertEquals("2020-03-27", day2.getDate().toString());
        assertEquals(-38000, day2.getBalance().intValue());

        AccountDay day3 = new AccountDay(new String[]{"=====12/23 -100원===="});
        assertEquals("2020-12-23", day3.getDate().toString());
        assertEquals(-100, day3.getBalance().intValue());

        assertThrows(java.time.DateTimeException.class, () -> new AccountDay(new String[]{"=====13/23 -100원===="}));
    }

    @Test
    public void 출력() {
        AccountDay day = new AccountDay(new String[]{"======5/23 16,000원=====",
                "+ 광재, 민우 60,000",
                "+ 지수 30,000",
                "- 물 20,000"});
        System.out.println(day.toString());
    }

    @Test
    public void matcherTest() {
        String str = "- 음료비 5,000,000 4/5";
        String CURRENCY_REGEX = "(,?\\d?\\d?\\d){1,}+";
        Pattern pattern = Pattern.compile(CURRENCY_REGEX);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            System.out.println("found:" + matcher.group(0) +":");
        } else {
            System.out.println("no");
        }

    }
}