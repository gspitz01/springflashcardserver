package com.gregspitz.springflashcardserver.data;

import com.gregspitz.springflashcardserver.model.Flashcard;

import java.util.*;

public class FlashcardRepository implements FlashcardDataSource {

    private Map<String, Flashcard> flashcards;

    public FlashcardRepository() {
        flashcards = new HashMap<>();
    }

    @Override
    public List<Flashcard> getFlashcards() {
        return new ArrayList<>(flashcards.values());
    }

    @Override
    public Flashcard getFlashcardById(String id) {
        return flashcards.get(id);
    }

    @Override
    public void deleteFlashcard(String id) {
        flashcards.remove(id);
    }

    @Override
    public void deleteFlashcard(Flashcard flashcard) {
        if (flashcard != null) {
            deleteFlashcard(flashcard.getId());
        }
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        if (flashcard != null) {
            flashcards.put(flashcard.getId(), flashcard);
        }
    }

    @Override
    public void addFlashcards(List<Flashcard> flashcards) {
        for (Flashcard flashcard : flashcards) {
            if (flashcard != null) {
                this.flashcards.put(flashcard.getId(), flashcard);
            }
        }
    }
}
