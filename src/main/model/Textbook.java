package model;

public class Textbook {

    private String title;
    private String author;
    private String subject;

    // EFFECTS: constructs a Textbook with given author, subject, year
    public Textbook(String author, String title, String subject) {
        this.title = title;
        this.author = author;
        this.subject = subject;
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



    


}
