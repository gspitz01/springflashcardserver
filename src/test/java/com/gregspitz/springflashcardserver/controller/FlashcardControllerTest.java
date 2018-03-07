package com.gregspitz.springflashcardserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import com.gregspitz.springflashcardserver.di.AppConfig;
import com.gregspitz.springflashcardserver.model.Flashcard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlashcardController.class, secure = false)
public class FlashcardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlashcardRepository mockRepository;


    @Test
    public void getFlashcardsRequest_respondsWithListOfFlashcards() throws Exception {
        Flashcard flashcard1 = new Flashcard("0", "Front", "Back");
        Flashcard flashcard2 = new Flashcard("1", "Front2", "Back2");
        List<Flashcard> expectedFlashcards = Arrays.asList(flashcard1, flashcard2);
        when(mockRepository.getFlashcards()).thenReturn(expectedFlashcards);

        MvcResult result = mockMvc.perform(get("/flashcards"))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Flashcard> actualFlashcards = objectMapper.readValue(
                responseJson, typeFactory.constructCollectionType(List.class, Flashcard.class));

        assertEquals(expectedFlashcards, actualFlashcards);
    }
}
