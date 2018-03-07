package com.gregspitz.springflashcardserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import com.gregspitz.springflashcardserver.model.Flashcard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlashcardController.class, secure = false)
public class FlashcardControllerTest {

    private static final Flashcard FLASHCARD_1 = new Flashcard("0", "Front", "Back");
    private static final Flashcard FLASHCARD_2 = new Flashcard("1", "Front2", "Back2");
    private static final List<Flashcard> EXPECTED_FLASHCARDS = Arrays.asList(FLASHCARD_1, FLASHCARD_2);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final TypeFactory TYPE_FACTORY = OBJECT_MAPPER.getTypeFactory();

    @Autowired
    private MockMvc mockMvc;

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
        // TODO: fill this in
    }
}
