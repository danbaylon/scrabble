package com.palo.it.scrabblecalculator;

import com.palo.it.model.entity.Entry;
import com.palo.it.repository.CalculatorRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ScrabbleApplicationRepositoryTests {

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Test
    @Sql("/scripts/insert.sql")
    public void whenInitialized_thenValidateExisting() {
        Entry entry = calculatorRepository.findByWord("EXCITING");
        assertThat(entry).isNotNull();
    }
}
