package com.palo.it.service.mapper;

import com.palo.it.model.entity.Entry;
import com.palo.it.service.dto.EntryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntryMapper extends EntityMapper<EntryDto, Entry> {

    EntryDto toDto(Entry entry);

    Entry toEntity(EntryDto entryDto);

}
