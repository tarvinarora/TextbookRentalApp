package ui;

import model.Textbook;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import org.junit.platform.console.shadow.picocli.CommandLine.Help.Ansi.Text;

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


    public HomeMenu() {
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

        System.out.println("Welcome to TextXChange!");
        chooseOptions();
    }

    // EFFECTS: 
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
            
            default:
            System.out.println("Incorrect selection. Please try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a rental listing for a textbook
    private void listTextbook() {
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

        Textbook textbook = new Textbook(title, author, subject, rentalPrice, condition);

        switch(textbook.getSubject()) {
            case "Math":
                this.mathbooks.add(textbook);
                break;
            case "French":
                this.frenchbooks.add(textbook);
                break;
            case "Chemistry":
                this.chembooks.add(textbook);
                break;
            case "Computer Science":
                this.csbooks.add(textbook);
                break;
            case "English":
                this.englishbooks.add(textbook);
                break;
            case "Physics":
                this.physbooks.add(textbook);
                break;
            case "Biology":
                this.biobooks.add(textbook);
                break;
            case "Statistics":
                this.statbooks.add(textbook);
                break;
        }
        System.out.println("New rental listing successfully created!");
        chooseOptions();
    }



    private void handleRentTextbook() {
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

    private List<Textbook> processSubjectSelection (String input) {
        switch(input) {
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

    private void displayList(List<Textbook> textbook) {
        for(Textbook t: textbook) {
            System.out.printf("%s\n", t.getTitle());
        }
    }

    private void selectRental() {
        System.out.println("Do you want to rent any textbooks listed?");
        String confirm = scanner.nextLine();
        switch(confirm) {
            case "Y":
                System.out.println("Please enter the title of the book:");
                String title = scanner.nextLine();
                System.out.println("Yay! You have rented " + title);
                break;
            case "N":
                System.out.println("No textbooks have been selected.");
                break;
        }
    }

    private void searchTextbook() {
        System.out.println("Please enter the title of the book you want to find:");
        String title = scanner.nextLine();
        System.out.println("Please enter the subject of book required:");
        String subject = scanner.nextLine();
        
        if (subject.equals("Math")) {
            for (Textbook book: mathbooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("French")) {
            for (Textbook book: frenchbooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("Chemistry")) {
            for (Textbook book: chembooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("Computer Science")) {
            for (Textbook book: csbooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("English")) {
            for (Textbook book: englishbooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("Physics")) {
            for (Textbook book: physbooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("Biology")) {
            for (Textbook book: biobooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else if (subject.equals("Statistics")) {
            for (Textbook book: statbooks) {
                if(book.getTitle().equals(title)) {
                    System.out.println("The book " +title+ " was found.");
                    selectRental();
                    break;
                }
                System.out.println("Sorry your book was not found.");
                chooseOptions();
            }
        } else {
            System.out.println("Sorry your book was not found.");
            chooseOptions();
        }
        }
    }

