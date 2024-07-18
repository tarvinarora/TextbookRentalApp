package model;

public class Textbook {

    private String title;
    private String author;
    private String subject;
    private String rentalPrice;
    private String condition;

    // EFFECTS: constructs a Textbook with given author, subject, year
    public Textbook(String title, String author, String subject, String rentalPrice, String condition) {
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.rentalPrice = rentalPrice;;
        this.condition = condition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTitle() {
        return title;
    }
    
    // EFFECTS: returns the author for the textbook
    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public String getCondition() {
        return condition;
    }

}
