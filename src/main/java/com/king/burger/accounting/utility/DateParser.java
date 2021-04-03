package com.king.burger.accounting.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    private final static String SHORT_DATE_REGEX = "([0-1]?[0-9])/([0-3]?[0-9])";
    private final static String FULL_DATE_REGEX = "((20)?[0-9][0-9]/[0-1]?[0-9])/([0-3]?[0-9])";

    public static LocalDate parse(DateTimeFormatter pattern, String date) {
        if (date.matches(SHORT_DATE_REGEX)) {
            date = 2020 + "/" + date;
        } else if (!date.matches(FULL_DATE_REGEX)) {
            return null;
        }
        return LocalDate.parse(date, pattern);
    }

}
