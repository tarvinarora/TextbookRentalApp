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

##Phase 4: Task 2

Events logged during this session:
Thu Aug 08 17:59:52 PDT 2024
Textbook title set to Tricolore.
Thu Aug 08 17:59:52 PDT 2024
Author name set to Jacob Penn.
Thu Aug 08 17:59:52 PDT 2024
Textbook added to French list.
Thu Aug 08 17:59:52 PDT 2024
Rental Price set to 5.
Thu Aug 08 17:59:52 PDT 2024
Textbook condition set to Good.
Thu Aug 08 17:59:52 PDT 2024
Textbook Tricolore marked as Not Rented.
Thu Aug 08 18:00:14 PDT 2024
Textbook title set to Advanced Programming.
Thu Aug 08 18:00:14 PDT 2024
Author name set to Dmitri Kostas.
Thu Aug 08 18:00:14 PDT 2024
Textbook added to Computer Science list.
Thu Aug 08 18:00:14 PDT 2024
Rental Price set to 10.
Thu Aug 08 18:00:14 PDT 2024
Textbook condition set to Excellent.
Thu Aug 08 18:00:14 PDT 2024
Textbook Advanced Programming marked as Not Rented.
Thu Aug 08 18:00:33 PDT 2024
Textbook Tricolore marked as Rented.
Thu Aug 08 18:00:50 PDT 2024
Textbook Advanced Programming added to wishlist for Sophia

Loaded state from ./data/data.json
Events logged during this session:
Thu Aug 08 18:02:49 PDT 2024
Textbook Advanced Programming rental status set to false
Thu Aug 08 18:02:49 PDT 2024
Textbook Advanced Programming added to wishlist for Sophia
Thu Aug 08 18:02:49 PDT 2024
Textbook Tricolore rental status set to true
Thu Aug 08 18:02:49 PDT 2024
Textbook Advanced Programming rental status set to false
Thu Aug 08 18:03:07 PDT 2024
Textbook Tricolore added to wishlist for Tarvin
Thu Aug 08 18:03:17 PDT 2024
Textbook Advanced Programming marked as Rented.

##Phase 4: Task 3

I think the implementation of design patterns, specifically the Observer Pattern could improve the code reusability in my application. The observer pattern specifically could be used since the Buyer needs to be updated about the Textbook rental status to be able to carry out Rent functions. 

Another improvement, would be the application of the Single Responsibility Principle, and the use of abstract methods to replace duplicating functionality in other methods. To enhance this, the Buyer and Seller classes could be made to implement an interface called "User". This would reduce duplication of code.