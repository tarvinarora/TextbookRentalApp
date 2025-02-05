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

    // EFFECTS: constructs a buyer with a buyerName and an empty Wishlist
    public Buyer() {
        this.buyerName = "";
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
        if (!wishlisted.contains(textbook)) { // checks for duplicates
            wishlisted.add(textbook);
        }
        EventLog.getInstance()
                .logEvent(new Event("Textbook " + textbook.getTitle() + " added to wishlist for " + getBuyerName()));
    }

    // EFFECTS: returns the Buyer's wishlist
    public List<Textbook> getWishlist() {
        return Collections.unmodifiableList(wishlisted);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("buyerName", buyerName);
        json.put("wishlisted", wishlistToJson());
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
