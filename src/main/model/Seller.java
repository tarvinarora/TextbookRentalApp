package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Represents a seller having a collection of rental listings
public class Seller implements Writable{

    private String sellerName;
    private List<Textbook> activelistings;

    public Seller(String sellerName) {
        this.sellerName = sellerName;
        this.activelistings = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: sets the Seller name to give name
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    //EFFECTS: returns seller name
    public String getSellerName() {
        return sellerName;
    }


    //MODIFIES: this
    //EFFECTS: adds a textbook listing to active listings
    public void addToListings(Textbook textbook) {
        activelistings.add(textbook);
    }

    // EFFECTS: returns an unmodifiable list of activelistings of the seller
    public List<Textbook> getActiveListings() {
        return Collections.unmodifiableList(activelistings);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Seller Name", sellerName);
        json.put("Active Listings", activeListingsToJson());
        return json;
    }

    // EFFECTS: returns activelistings in this seller as a JSON array
    private JSONArray activeListingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Textbook t : activelistings) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
