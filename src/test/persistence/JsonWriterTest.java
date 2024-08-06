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
            String filePath = "testWriterGeneralState.json";
            setupAndWriteState(filePath);
            HashMap<String, Object> readState = readState(filePath);
            verifyBuyers(readState);
            verifyBookMap(readState);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void setupAndWriteState(String filePath) throws IOException {
        JsonWriter writer = new JsonWriter(filePath);
        writer.open();

        HashMap<String, Object> state = createState();
        writer.write(state);
        writer.close();
    }

    private HashMap<String, Object> createState() {
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
        return state;
    }

    private HashMap<String, Object> readState(String filePath) throws IOException {
        JsonReader reader = new JsonReader(filePath);
        return reader.read();
    }

    private void verifyBuyers(HashMap<String, Object> state) {
        HashMap<String, Buyer> buyers = (HashMap<String, Buyer>) state.get("buyers");
        assertEquals(1, buyers.size());
        Buyer buyer = buyers.get("Tarvin");
        assertNotNull(buyer);
        assertEquals("Tarvin", buyer.getBuyerName());
        assertEquals(1, buyer.getWishlist().size());
        Textbook textbook = buyer.getWishlist().get(0);
        assertEquals("Effective Java", textbook.getTitle());
    }

    private void verifyBookMap(HashMap<String, Object> state) {
        HashMap<String, ArrayList<Textbook>> bookMap = (HashMap<String, ArrayList<Textbook>>) state.get("bookMap");
        assertEquals(1, bookMap.size());
        ArrayList<Textbook> textbooksList = bookMap.get("Computer Science");
        assertNotNull(textbooksList);
        assertEquals(1, textbooksList.size());
        Textbook book = textbooksList.get(0);
        assertEquals("Effective Java", book.getTitle());

    }
}
