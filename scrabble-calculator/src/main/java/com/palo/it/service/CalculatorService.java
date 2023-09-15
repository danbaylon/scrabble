package com.palo.it.service;

import com.palo.it.service.dto.EntryDto;

import java.util.List;

public interface CalculatorService {

    List<EntryDto> getTopEntries();

    EntryDto save(EntryDto dto);
}
