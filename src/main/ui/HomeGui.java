package ui;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGui extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public HomeGui() {
        JFrame frame = new JFrame("Textbook Rental Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        JPanel homePanel = createHomePanel();
        JPanel rentPanel = createRentPanel();
        JPanel listPanel = createListPanel();
        JPanel searchPanel = createSearchPanel();
        mainPanel.add(homePanel, "Home");
        mainPanel.add(rentPanel, "Rent");
        mainPanel.add(listPanel, "List");
        mainPanel.add(searchPanel, "Search");
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton rentButton = createRentButton();
        JButton listButton = createListButton();
        JButton searchButton = createSearchButton();
        JButton exitButton = createExitButton();

        panel.add(rentButton);
        panel.add(listButton);
        panel.add(searchButton);
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

    private JButton createExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        JTextField nameField = new JTextField(20);

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

        JButton mathButton = new JButton("Math");
        JButton frenchButton = new JButton("French");
        JButton chemistryButton = new JButton("Chemistry");
        JButton computerScienceButton = new JButton("Computer Science");
        JButton englishButton = new JButton("English");
        JButton physicsButton = new JButton("Physics");
        JButton biologyButton = new JButton("Biology");
        JButton statisticsButton = new JButton("Statistics");

        subjectPanel.add(mathButton);
        subjectPanel.add(frenchButton);
        subjectPanel.add(chemistryButton);
        subjectPanel.add(computerScienceButton);
        subjectPanel.add(englishButton);
        subjectPanel.add(physicsButton);
        subjectPanel.add(biologyButton);
        subjectPanel.add(statisticsButton);
        return subjectPanel;
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
                System.out.println("New Rental Listing Successfully Created!");
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

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String subject = subjectField.getText();
                // Logic to search for the book
                System.out.println("Searching for book: " + title + " in subject: " + subject);
            }
        });

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

}
