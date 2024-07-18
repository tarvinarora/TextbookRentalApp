package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextbookTest {

    private Textbook testTextbook;
    
    @BeforeEach
    void runBefore() {
        testTextbook = new Textbook("Intro to Programming", 
        "Anne Fringer", "Computer Science", "15", "Good");
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

    @Test
    void testSetAuthor() {
        testTextbook.setAuthor("Anne Fringer");
        assertEquals("Anne Fringer", testTextbook.getAuthor());
    }

    @Test
    void testSetRentalPrice() {
        testTextbook.setRentalPrice("15");
        assertEquals("15", testTextbook.getRentalPrice());
    }

    @Test
    void testSetCondition() {
        testTextbook.setCondition("Bad");
        assertEquals("Bad", testTextbook.getCondition());
    }
}
