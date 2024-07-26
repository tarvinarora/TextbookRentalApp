package persistence;

import model.Buyer;
import model.Textbook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public Buyer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBuyer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Buyer parseBuyer(JSONObject jsonObject) {
        String buyerName = jsonObject.getString("Buyer Name");
        Buyer b = new Buyer(buyerName);
        addTextbooks(b, jsonObject);
        return b;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTextbooks(Buyer b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Wishlisted");
        for (Object json : jsonArray) {
            JSONObject nextTextbook = (JSONObject) json;
            addTextbook(b, nextTextbook);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addTextbook(Buyer b, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String author = jsonObject.getString("Author");
        String subject = jsonObject.getString("Subject");
        String rentalPrice = jsonObject.getString("Rental Price");
        String condition = jsonObject.getString("Condition");
        Textbook textbook = new Textbook(title, author, subject, rentalPrice, condition);
        b.addToWishlist(textbook);
    }
}

