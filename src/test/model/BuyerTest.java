package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BuyerTest {

    private Buyer testBuyer;
    private Textbook testTextbook;

    @BeforeEach 
    void runBefore() {
        testBuyer = new Buyer();
        testTextbook = new Textbook("Title", "Author", "Math", "9", "good");
    }

    @Test
    void testConstructor() {
        assertNull(testBuyer.getBuyerName());
        assertEquals(0, testBuyer.getWishlist().size());
    }

    @Test
    void testSetBuyerName() {
        testBuyer.setBuyerName("Matilda");
        assertEquals("Matilda", testBuyer.getBuyerName());
    }

    @Test
    void testaddToWishlist() {
        testBuyer.addToWishlist(testTextbook);  
        assertEquals(1, testBuyer.getWishlist().size());
    }

}
