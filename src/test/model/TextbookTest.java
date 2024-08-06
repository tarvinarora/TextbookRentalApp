package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextbookTest {

    private Textbook testTextbook;
    private Textbook testTextbookSame;
    private Textbook testTextbook3;
    private Textbook testTextbook4;
    private Textbook testTextbookAlmostSame;
    private Textbook testTextbookAuthorDiff;

    @BeforeEach
    void runBefore() {
        testTextbook = new Textbook("Intro to Programming",
                "Anne Fringer", "Computer Science", "15", "Good");
        testTextbookSame = new Textbook("Intro to Programming",
                "Anne Fringer", "Computer Science", "15", "Good");
        testTextbook3 = new Textbook("Tricolore 1", "Cambridge", "French", "10", "New");
        testTextbook4 = new Textbook("Advanced Computing", "Anne Fringer", "Computer Science", "", "");
        testTextbookAlmostSame = new Textbook("Intro to Programming", "Anne Fringer", "Statistics", "", "");
        testTextbookAuthorDiff = new Textbook("Intro to Programming", "Anne Frank", "Computer Science", "", "");
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

    @Test
    void testMarkRented() {
        testTextbook.markRented();
        assertTrue(testTextbook.isRented());
        testTextbook.markNotRented();
        assertFalse(testTextbook.isRented());
        testTextbook.markNotRented();
        assertFalse(testTextbook.isRented());
        testTextbook.markRented();
        assertTrue(testTextbook.isRented());

    }

    @Test
    void testMarkNotRented() {
        testTextbook.markNotRented();
        assertFalse(testTextbook.isRented());
        testTextbook.markNotRented();
        assertFalse(testTextbook.isRented());
        testTextbook.markRented();
        assertTrue(testTextbook.isRented());
        testTextbook.markNotRented();
        assertFalse(testTextbook.isRented());

    }

    @Test
    void testIsRented() {
        testTextbook.markRented();
        assertEquals(true, testTextbook.isRented());
        testTextbook.markNotRented();
        assertEquals(false, testTextbook.isRented());

    }

    @Test
    void testEquals() {
        assertEquals(testTextbook, testTextbookSame);
        assertEquals(testTextbook, testTextbook);
        assertNotEquals(testTextbook, testTextbook3);
        assertNotEquals(testTextbook, "NotATextbook");
        assertNotEquals(testTextbook, null);
        assertNotEquals(testTextbook, testTextbook4);
        assertNotEquals(testTextbook, testTextbookAlmostSame);
        assertNotEquals(testTextbook, testTextbookAuthorDiff);
    }

    @Test
    void testHashCode() {
        assertEquals(testTextbook.hashCode(), testTextbook.hashCode());
        assertEquals(testTextbook.hashCode(), testTextbookSame.hashCode());

    }
}
