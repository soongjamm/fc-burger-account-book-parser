package com.king.burger.accounting.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ToString
@NoArgsConstructor
@Data
@Entity
public class Details {
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
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotNull
    private String content;
    @NotNull
    private BigDecimal amount;
    private String memo;

    public Details(Type type, String content, BigDecimal amount) {
        this.type = type;
        this.content = content;
        this.amount = amount;
    }

    public Details(Type type, @NotNull String content, @NotNull BigDecimal amount, String memo) {
        this.type = type;
        this.content = content;
        this.amount = amount;
        this.memo = memo;
    }
}
