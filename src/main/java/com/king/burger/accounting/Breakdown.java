package com.king.burger.accounting;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@NoArgsConstructor
@Data
@Entity
public class Breakdown {
    enum Type {
        INCOME("+"), SPENDING("-");
        private String character;

        Type(String character) {
            this.character = character;
        }

        public String getCharacter() {
            return character;
        }
    }

    @Id @GeneratedValue
    private Long id;
    @Enumerated
    private Type type;
    @NotNull
    private String content;
    @NotNull
    private BigDecimal amount;
    private String memo;

    public Breakdown(Type type, String content, BigDecimal amount) {
        this.type = type;
        this.content = content;
        this.amount = amount;
    }

    public Breakdown(Type type, @NotNull String content, @NotNull BigDecimal amount, String memo) {
        this.type = type;
        this.content = content;
        this.amount = amount;
        this.memo = memo;
    }
}
