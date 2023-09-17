package com.palo.it.service.impl;

import com.palo.it.model.entity.Entry;
import com.palo.it.model.exception.ApplicationException;
import com.palo.it.model.types.Points;
import com.palo.it.repository.CalculatorRepository;
import com.palo.it.service.CalculatorService;
import com.palo.it.service.dto.EntryDto;
import com.palo.it.service.mapper.EntryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private final CalculatorRepository calculatorRepository;

    private final EntryMapper entryMapper;

    @Override
    public List<EntryDto> getTopEntries() {
        log.debug("Get Top 10 Entries");
        return calculatorRepository
                .findAll()
                .stream()
                .map(entryMapper::toDto)
                .sorted(Comparator.comparing(EntryDto::getScore).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public EntryDto save(EntryDto dto) throws ApplicationException {
        if(!isValid(dto)) {
            throw new ApplicationException("This is not a valid entry", new Exception());
        }
        return entryMapper.toDto(calculatorRepository.save(entryMapper.toEntity(dto)));
    }

    private boolean isValid(EntryDto dto) {
        return dto.getScore() == dto.getWord()
                .chars()
                .mapToObj(c -> (char) c)
                .map(Points::getPointsByCharacters)
                .mapToInt(Points::getPoint)
                .sum();
    }

}
