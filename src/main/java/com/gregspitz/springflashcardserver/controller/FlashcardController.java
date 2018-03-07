package com.gregspitz.springflashcardserver.controller;

import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import com.gregspitz.springflashcardserver.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/flashcard/{flashcardId}", method = RequestMethod.GET)
    public Flashcard getFlashcardById(@PathVariable(value="flashcardId") String flashcardId) {
        return repository.getFlashcardById(flashcardId);
    }

    @RequestMapping(value = "/flashcard", method = RequestMethod.POST)
    public Flashcard postFlashcard(@RequestBody Flashcard flashcard) {
        repository.addFlashcard(flashcard);
        return flashcard;
    }
}
