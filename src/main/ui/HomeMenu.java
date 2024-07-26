package ui;

import model.Textbook;
import model.Buyer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;


public class HomeMenu {

    private Scanner scanner;
    private List<Textbook> textbooks;
    private List<Textbook> mathbooks;
    private List<Textbook> frenchbooks;
    private List<Textbook> chembooks;
    private List<Textbook> csbooks;
    private List<Textbook> englishbooks;
    private List<Textbook> physbooks;
    private List<Textbook> biobooks;
    private List<Textbook> statbooks;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/wishList.json";
    private Buyer buyer;


    public HomeMenu() throws FileNotFoundException {
        this.textbooks = new ArrayList<>();
        this.mathbooks = new ArrayList<>();
        this.frenchbooks = new ArrayList<>();
        this.chembooks = new ArrayList<>();
        this.csbooks = new ArrayList<>();
        this.englishbooks = new ArrayList<>();
        this.physbooks = new ArrayList<>();
        this.biobooks = new ArrayList<>();
        this.statbooks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        buyer = new Buyer();

        System.out.println("Welcome to TextXChange!");
        chooseOptions();
    }

    // EFFECTS: displays the menu options, takes user input, and processes selected option  
    public void chooseOptions() {
        displayOptions();
        String input = this.scanner.nextLine();
        processCommands(input);
    }

