package com.palo.it.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.palo.it.service.validator.ValidWord;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntryDto {

    private Long id;

    @ValidWord
    private String word;
    private Integer score;

    public String getWord() {
        return word.toUpperCase();
    }
}
