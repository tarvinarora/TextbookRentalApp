package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuyerTest {

    private Buyer testBuyer;

    @BeforeEach 
    void runBefore(){
        testBuyer = new Buyer("John", 001);
    }

    @Test
    void testSetBuyerName() {
        testBuyer.setBuyerName("Matilda");
        assertEquals("Matilda", testBuyer.getBuyerName());
    }

    @Test 
    void testSetBuyerId() {
        testBuyer.setBuyerId(002);
        assertEquals(002, testBuyer.getBuyerId());
    }

    

}
