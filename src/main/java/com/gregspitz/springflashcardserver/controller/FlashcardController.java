package com.gregspitz.springflashcardserver.controller;

import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import com.gregspitz.springflashcardserver.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardController {

    // TODO: fill this in and test

    @Autowired
    private FlashcardRepository repository;

    @RequestMapping("/flashcards")
    public List<Flashcard> getFlashcards() {
        return repository.getFlashcards();
    }

    @RequestMapping("/flashcard/{flashcardId}")
    public Flashcard getFlashcardById(@PathVariable(value="flashcardId") String flashcardId) {
        return repository.getFlashcardById(flashcardId);
    }
}
