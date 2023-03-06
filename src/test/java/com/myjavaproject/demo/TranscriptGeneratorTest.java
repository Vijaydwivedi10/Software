package com.myjavaproject.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class TranscriptGeneratorTest {

    private final String TEST_STUDENT_EMAIL = "john@example.com";

    @Test
    public void testTranscriptGenerator() {
        // Generate transcript for the student with TEST_STUDENT_EMAIL
        boolean isGenerated = Student.TranscriptGenerator(TEST_STUDENT_EMAIL);

        // Check if the transcript file was generated
        File file = new File("transcript.txt");
        assertTrue(file.exists());

        // Clean up the transcript file if it was generated
        if (isGenerated) {
            file.delete();
        }
    }

}
