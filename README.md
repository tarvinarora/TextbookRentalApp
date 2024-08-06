# My Personal Project

## Description: Textbook Rental Application - TextXChange

My personal project is a textbook rental application designed specifically for students at UBC. This application facilitates access to textbooks between UBC students, offering both *rental opportunities* and *listings*. Users can either rent out their textbooks to fellow students or find textbooks available for rent. Textbooks are categorized by *Year Level, Subject, Course, and Faculty* to streamline search processes.

The primary users of this application are UBC **undergraduate** and **graduate** students. The **'Borrow a Book'** interface features a category-based navigation system and a search function that enables students to locate textbooks by author, course, or keywords. On the **'Rent a Book'** interface, students can provide details about textbooks they wish to list for rent and create new listings or manage other listings. This project is personally significant to me due to my experience as a UBC student, where the high-cost of textbooks made students hesitate to "purchase" them. So, while this approach to rent textbooks for a smaller fee is not only *affordable*, it also promotes a *sustainability* in our use of physical resources. 

## User Stories for the Textbook Rental Application (Rent a Book Interface):
- As a user, I want to be able to create a listing for a new Textbook rental. 
- As a user, I want to be able to add a Listing to a Rental list.
- As a user, I want to be able to edit any field of the rental listing.
- As a user, I want to be able to enter the Title, Author, Subject, RentalPrice, and Condition of the textbook. 
- As a user, I want to be able to add a textbook to my wishlist.
- As a user, I want to be able to save my application state before I quit the application (if I choose). 
- As a user, I want to be able to load my application state from the previous session from file (if I choose).



## User Stories for the Textbook Rental Application (Borrow a Book Interface):
- As a user, I want to be able to navigate through given categories to find a required book.
- As a user, I want to be able to search the book by Title, and subject. 
- As a user, I want to be able to return to main menu. 
- As a user, I want to be able to view if the rental is available or not available. 
- As a user, I want to be able to rent a textbook. 
- As a user, I want to be able to view my Wishlist.
- As a user I want to be able to see my wishlist in my next session(provided I saved and loaded the state correctly)
- As a user, I want to choose through a catalogue of subjects to find my subject related textbooks. 

#Instructions For Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by:
1: Clicking on "Rent a Book"
2: Entering your name
3: Clicking on the desired subject (please make a listing if no books are found)
4: Clicking on AddToWishlist
5: Confirming the title 
6: Click on "Add Textbook to Wishlist".
Thereby adding multiple Textbooks to a Buyer(via their wishlists).

- You can generate the second required action related to the user story "adding multiple Xs to a Y" by:
1. Click on "Search a Book"
2. Enter the Title
3. Enter a Subject
4. Click on "Search"
Thereby allowing the user to filter through Textbooks to return a desired book. 

Other required action examples include: Rent a Book, Viewing the Wishlist.


- You can locate my visual component by:
Successfully renting a book!
1. Clicking on "Rent a Book"
2: Entering your name
3: Clicking on the desired subject (please make a listing if no books are found)
4. "Rent"
5. Entering the title to confirm
6. Click on Confirm Rental
"
- You can save the state of my application by clicking "Yes" after clicking "Exit" or attempting to close the application.

- You can reload the state of my application by clicking "Yes" before the application attempts to open. 