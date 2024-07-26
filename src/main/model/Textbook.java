package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a textbook having a title, author, subject, rentalPrice, condition and status

public class Textbook implements Writable {

    private String title;
    private String author;
    private String subject;
    private String rentalPrice;
    private String condition;
    private boolean status;

    // EFFECTS: constructs a Textbook with given author, subject, year
    public Textbook(String title, String author, String subject, String rentalPrice, String condition) {
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.rentalPrice = rentalPrice;;
        this.condition = condition;
    }

    // MODIFIES: this
    // EFFECTS: sets the Title of the textbook to the given title
    public void setTitle(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: sets the Author of the textbook to the given author
    public void setAuthor(String author) {
        this.author = author;
    }

    // MODIFIES: this
    // EFFECTS: set the Subject of the textbook to the given subject
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // MODIFIES: this
    // EFFECTS: sets the Rental Price of the textbook to the given price
    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    // MODIFIES: this
    // EFFECTS: sets the Condition of the textbook to the given condition
    public void setCondition(String condition) {
        this.condition = condition;
    }

    // EFFECTS: return the title of the textbook
    public String getTitle() {
        return title;
    }
    
    // EFFECTS: returns the author for the textbook
    public String getAuthor() {
        return author;
    }

    // EFFECTS: returns the subject of the textbook
    public String getSubject() {
        return subject;
    }

    // EFFECTS: returns the rental price of the textbook
    public String getRentalPrice() {
        return rentalPrice;
    }

    // EFFECTS: returns the condition of the textbook
    public String getCondition() {
        return condition;
    }

    // MODIFIES: this
    // EFFECTS: marks the textbook as rented
    public void markRented() {
        status = true;
    }

    // MODIFIES: this
    // EFFECTS: marks the textbook as not rented
    public void markNotRented() {
        status = false;
    }

    //EFFECTS: returns whether textbook rental status
    public boolean isRented() {
        return status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Author", author);
        json.put("Subject", subject);
        json.put("Rental Price", rentalPrice);
        json.put("Condition", condition);
        return json;
    }

}
