package com.gregspitz.springflashcardserver.controller;

import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import com.gregspitz.springflashcardserver.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardController {

    @Autowired
    private FlashcardRepository repository;

    @RequestMapping("/flashcards")
    public List<Flashcard> getFlashcards() {
        return repository.getFlashcards();
    }
}
