package model;

import java.util.ArrayList;

public class Buyer {

    private String buyerName;
    private int buyerId;
    private ArrayList<Textbook> wishlisted;

    // EFFECTS: constructs a new Buyer with a name, an ID, and an empty wishlist
    public Buyer(String buyerName, int buyerId) {
        this.buyerName = buyerName;
        this.buyerId = buyerId;
        this.wishlisted = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets the buyer name to a given buyer name
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    // MODIFIES: this
    // EFFECTS: sets the buyer id to a given buyer id
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    // EFFECTS: returns buyer name
    public String getBuyerName() {
        return buyerName;
    }

    // EFFECTS: returns buyer id
    public int getBuyerId() {
        return buyerId;
    }

    // EFFECTS: returns a List of Textbooks wishlisted by the buyer
    public ArrayList<Textbook> getWishlist() {
        return wishlisted;
    }

}
