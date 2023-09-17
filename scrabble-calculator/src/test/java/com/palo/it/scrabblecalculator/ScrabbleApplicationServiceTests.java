package com.palo.it.scrabblecalculator;

import com.palo.it.model.entity.Entry;
import com.palo.it.model.exception.ApplicationException;
import com.palo.it.repository.CalculatorRepository;
import com.palo.it.service.dto.EntryDto;
import com.palo.it.service.impl.CalculatorServiceImpl;
import com.palo.it.service.mapper.EntryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScrabbleApplicationServiceTests {

    @Mock
    private CalculatorRepository calculatorRepository;

    @Mock
    private EntryMapper entryMapper;

    @InjectMocks
    private CalculatorServiceImpl calculatorService;

    private Entry entry;
    private EntryDto entryDto;


    @BeforeEach
    public void setup() {
        entry = new Entry();
        entry.setWord("EXCITING");
        entry.setScore(18);

        entryDto = EntryDto.builder().word("exciting").score(18).build();
    }

    @Test
    public void givenEntryObject_whenSaveEntry_thenReturnObject() {
        when(entryMapper.toEntity(entryDto)).thenReturn(entry);
        when(calculatorRepository.save(entry)).thenReturn(entry);
        when(entryMapper.toDto(entry)).thenReturn(entryDto);

        EntryDto response = calculatorService.save(entryDto);

        assertThat(response).isNotNull();
        assertThat(response.getWord()).isEqualTo(entry.getWord());

    }

    @Test
    public void givenEntryObject_whenSaveEntryMismatchScore_thenThrowsException() {

        entryDto = EntryDto.builder().word("exciting").score(9).build();

        assertThrows(ApplicationException.class, () -> {
            calculatorService.save(entryDto);
        });

        verify(calculatorRepository, never()).save(any(Entry.class));
    }

    @Test
    public void givenEntries_whenGetTop10_thenReturnEntries() {
        when(calculatorRepository.findAll()).thenReturn(List.of(entry));

        List<EntryDto> entryDtos = calculatorService.getTopEntries();
        assertThat(entryDtos).isNotNull();
        assertThat(entryDtos.size()).isEqualTo(1);
    }

}
