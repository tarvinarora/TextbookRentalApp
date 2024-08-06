package persistence;

import model.Buyer;
import model.Textbook;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of application state to file
    public void write(Map<String, Object> state) {
        JSONObject json = new JSONObject();
        json.put("buyers", toJsonBuyers((HashMap<String, Buyer>) state.get("buyers")));
        json.put("bookMap", toJsonBookMap((HashMap<String, ArrayList<Textbook>>) state.get("bookMap")));
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: converts buyers map to JSON object
    private JSONObject toJsonBuyers(HashMap<String, Buyer> buyers) {
        JSONObject jsonObject = new JSONObject();
        if (buyers != null) {
            for (Map.Entry<String, Buyer> entry : buyers.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue().toJson());
            }
        }
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECTS: converts book map to JSON object
    private JSONObject toJsonBookMap(HashMap<String, ArrayList<Textbook>> bookMap) {
        JSONObject jsonObject = new JSONObject();
        if (bookMap != null) {
            for (Map.Entry<String, ArrayList<Textbook>> entry : bookMap.entrySet()) {
                JSONArray jsonArray = new JSONArray();
                for (Textbook textbook : entry.getValue()) {
                    jsonArray.put(textbook.toJson());
                }
                jsonObject.put(entry.getKey(), jsonArray);
            }
        }
        return jsonObject;
    }

}