    // EFFECTS: displays a list of commands in the home menu
    public void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("a: List Your TextBook");
        System.out.println("b: Rent A TextBook");
        System.out.println("c: Search Your Textbook");
        System.out.println("d: Add to Wishlist");
        System.out.println("e: Save Wishlist");
        System.out.println("f: Load wishlist");
    }

    // EFFECTS: processes user input in home menu and redirects user
    public void processCommands(String input) {
        switch (input) {
            case "a":
                listTextbook();
                break;           
            case "b":
                handleRentTextbook();
                break;            
            case "c":
                searchTextbook();
                break;            
            case "d":
                editWishlist();
                break;
            case "e":
                saveWishlist();
                break;
            case "f":
                loadWishlist();
                break;
            default:
                System.out.println("Incorrect selection. Please try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a rental listing for a textbook
    private void listTextbook() {
        Textbook textbook = createTextbook();
        addTextbookToList(textbook);
        System.out.println("New rental listing successfully created!");
        chooseOptions();
    }

    //EFFECTS: creates a new Textbook from user input
    public Textbook createTextbook() {
        System.out.println("Please enter the title of the textbook:");
        String title = this.scanner.nextLine();
        System.out.println("Please enter the author of the book:");
        String author = this.scanner.nextLine();
        System.out.println("Please enter the subject of the book:");
        String subject = this.scanner.nextLine();
        System.out.println("Please enter the rental price of the book:");
        String rentalPrice = scanner.nextLine();
        System.out.println("Please enter the condition of the book:");
        String condition = scanner.nextLine();
        return new Textbook(title, author, subject, rentalPrice, condition);
    }

    //EFFECTS: adds the textbook to its corresponding subject list
    @SuppressWarnings("methodlength")
    public void addTextbookToList(Textbook textbook) {
        switch (textbook.getSubject()) {
            case "Math":
                addToList(mathbooks, textbook);
                break;
            case "French":
                addToList(frenchbooks, textbook);
                break;
            case "Chemistry":
                addToList(chembooks, textbook);
                break;
            case "Computer Science":
                addToList(csbooks, textbook);
                break;
            case "English":
                addToList(englishbooks, textbook);
                break;
            case "Physics":
                addToList(physbooks, textbook);
                break;
            case "Biology":
                addToList(biobooks, textbook);
                break;
            case "Statistics":
                addToList(statbooks, textbook);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds subjectbook to subject list, adds textbook to general list
    // and marks as not rented.
    public void addToList(List<Textbook> subjectBook, Textbook textbook) {
        subjectBook.add(textbook);
        textbooks.add(textbook);
        textbook.markNotRented();
    }

    //EFFECTS: displays menu for textbook rental and processes user commands
    private void handleRentTextbook() {
        if (buyer.getBuyerName() == null) {
            System.out.println("Please enter your name: ");
            buyer.setBuyerName(scanner.nextLine());
        }
        displaySubjects();
        String input = this.scanner.nextLine();
        List<Textbook> returnedList = processSubjectSelection(input);
        displayList(returnedList);
        selectRental();
        chooseOptions();
    }

    // EFFECTS: displays subjects for which textbooks are available
    private void displaySubjects() {
        System.out.println("Please select the subject:");
        System.out.println("M: Math");
        System.out.println("F: French");
        System.out.println("C: Chemistry");
        System.out.println("CS: Computer Science");
        System.out.println("E: English");
        System.out.println("P: Physics");
        System.out.println("B: Biology");
        System.out.println("S: Statistics");
        
    }

    //EFFECTS: lists a list of textbooks selected by subject
    private List<Textbook> processSubjectSelection(String input) {
        switch (input) {
            case "M":
                return mathbooks;
            case "F":
                return frenchbooks;
            case "C":
                return chembooks;
            case "CS":
                return csbooks;
            case "E":
                return englishbooks;
            case "P":
                return physbooks;
            case "B":
                return biobooks;
            case "S":
                return statbooks;
            default:
                return textbooks;
        }
    }

    // EFFECTS: displays the title of textbooks for active listings
    private void displayList(List<Textbook> textbook) {
        for (Textbook t: textbook) {
            System.out.printf("%s\n", t.getTitle());
        }
    }

    // EFFECTS: if the user wants to rent textbooks, then a new menu is presented,
    //          menu will check for availability and print messages accordingly
    public void selectRental() {
        System.out.println("Do you want to rent any textbooks listed?");
        String confirm = scanner.nextLine();
        switch (confirm) {
            case "Y":
                handleRental();
                break;
            case "N":
                System.out.println("No textbooks have been selected.");
                chooseOptions();
                break;
        }
    }

    // EFFECTS: if textbook != null AND textbook is not rented, then mark textbook as rented
    //          along with success message, otherwise print error message and return to menu
    public void handleRental() {
        System.out.println("Please enter the title of the book:");
        String title = scanner.nextLine();
        Textbook textbook = findTextbook(title);
        if (textbook != null) {
            if (!textbook.isRented()) {
                textbook.markRented();
                System.out.println("Yay! You have rented " + title);
                chooseOptions();
            } else {
                System.out.println("Sorry! The book has already been rented.");
                chooseOptions();
            }
        } else {
            System.out.println("Sorry! The book was not found.");
            chooseOptions();
        }

    }

    // EFFECTS: searches for the textbook in active listings and returns it, otherwise null
    private Textbook findTextbook(String title) {
        for (Textbook book: textbooks) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    // EFFECTS: conducts a search of required textbook by title and subject using helper
    private void searchTextbook() {
        System.out.println("Please enter the title of the book you want to find:");
        String title = scanner.nextLine();
        System.out.println("Please enter the subject of book required:");
        String subject = scanner.nextLine(); 
        boolean found = false;
        if (subject.equals("Math")) {
            found = findSubjectBook(mathbooks, title);
        } else if (subject.equals("French")) {
            found = findSubjectBook(frenchbooks, title); 
        } else if (subject.equals("Chemistry")) {
            found = findSubjectBook(chembooks, title);
        } else if (subject.equals("Computer Science")) {
            found = findSubjectBook(csbooks, title);
        } else if (subject.equals("English")) {
            found = findSubjectBook(englishbooks, title);
        } else if (subject.equals("Physics")) {
            found = findSubjectBook(physbooks, title);
        } else if (subject.equals("Biology")) {
            found = findSubjectBook(biobooks, title);   
        } else if (subject.equals("Statistics")) {
            found = findSubjectBook(statbooks, title);
        }
        if (!found) {
            System.out.println("Sorry, your book was not found.");
        }
        chooseOptions();
    }

    //  EFFECTS: if book title is found then prints success message,
    //           if book isRented then prints sorry message else,
    //           prints that the book is available
    public boolean findSubjectBook(List<Textbook> subjectBook, String title) {
        for (Textbook book: subjectBook) {
            if (book.getTitle().equals(title)) {
                System.out.println("The book " + title + " was found.");
                if (book.isRented()) {
                    System.out.println("Sorry! Book is rented.");
                } else {
                    System.out.println("Book is available to rent!");
                }
                return true;
            }
        }
        return false;
    }

    // EFFECTS: add textbook to wishlist
    public void editWishlist() {
        System.out.println("Please enter title of the book: ");
        String title = scanner.nextLine();
        Textbook textbook = findTextbook(title);
        if (textbook != null) {
            buyer.addToWishlist(textbook);
            System.out.println(textbook.getTitle() + " was added to wishlist!");
            chooseOptions();
        } else {
            System.out.println("Sorry cannot be added to wishlist!");
            chooseOptions();
        }
    }

    //EFFECTS: save Buyer's wishlist to file
    public void saveWishlist() {
        if (buyer.getBuyerName() == null) {
            System.out.println("Please enter your name: ");
            buyer.setBuyerName(scanner.nextLine());
        }
        try {
            jsonWriter.open();
            jsonWriter.write(buyer);
            jsonWriter.close();
            System.out.println("Saved " + buyer.getBuyerName() + "'s Wishlist to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        chooseOptions();
    }

    // EFFECTS: load the saved state of the wishlist
    public void loadWishlist() {
        try {
            buyer = jsonReader.read();
            System.out.println("Loaded " + buyer.getBuyerName() + "'s Wishlist from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        chooseOptions();
    }
}

