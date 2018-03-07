package com.gregspitz.springflashcardserver.data;

import com.gregspitz.springflashcardserver.model.Flashcard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the implementation of {@link DataStructuresToFlashcardTransformer}
 */
public class DataStructuresToFlashcardTransformerTest {

    private static final String DATA_FILE_NAME = "DataStructures.json";

    private String jsonFile;

    @Before
    public void setup() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(DATA_FILE_NAME);
        jsonFile = resource.getFile();
    }

    @Test
    public void transformsFile() throws Exception {
        List<Flashcard> flashcards = DataStructuresToFlashcardTransformer.transformFromFile(jsonFile);
        assertEquals(54, flashcards.size());
        assertEquals("Abstract factory", flashcards.get(0).getFront());
    }

    @Test(expected = IOException.class)
    public void tryingToOpenFileNotThere_throwsIOException() throws IOException {
        DataStructuresToFlashcardTransformer.transformFromFile("Not a real file");
    }
}
