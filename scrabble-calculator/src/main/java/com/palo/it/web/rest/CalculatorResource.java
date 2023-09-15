package com.palo.it.web.rest;

import com.palo.it.api.ApiEndpoint;
import com.palo.it.api.AppApi;
import com.palo.it.service.CalculatorService;
import com.palo.it.service.dto.EntryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@AppApi
@RequiredArgsConstructor
public class CalculatorResource {

    private final CalculatorService calculatorService;

    @GetMapping(value = ApiEndpoint.CR_GET_ALL_TOP_SCORES)
    public ResponseEntity<List<EntryDto>> getAllTopScores() {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(calculatorService.getTopEntries());
    }

    @PostMapping(value = ApiEndpoint.CR_SAVE_ENTRY)
    public ResponseEntity<EntryDto> save(@Valid @RequestBody EntryDto entryDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(calculatorService.save(entryDto));
    }

}
