package com.gregspitz.springflashcardserver.data;

import com.gregspitz.springflashcardserver.model.Flashcard;

import java.util.Arrays;
import java.util.List;

public class FlashcardRepository implements FlashcardDataSource {

    // TODO: fill this in and test

    @Override
    public List<Flashcard> getFlashcards() {
        return Arrays.asList(new Flashcard("Front", "Back"));
    }

    @Override
    public Flashcard getFlashcardById(String id) {
        return null;
    }

    @Override
    public void deleteFlashcard(String id) {

    }

    @Override
    public void deleteFlashcard(Flashcard flashcard) {

    }

    @Override
    public void addFlashcard(Flashcard flashcard) {

    }

    @Override
    public void addFlashcards(List<Flashcard> flashcards) {

    }
}
