package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SellerTest {

    private Seller testSeller;
    private Textbook testTextbook;

    @BeforeEach
    void runBefore() {
        testSeller = new Seller("Maya");
        testTextbook = new Textbook("Title", "Author", "Math", "10", "Fair");
    }

    @Test
    void testConstructor() {
        assertEquals("Maya", testSeller.getSellerName());
        assertEquals(0, testSeller.getActiveListings().size());
    }

    @Test
    void testSetSellerName() {
        testSeller.setSellerName("Bond");
        assertEquals("Bond", testSeller.getSellerName());
    }

    @Test
    void testAddToListings() {
        testSeller.addToListings(testTextbook);
        assertEquals(1, testSeller.getActiveListings().size());
    }
}
