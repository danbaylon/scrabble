package com.palo.it.scrabblecalculator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palo.it.model.entity.Entry;
import com.palo.it.repository.CalculatorRepository;
import com.palo.it.service.dto.EntryDto;
import com.palo.it.service.mapper.EntryMapper;
import com.palo.it.web.rest.CalculatorResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(CalculatorResource.class)
@Slf4j
class ScrabbleCalculatorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CalculatorRepository calculatorRepository;

	@Autowired
	private EntryMapper entryMapper;

	@Test
	void whenValidEntry_thenShouldSaveSuccessfully() throws Exception {
		EntryDto dto = EntryDto.builder().score(18).word("EXCITING").build();

		mockMvc.perform(post("/api/v1/entry")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk());

		Entry entry = calculatorRepository.findByWord("EXCITING");
		assertThat(entry.getScore()).isEqualTo(18);
	}


	@Test
	void whenNotValidEntryWithNumeric_thenShouldThrowException() throws Exception {
		EntryDto dto = EntryDto.builder().score(18).word("EXCITING1").build();

		mockMvc.perform(post("/api/v1/entry")
							.contentType("application/json")
							.content(objectMapper.writeValueAsString(dto)))
					.andExpect(status().isBadRequest());
	}

	@Test
	void whenNotValidEntryWithWrongScoreValue_thenShouldThrowException() throws Exception {
		Exception exception = assertThrows(NestedServletException.class, () -> {
			EntryDto dto = EntryDto.builder().score(9).word("EXCITING").build();

			mockMvc.perform(post("/api/v1/entry")
							.contentType("application/json")
							.content(objectMapper.writeValueAsString(dto)))
					.andExpect(status().isOk());
		});

		String expectedMessage = "This is not a valid entry";
		String actualMessage = exception.getMessage();

		assert actualMessage != null;
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void whenNoRecords_thenShouldReturnEmpty() throws Exception {
		calculatorRepository.deleteAll(); //clear
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/v1/entries/top-scores")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));

	}

	@Test
	void whenRecordsExist_thenShouldReturnSortedRecords() throws Exception {
		calculatorRepository.deleteAll(); //clear
		calculatorRepository.save(entryMapper.toEntity(EntryDto.builder().score(18).word("EXCITING").build()));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
						.get("/api/v1/entries/top-scores")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		List<EntryDto> entryDtos = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
		assertEquals(1, entryDtos.size());
	}

	@Test
	void whenTooManyRecordsExist_thenShouldReturnTop10SortedRecords() throws Exception {
		calculatorRepository.deleteAll();
		this.setUpData();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
						.get("/api/v1/entries/top-scores")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		List<EntryDto> entryDtos = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
		assertEquals(10, entryDtos.size());
		assertEquals(12, entryDtos.get(0).getScore());
	}

	@AfterTestClass
	public void tearDown() {
		calculatorRepository.deleteAll();
	}
	private void setUpData() {
		calculatorRepository.saveAll(
				List.of(
						entryMapper.toEntity(EntryDto.builder().score(5).word("DOG").build()),
						entryMapper.toEntity(EntryDto.builder().score(5).word("CAT").build()),
						entryMapper.toEntity(EntryDto.builder().score(3).word("RAT").build()),
						entryMapper.toEntity(EntryDto.builder().score(3).word("TOP").build()),
						entryMapper.toEntity(EntryDto.builder().score(10).word("BOTTOM").build()),
						entryMapper.toEntity(EntryDto.builder().score(4).word("UP").build()),
						entryMapper.toEntity(EntryDto.builder().score(8).word("DOWN").build()),
						entryMapper.toEntity(EntryDto.builder().score(7).word("LEFT").build()),
						entryMapper.toEntity(EntryDto.builder().score(9).word("RIGHT").build()),
						entryMapper.toEntity(EntryDto.builder().score(7).word("STRONG").build()),
						entryMapper.toEntity(EntryDto.builder().score(12).word("WEAK").build())
				));
	}
}
