package persistence;

import model.Buyer;
import model.Textbook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Buyer from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HashMap<String, Object> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private HashMap<String, Object> parseState(JSONObject jsonObject) {
        HashMap<String, Object> state = new HashMap<>();
        state.put("buyers", parseBuyers(jsonObject));
        state.put("bookMap", parseBookMap(jsonObject));
        return state;
    }

    // REQUIRES: jsonObject != null
    // EFFECTS: parses the given JSONObject to create and return a HashMap of buyers
    private HashMap<String, Buyer> parseBuyers(JSONObject jsonObject) {
        HashMap<String, Buyer> buyers = new HashMap<>();
        if (jsonObject.has("buyers")) {
            JSONObject buyersObject = jsonObject.getJSONObject("buyers");
            for (String key : buyersObject.keySet()) {
                JSONObject buyerObject = buyersObject.getJSONObject(key);
                Buyer buyer = parseBuyer(buyerObject);
                buyers.put(key, buyer);
            }
        }
        return buyers;
    }

    // REQUIRES: jsonObject != null
    // EFFECTS: parses the given JSONObject to create and return a HashMap of book
    // lists
    private HashMap<String, ArrayList<Textbook>> parseBookMap(JSONObject jsonObject) {
        HashMap<String, ArrayList<Textbook>> bookMap = new HashMap<>();
        if (jsonObject.has("bookMap")) {
            JSONObject bookMapObject = jsonObject.getJSONObject("bookMap");
            for (String key : bookMapObject.keySet()) {
                JSONArray textbooksArray = bookMapObject.getJSONArray(key);
                ArrayList<Textbook> textbooks = parseTextbooks(textbooksArray);
                bookMap.put(key, textbooks);
            }
        }
        return bookMap;
    }

    // REQUIRES: textbooksArray != null
    // EFFECTS: parses the given JSONArray to create and return an ArrayList of
    // Textbook objects
    private ArrayList<Textbook> parseTextbooks(JSONArray textbooksArray) {
        ArrayList<Textbook> textbooks = new ArrayList<>();
        for (Object obj : textbooksArray) {
            JSONObject textbookObject = (JSONObject) obj;
            Textbook textbook = parseTextbook(textbookObject);
            textbooks.add(textbook);
        }
        return textbooks;
    }

    // REQUIRES: jsonObject != null
    private Buyer parseBuyer(JSONObject jsonObject) {
        String buyerName = jsonObject.getString("buyerName");
        Buyer buyer = new Buyer(buyerName);
        JSONArray jsonArray = jsonObject.getJSONArray("wishlisted");
        for (Object json : jsonArray) {
            JSONObject nextTextbook = (JSONObject) json;
            Textbook textbook = parseTextbook(nextTextbook);
            buyer.addToWishlist(textbook);
        }
        return buyer;
    }

    // EFFECTS: parses textbook from JSON object and returns it
    private Textbook parseTextbook(JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String author = jsonObject.getString("Author");
        String subject = jsonObject.getString("Subject");
        String rentalPrice = jsonObject.getString("Rental Price");
        String condition = jsonObject.getString("Condition");
        boolean status = jsonObject.getBoolean("Is Rented");
        Textbook textbook = new Textbook(title, author, subject, rentalPrice, condition);
        textbook.setRented(status);
        return textbook;
    }

}
