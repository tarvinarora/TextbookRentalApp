package ui;

import java.util.ArrayList;
import java.util.HashMap;

import model.Textbook;
import model.Buyer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeGui extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HashMap<String, ArrayList<Textbook>> bookMap; // key:Subject; value: list of textbooks
    private HashMap<String, Buyer> buyers; // key: BuyerName; value: Buyer object
    private Buyer currentBuyer;
    private JTextField nameField;
    private static final String JSON_STORE = "./data/data.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public HomeGui() {
        bookMap = new HashMap<>();
        buyers = new HashMap<>();
        currentBuyer = new Buyer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        promptLoadState();

        JFrame frame = new JFrame("Textbook Rental Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        JPanel homePanel = createHomePanel();
        JPanel rentPanel = createRentPanel();
        JPanel listPanel = createListPanel();
        JPanel searchPanel = createSearchPanel();
        JPanel viewWishlistPanel = createViewWishlistPanel();
        JPanel confirmRentalPanel = createConfirmRentalPanel();
        JPanel confirmAdditionToWishlistPanel = confirmAdditionToWishlistPanel();
        mainPanel.add(homePanel, "Home");
        mainPanel.add(rentPanel, "Rent");
        mainPanel.add(listPanel, "List");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(viewWishlistPanel, "ViewWishlist");
        mainPanel.add(confirmRentalPanel, "ConfirmRental");
        mainPanel.add(confirmAdditionToWishlistPanel, "ConfirmAdditionToWishlist");

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int response = JOptionPane.showConfirmDialog(frame, 
                        "Do you want to save your data before exiting?", "Save Data",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    saveState(); 
                }
                System.exit(0);
            }
        });

        
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    //EFFECTS: checks is user wants to load state of application, 
    //         if YES then load the state, else continue with app
    private void promptLoadState() {
        int option = JOptionPane.showConfirmDialog(
            this,
            "Do you want to load the previous state of the application?",
            "Load State",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
    );
    if (option == JOptionPane.YES_OPTION) {
        loadState();
    }
    }

    private void loadState() {
        try {
            HashMap<String, Object> state = jsonReader.read();
            buyers = (HashMap<String, Buyer>) state.get("buyers");
            bookMap = (HashMap<String, ArrayList<Textbook>>) state.get("bookMap");
            System.out.println("Loaded state from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    private void saveState() {
        try {
            jsonWriter.open();
            HashMap<String, Object> state = new HashMap<>();
            state.put("buyers", buyers);
            state.put("bookMap", bookMap);

            jsonWriter.write(state);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(mainPanel, "Unable to save data.",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton rentButton = createRentButton();
        JButton listButton = createListButton();
        JButton searchButton = createSearchButton();
        JButton viewWishlistButton = createViewWishlistButton();
        JButton exitButton = createExitButton();

        panel.add(rentButton);
        panel.add(listButton);
        panel.add(searchButton);
        panel.add(viewWishlistButton);
        panel.add(exitButton);
        return panel;
    }

    private JButton createRentButton() {
        JButton rentButton = new JButton("Rent a Book");
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Rent");
            }
        });
        return rentButton;
    }

    private JButton createListButton() {
        JButton listButton = new JButton("List a Book");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "List");
            }
        });
        return listButton;
    }

    private JButton createSearchButton() {
        JButton searchButton = new JButton("Search a Book");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Search");
            }
        });
        return searchButton;
    }

    private JButton createViewWishlistButton() {
        JButton viewWishlistButton = new JButton("View Wishlist");
        viewWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "ViewWishlist");
            }
        });
        return viewWishlistButton;
    }

    private JButton createExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(mainPanel, 
                        "Do you want to save your data before exiting?", "Save Data",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    saveState();
                }
                System.exit(0);
            }
        });
        return exitButton;
    }

    private JPanel createRentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        panel.add(createRentNorthPanel(), BorderLayout.NORTH);
        panel.add(createRentSubjectPanel(), BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);
        return panel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Home");
            }
        });
        return backButton;
    }

    private JPanel createRentNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Please enter your name:");
        nameField = new JTextField(20);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JLabel subjectLabel = new JLabel("Select a Subject", JLabel.CENTER);
        northPanel.add(namePanel);
        northPanel.add(subjectLabel);
        return northPanel;
    }

    private JPanel createRentSubjectPanel() {
        JPanel subjectPanel = new JPanel();
        subjectPanel.setLayout(new GridLayout(4, 2, 10, 10));

        subjectPanel.add(createSubjectButton("Math"));
        subjectPanel.add(createSubjectButton("French"));
        subjectPanel.add(createSubjectButton("Chemistry"));
        subjectPanel.add(createSubjectButton("Computer Science"));
        subjectPanel.add(createSubjectButton("English"));
        subjectPanel.add(createSubjectButton("Physics"));
        subjectPanel.add(createSubjectButton("Biology"));
        subjectPanel.add(createSubjectButton("Statistics"));
        return subjectPanel;
    }

    private JButton createSubjectButton(String subject) {
        JButton subjectButton = new JButton(subject);
        subjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter your name first.",
                            "Name Required", JOptionPane.WARNING_MESSAGE);
                } else {
                    String buyerName = nameField.getText();
                    if (!buyers.containsKey(buyerName)) { // if the name doesn't already exist
                        currentBuyer = new Buyer(buyerName); // create a new buyer 
                        buyers.put(buyerName, currentBuyer); // add the new buyer to the buyers list
                    } else {
                        currentBuyer = buyers.get(buyerName); // retrieve existing buyer
                    }

                    displayBooksForSubject(subject);
                }
            }
        });
        return subjectButton;
    }

    private void displayBooksForSubject(String subject) {
        if (bookMap.containsKey(subject)) { // does the map have subjects
            ArrayList<Textbook> textbooks = bookMap.get(subject); // getting the list of textbooks
            if (textbooks.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "No books available for subject: " + subject,
                        "No Books", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder bookList = new StringBuilder("Books available for " + subject + ":\n");
                int index = 1;
                for (Textbook book : textbooks) {
                    bookList.append(index).append(". ").append(book.getTitle()).append("\n");
                    index++;
                }
                int result = JOptionPane.showOptionDialog(mainPanel, bookList.toString(), "Books List",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        new String[] { "Cancel", "Rent", "Add to Wishlist"}, "Rent");

                if (result == JOptionPane.NO_OPTION) {
                    cardLayout.show(mainPanel, "ConfirmRental");
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    cardLayout.show(mainPanel, "ConfirmAdditionToWishlist");
                }
            }
        } else {
            JOptionPane.showMessageDialog(mainPanel, "No books available for subject: " + subject, "No Books",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JPanel createListPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField[] fields = addListFields(panel);
        addListButtons(panel, fields);
        return panel;
    }

    private JTextField[] addListFields(JPanel panel) {
        JLabel[] labels = {
                new JLabel("Title:"), new JLabel("Author:"),
                new JLabel("Subject:"), new JLabel("Price:"),
                new JLabel("Condition:")
        };
        JTextField[] fields = {
                new JTextField(), new JTextField(),
                new JTextField(), new JTextField(),
                new JTextField()
        };
        for (int i = 0; i < labels.length; i++) {
            panel.add(labels[i]);
            panel.add(fields[i]);
        }
        return fields;
    }

    private void addListButtons(JPanel panel, JTextField[] fields) {
        JButton submitButton = new JButton("Create Listing");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = fields[0].getText();
                String author = fields[1].getText();
                String subject = fields[2].getText();
                String price = fields[3].getText();
                String condition = fields[4].getText();
                Textbook textbook = new Textbook(title, author, subject, price, condition);
                textbook.markNotRented();
                if (!bookMap.containsKey(subject)) { // are there textbooks of selected subject?
                    bookMap.put(subject, new ArrayList<Textbook>());
                }
                bookMap.get(subject).add(textbook); // otherwise just add to already existing list
                JOptionPane.showMessageDialog(panel, "New Rental Listing Successfully Created!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                for (JTextField field : fields) {
                    field.setText("");
                }
            }
        });
        panel.add(submitButton);
        panel.add(createBackButton());
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel subjectLabel = new JLabel("Subject:");
        JTextField subjectField = new JTextField();

        JButton searchButton = createSearchFunction(titleField, subjectField, panel);

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(new JLabel());
        panel.add(searchButton);
        panel.add(new JLabel());
        panel.add(createBackButton());

        return panel;
    }

    private JButton createSearchFunction(JTextField titleField, JTextField subjectField, JPanel panel) {
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchAction(titleField, subjectField, panel);
            }
        });
        return searchButton;
    }

    private void handleSearchAction(JTextField titleField, JTextField subjectField, JPanel panel) {
        String title = titleField.getText();
        String subject = subjectField.getText();
        if (bookMap.containsKey(subject)) {
            ArrayList<Textbook> subjectBooks = bookMap.get(subject);
            boolean found = false;

            for (Textbook t : subjectBooks) {
                if (t.getTitle().equalsIgnoreCase(title)) {
                    JOptionPane.showMessageDialog(panel, "Book found: " + t.getTitle(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(panel, "Book not found.",
                        "Failure", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Subject not found.",
                    "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createConfirmRentalPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = new JLabel("Please Enter the title of the book you want to rent: ");
        JTextField titleField = new JTextField();
        JButton confirmButton = new JButton("Confirm Rental");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmRental(titleField.getText());
            }
        });
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(confirmButton);
        panel.add(createBackButton());

        return panel;
    }

    private void confirmRental(String title) {
        for (ArrayList<Textbook> textbooks : bookMap.values()) {
            for (Textbook book : textbooks) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    if (book.isRented()) {
                        JOptionPane.showMessageDialog(mainPanel, "Sorry, the book is already rented.", "Rental Status",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        book.markRented();
                        JOptionPane.showMessageDialog(mainPanel, "Yay, you have rented the book!", "Rental Status",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(mainPanel, "No book found with title: " + title, "Rental Status",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel confirmAdditionToWishlistPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel titleLabel = new JLabel("Please enter the Title of the book you want to Add to Wishlist: ");
        JTextField titleField = new JTextField();
        JButton confirmButton = new JButton("Add Textbook to Wishlist");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmAdditionToWishlist(titleField.getText());
            }
        });
    panel.add(titleLabel);
    panel.add(titleField);
    panel.add(confirmButton);
    panel.add(createBackButton());

    return panel;
    }

    private void confirmAdditionToWishlist(String title) {
        for (ArrayList<Textbook> textbooks : bookMap.values()) {
            for (Textbook book : textbooks) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    currentBuyer.addToWishlist(book);
                    JOptionPane.showMessageDialog(mainPanel, "Book added to wishlist!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(mainPanel, "No book found with title: " + title, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private JPanel createViewWishlistPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter Buyer Name:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JButton viewButton = new JButton("View Wishlist");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buyerName = nameField.getText();
                if (buyerName.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter a buyer name.",
                            "Name Required", JOptionPane.WARNING_MESSAGE);
                } else {
                    viewWishlist(buyerName);
                }
            }
        });

        panel.add(namePanel, BorderLayout.NORTH);
        panel.add(viewButton, BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);

        return panel;
    }

    private void viewWishlist(String buyerName) {
        Buyer buyer = buyers.get(buyerName); // retrieve the buyer from the buyers map
        if (buyer == null) {
            JOptionPane.showMessageDialog(mainPanel, "Buyer not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } else { // if the buyer exists
            StringBuilder wishlist = new StringBuilder("Wishlist for " + buyerName + ":\n"); // making wishlist for that buyer
            for (Textbook book : buyer.getWishlist()) {
                wishlist.append(book.getTitle()).append("\n");
            }
            JOptionPane.showMessageDialog(mainPanel, wishlist.toString(), "Wishlist", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
