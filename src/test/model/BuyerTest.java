package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BuyerTest {

    private Buyer testBuyer;

    @BeforeEach 
    void runBefore(){
        testBuyer = new Buyer("John");
    }

    @Test
    void testSetBuyerName() {
        testBuyer.setBuyerName("Matilda");
        assertEquals("Matilda", testBuyer.getBuyerName());
    }

    

}
