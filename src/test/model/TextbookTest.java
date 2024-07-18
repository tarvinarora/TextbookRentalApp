package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextbookTest {

    private Textbook testTextbook;
    
    @BeforeEach
    void runBefore() {
        testTextbook = new Textbook("Anne Cringer", "Intro to Probability", "Statistics", 15);
    }

    @Test
    void testSetTitle() {
        testTextbook.setTitle("Intro to Java");
        assertEquals("Intro to Java", testTextbook.getTitle());
    }

    @Test
    void testSetSubject() {
        testTextbook.setSubject("Math");
        assertEquals("Math", testTextbook.getSubject());
    }
}
