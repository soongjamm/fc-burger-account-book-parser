package com.king.burger.accounting;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class AccountDay {

    @Id
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Breakdown> breakdowns = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n내역\n");
        sb.append("결산일: " + (date == null ? "입력되지 않았음" :date.toString()) + "\n");
        for (Breakdown breakdown : breakdowns) {
            sb.append(breakdown + "\n");
        }
        return sb.toString();
    }

    public void addBreakdown(Breakdown breakdown) {
        this.breakdowns.add(breakdown);
    }
}
