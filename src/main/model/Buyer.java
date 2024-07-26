package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a Buyer having a collection of wishlisted textbooks
public class Buyer implements Writable {

    private String buyerName;
    private List<Textbook> wishlisted;

    // EFFECTS: constructs a new Buyer with a name, an ID, and an empty wishlist
    public Buyer(String buyerName) {
        this.buyerName = buyerName;
        this.wishlisted = new ArrayList<>();
    }
    
    public Buyer() {
        this.buyerName = null;
        this.wishlisted = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets the buyer name to a given buyer name
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    // EFFECTS: returns buyer name
    public String getBuyerName() {
        return buyerName;
    }

    // EFFECTS: adds a wishlist textbook to a List of Textbooks
    public void addToWishlist(Textbook textbook) {
        wishlisted.add(textbook);
    }

    public List<Textbook> getWishlist() {
        return Collections.unmodifiableList(wishlisted);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Buyer Name", buyerName);
        json.put("Wishlisted", wishlistToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray wishlistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Textbook t : wishlisted) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
