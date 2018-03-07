package com.gregspitz.springflashcardserver.data;

import com.gregspitz.springflashcardserver.model.Flashcard;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.gregspitz.springflashcardserver.TestData.EXPECTED_FLASHCARDS;
import static com.gregspitz.springflashcardserver.TestData.FLASHCARD_1;
import static com.gregspitz.springflashcardserver.TestData.FLASHCARD_2;
import static org.junit.Assert.assertEquals;

public class FlashcardRepositoryTest {

    private FlashcardRepository repository;

    @Before
    public void setup() {
        repository = new FlashcardRepository();
    }

    @Test
    public void canAddFlashcardsIndividuallyAndRetrieveFlashcards() {
        repository.addFlashcard(FLASHCARD_1);
        repository.addFlashcard(FLASHCARD_2);
        List<Flashcard> flashcards = repository.getFlashcards();
        assertEquals(EXPECTED_FLASHCARDS, flashcards);
    }

    @Test
    public void canAddListOfFlashcardsAndRetrieveFlashcards() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        List<Flashcard> flashcards = repository.getFlashcards();
        assertEquals(EXPECTED_FLASHCARDS, flashcards);
    }

    @Test
    public void wontAddNullFlashcardIndividually() {
        repository.addFlashcard(null);
        List<Flashcard> flashcards = repository.getFlashcards();
        assertEquals(0, flashcards.size());
    }

    @Test
    public void wontAddNullFlashcardByGroup() {
        List<Flashcard> nullList = Arrays.asList(FLASHCARD_1, null);
        repository.addFlashcards(nullList);
        List<Flashcard> flashcards = repository.getFlashcards();
        assertEquals(1, flashcards.size());
    }

    @Test
    public void canRetrieveFlashcardById() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        Flashcard retrievedFlashcard = repository.getFlashcardById(FLASHCARD_1.getId());
        assertEquals(FLASHCARD_1, retrievedFlashcard);
    }

    @Test
    public void canDeleteFlashcard() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        repository.deleteFlashcard(FLASHCARD_1);
        List<Flashcard> flashcards = repository.getFlashcards();
        assertEquals(1, flashcards.size());
    }

    @Test
    public void canDeleteFlashcardById() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        repository.deleteFlashcard(FLASHCARD_1.getId());
        List<Flashcard> flashcards = repository.getFlashcards();
        assertEquals(1, flashcards.size());
    }

    @Test
    public void wontThrowExceptionOnNullFlashcardDeletion() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        Flashcard nullFlashcard = null;
        repository.deleteFlashcard(nullFlashcard);
    }

    @Test
    public void wontThrowExceptionOnNullIdDeletion() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        String nullId = null;
        repository.deleteFlashcard(nullId);
    }

    @Test
    public void getReturnsNullForUnusedId() {
        repository.addFlashcards(EXPECTED_FLASHCARDS);
        Flashcard flashcard = repository.getFlashcardById(null);
        assertEquals(null, flashcard);
    }
}
