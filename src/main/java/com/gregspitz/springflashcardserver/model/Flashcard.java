package com.gregspitz.springflashcardserver.model;

import java.util.UUID;

/**
 * Represents a single flashcard
 */
public class Flashcard {
    private String id;
    private String front;
    private String back;

    // Default constructor for Jackson
    public Flashcard() {}

    public Flashcard(String front, String back) {
        this(UUID.randomUUID().toString(), front, back);
    }

    public Flashcard(String id, String front, String back) {
        this.id = id;
        this.front = front;
        this.back = back;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getId() {
        return id;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != Flashcard.class) {
            return false;
        }
        Flashcard otherCard = (Flashcard) obj;
        return otherCard.getId().equals(getId()) &&
                otherCard.getFront().equals(getFront()) &&
                otherCard.getBack().equals(getBack());
    }
}
