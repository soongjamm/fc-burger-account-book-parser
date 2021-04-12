package com.king.burger.accounting.factory;

import com.king.burger.accounting.domain.Details;
import com.king.burger.accounting.domain.Income;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DetailsFactoryTest {

    @Test
    public void createDetails() {
        List<String> list = List.of("+ 숭잼 25,000");
        Details details = DetailsFactory.create(list).get(0);
        Assertions.assertThat(details.getAmount().toString()).isEqualTo("25000원");
        Assertions.assertThat(details).isInstanceOf(Income.class);
    }


}