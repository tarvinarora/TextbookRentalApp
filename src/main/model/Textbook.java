package model;

import java.util.Objects;

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
        this.rentalPrice = rentalPrice;
        ;
        this.condition = condition;
        this.status = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the Title of the textbook to the given title
    public void setTitle(String title) {
        this.title = title;
        EventLog.getInstance().logEvent(new Event("Textbook title set to " + title + "."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Author of the textbook to the given author
    public void setAuthor(String author) {
        this.author = author;
        EventLog.getInstance().logEvent(new Event("Author name set to " + author + "."));
    }

    // MODIFIES: this
    // EFFECTS: set the Subject of the textbook to the given subject
    public void setSubject(String subject) {
        this.subject = subject;
        EventLog.getInstance().logEvent(new Event("Textbook added to " + subject + " list."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Rental Price of the textbook to the given price
    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
        EventLog.getInstance().logEvent(new Event("Rental Price set to " + rentalPrice + "."));
    }

    // MODIFIES: this
    // EFFECTS: sets the Condition of the textbook to the given condition
    public void setCondition(String condition) {
        this.condition = condition;
        EventLog.getInstance().logEvent(new Event("Textbook condition set to " + condition + "."));
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
        EventLog.getInstance().logEvent(new Event("Textbook " + title + " marked as Rented."));
    }

    // MODIFIES: this
    // EFFECTS: sets the rental status of the textbook
    public void setRented(boolean status) {
        this.status = status;
        EventLog.getInstance().logEvent(new Event("Textbook " + title + " rental status set to " + status));
    }

    // MODIFIES: this
    // EFFECTS: marks the textbook as not rented
    public void markNotRented() {
        status = false;
        EventLog.getInstance().logEvent(new Event("Textbook " + title + " marked as Not Rented."));
    }

    // EFFECTS: returns whether textbook rental status
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
        json.put("Is Rented", status);
        return json;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Textbook textbook = (Textbook) obj;
        return title.equals(textbook.title)
                && author.equals(textbook.author)
                && subject.equals(textbook.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, subject, rentalPrice, condition);
    }

}
