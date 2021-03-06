package com.gregspitz.springflashcardserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import com.gregspitz.springflashcardserver.model.Flashcard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static com.gregspitz.springflashcardserver.TestData.EXPECTED_FLASHCARDS;
import static com.gregspitz.springflashcardserver.TestData.FLASHCARD_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlashcardController.class, secure = false)
public class FlashcardControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final TypeFactory TYPE_FACTORY = OBJECT_MAPPER.getTypeFactory();

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<Flashcard> repositoryFlashcardCaptor;

    @Captor
    private ArgumentCaptor<String> deleteStringIdCaptor;

    @MockBean
    private FlashcardRepository mockRepository;


    @Test
    public void getFlashcardsRequest_respondsWithListOfFlashcards() throws Exception {
        when(mockRepository.getFlashcards()).thenReturn(EXPECTED_FLASHCARDS);

        MvcResult result = mockMvc.perform(get("/flashcards"))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        List<Flashcard> actualFlashcards = OBJECT_MAPPER.readValue(
                responseJson, TYPE_FACTORY.constructCollectionType(List.class, Flashcard.class));

        assertEquals(EXPECTED_FLASHCARDS, actualFlashcards);
    }

    @Test
    public void getFlashcardRequestWithId_respondsWithCorrectFlashcard() throws Exception {
        when(mockRepository.getFlashcardById(FLASHCARD_1.getId())).thenReturn(FLASHCARD_1);

        MvcResult result = mockMvc.perform(get("/flashcard/" + FLASHCARD_1.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        Flashcard actualFlashcard = OBJECT_MAPPER.readValue(responseJson, Flashcard.class);
        assertEquals(FLASHCARD_1, actualFlashcard);
    }

    @Test
    public void postFlashcardRequest_addsFlashcardToRepositoryAndRespondsWithFlashcard() throws Exception {
        Flashcard newFlashcard = new Flashcard("2", "Front", "Back");
        MvcResult result = mockMvc.perform(post("/flashcard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newFlashcard)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify response flashcard
        String responseJson = result.getResponse().getContentAsString();
        Flashcard actualFlashcard = OBJECT_MAPPER.readValue(responseJson, Flashcard.class);
        assertEquals(newFlashcard, actualFlashcard);

        // Verify flashcard added to repository
        verify(mockRepository).addFlashcard(repositoryFlashcardCaptor.capture());
        Flashcard savedFlashcard = repositoryFlashcardCaptor.getValue();
        assertEquals(newFlashcard, savedFlashcard);
    }

    @Test
    public void postFlashcardRequestWithNoFlashcardData_respondsWithError400() throws Exception {
        Flashcard nullFlashcard = null;
        mockMvc.perform(post("/flashcard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(nullFlashcard)))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void deleteFlashcardRequest_removesFlashcardFromRepository() throws Exception {
        mockMvc.perform(delete("/flashcard/" + FLASHCARD_1.getId()))
                .andExpect(status().isOk());

        verify(mockRepository).deleteFlashcard(deleteStringIdCaptor.capture());
        assertEquals(FLASHCARD_1.getId(), deleteStringIdCaptor.getValue());
    }
}
