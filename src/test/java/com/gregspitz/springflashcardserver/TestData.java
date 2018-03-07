package com.gregspitz.springflashcardserver;

import com.gregspitz.springflashcardserver.model.Flashcard;

import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final Flashcard FLASHCARD_1 = new Flashcard("0", "Front", "Back");
    public static final Flashcard FLASHCARD_2 = new Flashcard("1", "Front2", "Back2");
    public static final List<Flashcard> EXPECTED_FLASHCARDS = Arrays.asList(FLASHCARD_1, FLASHCARD_2);
}
