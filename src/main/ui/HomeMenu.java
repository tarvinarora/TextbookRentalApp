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

        Textbook textbook = new Textbook(author, title, subject);

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
        processSubjectSelection(input);
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
}
