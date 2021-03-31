package com.king.burger.accounting.domain;

import java.util.List;
import java.util.stream.Collectors;

public class DetailsFactory {

    public static List<Details> create(List<String> lines) {
        return lines.stream()
                .map(x -> stringToDetail(x.trim()))
                .collect(Collectors.toList())
                ;
    }

    private static Details stringToDetail(String line) {
        String firstCharacter = String.valueOf(line.charAt(0));
        if (firstCharacter.startsWith(Spending.SIGN)) {
            return new Spending(line);
        }
        return new Income(line);
    }
}
