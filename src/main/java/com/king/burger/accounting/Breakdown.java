package com.king.burger.accounting;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public abstract class Breakdown {
    private LocalDate date;
    private BigDecimal amount;
    private String content;
    private String memo;
}
