package model;

public class Textbook {
    int other;

    private String author;
    private int year;
    private String subject;

    //EFFECTS: constructs a Textbook with given author, subject, year
    public Textbook(String author, int year, String subject) {
        this.author = author;
        this.year = year;
        this.subject = subject;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getSubject() {
        return subject;
    }



    


}
