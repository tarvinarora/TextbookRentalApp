package persistence;

import model.Textbook;
import model.Buyer;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("data/my\0illegal:fileName.json");
            writer.open();
            fail("Exception should be thrown");
        } catch (FileNotFoundException e) {
            // Expected
        }
    }

    @Test
    void testWriterEmptyState() {
        try {
            // Set up empty state
            String filePath = "testWriterEmptyState.json";
            JsonWriter writer = new JsonWriter(filePath);
            writer.open();
            writer.write(new HashMap<>());
            writer.close();

            // Test the reader
            JsonReader reader = new JsonReader(filePath);
            HashMap<String, Object> state = reader.read();

            // Check if buyers and bookMap are empty
            assertTrue(((HashMap<String, Buyer>) state.get("buyers")).isEmpty());
            assertTrue(((HashMap<String, ArrayList<Textbook>>) state.get("bookMap")).isEmpty());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralState() {
        try {
            // Set up general state
            String filePath = "testWriterGeneralState.json";
            JsonWriter writer = new JsonWriter(filePath);
            writer.open();

            HashMap<String, Object> state = new HashMap<>();
            HashMap<String, Buyer> buyers = new HashMap<>();
            HashMap<String, ArrayList<Textbook>> bookMap = new HashMap<>();

            Buyer buyer = new Buyer("Tarvin");
            Textbook textbook = new Textbook("Effective Java", "Joshua Bloch", "Computer Science", "10", "New");
            buyer.addToWishlist(textbook);
            buyers.put("Tarvin", buyer);

            ArrayList<Textbook> textbooks = new ArrayList<>();
            textbooks.add(textbook);
            bookMap.put("Computer Science", textbooks);

            state.put("buyers", buyers);
            state.put("bookMap", bookMap);

            writer.write(state);
            writer.close();

            // Test the reader
            JsonReader reader = new JsonReader(filePath);
            HashMap<String, Object> readState = reader.read();

            // Check if buyers and bookMap are correctly parsed
            HashMap<String, Buyer> readBuyers = (HashMap<String, Buyer>) readState.get("buyers");
            assertEquals(1, readBuyers.size());
            Buyer readBuyer = readBuyers.get("Tarvin");
            assertNotNull(readBuyer);
            assertEquals("Tarvin", readBuyer.getBuyerName());
            assertEquals(1, readBuyer.getWishlist().size());
            Textbook readTextbook = readBuyer.getWishlist().get(0);
            assertEquals("Effective Java", readTextbook.getTitle());

            HashMap<String, ArrayList<Textbook>> readBookMap = (HashMap<String, ArrayList<Textbook>>) readState.get("bookMap");
            assertEquals(1, readBookMap.size());
            ArrayList<Textbook> readTextbooks = readBookMap.get("Computer Science");
            assertNotNull(readTextbooks);
            assertEquals(1, readTextbooks.size());
            Textbook readBook = readTextbooks.get(0);
            assertEquals("Effective Java", readBook.getTitle());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
