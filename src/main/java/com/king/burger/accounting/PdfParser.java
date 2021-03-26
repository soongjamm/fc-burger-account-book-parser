package com.king.burger.accounting;

import java.util.ArrayList;

public class PdfParser {
    public static void parse(String entire) {
        if (!entire.startsWith("축구공금")) {
            throw new IllegalArgumentException("축구공금 파일 아님");
        }

        String[] split = entire.split("\n");
        ArrayList<String> stringsForOneDay = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            stringsForOneDay.add(split[i]);
            if (split[i].startsWith(AccountDay.TITLE_DELIMITER)) {
                AccountDay accountDay = new AccountDay(stringsForOneDay.toArray(new String[0]));
                stringsForOneDay.clear();
                System.out.println(accountDay.toString());
            }
        }
    }
}
