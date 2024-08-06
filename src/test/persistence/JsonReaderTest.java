package persistence;

import model.Textbook;
import model.Buyer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyState() {
        try {
            // Set up empty JSON data
            String filePath = "testReaderEmptyState.json";
            writeJsonToFile(filePath, new JSONObject());

            // Test the reader
            JsonReader reader = new JsonReader(filePath);
            HashMap<String, Object> state = reader.read();

            assertTrue(((HashMap<String, Buyer>) state.get("buyers")).isEmpty());
            assertTrue(((HashMap<String, ArrayList<Textbook>>) state.get("bookMap")).isEmpty());

            // Clean up
            new File(filePath).delete();
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testReaderGeneralState() {
        try {
            String filePath = "testReaderGeneralState.json";
            JSONObject json = createJsonTestFile(filePath);

            JsonReader reader = new JsonReader(filePath);
            HashMap<String, Object> state = reader.read();

            verifyBuyers(state);
            verifyBookMap(state);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private JSONObject createJsonTestFile(String filePath) throws IOException {
        JSONObject json = new JSONObject();
        JSONObject buyersJson = new JSONObject();
        JSONObject buyerJson = createBuyerJson();
        buyersJson.put("Tarvin", buyerJson);
        json.put("buyers", buyersJson);

        JSONObject bookMapJson = createBookMapJson();
        json.put("bookMap", bookMapJson);

        writeJsonToFile(filePath, json);
        return json;
    }

    private JSONObject createBuyerJson() {
        JSONObject buyerJson = new JSONObject();
        buyerJson.put("buyerName", "Tarvin");
        JSONArray wishlist = new JSONArray();

        JSONObject textbookJson = createTextbookJson();
        wishlist.put(textbookJson);

        buyerJson.put("wishlisted", wishlist);
        return buyerJson;
    }

    private JSONObject createTextbookJson() {
        JSONObject textbookJson = new JSONObject();
        textbookJson.put("Title", "Effective Java");
        textbookJson.put("Author", "JoshuaB");
        textbookJson.put("Subject", "Computer Science");
        textbookJson.put("Rental Price", "10");
        textbookJson.put("Condition", "New");
        textbookJson.put("Is Rented", false);
        return textbookJson;
    }

    private JSONObject createBookMapJson() {
        JSONObject bookMapJson = new JSONObject();
        JSONArray textbooks = new JSONArray();
        textbooks.put(createTextbookJson());
        bookMapJson.put("Computer Science", textbooks);
        return bookMapJson;
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

    private void writeJsonToFile(String filePath, JSONObject jsonObject) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString(4));
        }
    }

}
