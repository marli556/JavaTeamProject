package libraryLending;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class LibraryApplication {

	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    LibraryManagement library = new LibraryManagement();
	    int appInit = 1;
	    int appChoice = 0;
	    int borrowOn = 1;

	    // Load users from file
	    String usersFilePath = "/Users/marliyatukamara/Documents/java_week12/inputFilesForJava/users.txt";
	    library.loadUserFromFile(usersFilePath);

	    // Display all users
	    System.out.println("\n");
	    library.displayAllUsers();  // didplay all user in system

	    System.out.println("\nWelcome to Library System Management");
	    System.out.print("Do you have an account? (yes/no): ");
	    String userResponse = scanner.next().trim().toLowerCase();

	    User userLog = null;
	    // Login
	    if (userResponse.equals("no")) {
	        System.out.print("Enter new Username: ");
	        String userName = scanner.next().trim();

	        // Check if the username already exists
	        boolean usernameExists = library.getUsers().stream()
	            .anyMatch(user -> user.getName().equals(userName));
	        if (usernameExists) {
	            System.out.println("Username already exists. Please choose a different username.");
	            return;
	        }

	        // Generate user ID
	        UserNameGenerator userGener = new UserNameGenerator();
	        String generatedUserId = userGener.generateRandomUserId();

	        // Create a new user
	        User newUser = new User(userName, generatedUserId);

	        // Add user to the library
	        library.addUser(newUser);

	        // Save new user to file
	        library.saveUsersToFile(usersFilePath);

	        System.out.println("User registered successfully! Welcome, " + userName +
	            " (your ID is: " + generatedUserId + ")");
	        userLog = newUser; // userlog in will be current user

	    } else if (userResponse.equals("yes")) {
	        System.out.print("Enter  your Username: ");
	        String userName = scanner.next().trim();

	        // Check if the username exists
	        boolean usernameExists = library.getUsers().stream()
	            .anyMatch(user -> user.getName().equals(userName));
	        if (usernameExists) {
	            userLog = library.getUsers().stream().filter(user -> user.getName()
	                    .equals(userName))
	                .findFirst()
	                .orElse(null);
	            System.out.println("Welcome back, " + userName + "!");
	        } else {
	            System.out.println("Username does not exist.");
	            return;
	        }

	    } else {
	        System.out.println("Invalid response.");
	        return;
	    }

	    while (appInit == 1) {
	        System.out.println("\nPlease select a category below:");
	        System.out.println("1. Genres");
	        System.out.println("2. Borrow");
	        System.out.println("3. Return(WIP)");
	        System.out.println("4. Exit");
	        System.out.print("\nEnter your choice (1-3): ");
	        appChoice = scanner.nextInt();

	        if (appChoice == 1) {
	            System.out.println("Loading library books from file...\n");
	            library.loadBooksFromFile();
	            System.out.println("\nPlease select a category from the following options:");
	            System.out.println("1. Fiction");
	            System.out.println("2. Mystery");
	            System.out.println("3. Software");
	            System.out.println("4. Exit");
	            System.out.print("\nEnter your choice (1-4): ");
	            int categoryChoice = scanner.nextInt();
	            String selectedCategory = "";

	            switch (categoryChoice) {
	                case 1:
	                    selectedCategory = "Fiction";
	                    break;
	                case 2:
	                    selectedCategory = "Mystery";
	                    break;
	                case 3:
	                    selectedCategory = "Software";
	                    break;
	                case 4:
	                	System.out.println("exit program");
	                	System.exit(0);
	                default:
	                    System.out.println("Invalid choice. choose a valid category.");
	                    continue; //exit the program
	            }
	            System.out.println("\nBooks in " + selectedCategory + " category:");
	            library.displayBooksByCategory(selectedCategory);

	        }

	        if (appChoice == 2) {
	            while (borrowOn == 1) {
	                System.out.print("\nWould you like to borrow an item? (yes/no): ");
	                String userRes = scanner.next().trim().toLowerCase();

	                if (userRes.equals("yes")) {
	                    System.out.println("Enter id or title for item: ");
	                    scanner.nextLine(); // clear the buffer
	                    String userInput = scanner.nextLine().trim(); // read the full line

	                    // Get the books that match the input (either ID or title)
	                    List<Book> matchedBooks = library.getBook(userInput);

	                    if (matchedBooks.isEmpty()) {
	                        System.out.println("No books found matching your input.");
	                    } else {
	                        // If books are found, display them
	                        System.out.println("Book found: ");
	                        for (Book book : matchedBooks) {
	                            System.out.println("Title: " + book.getTitle() +
	                                ", ID: " + book.getId() + ", Author: " +
	                                book.getAuthor() + ", Price: " +
	                                book.getPrices());
	                        }
	                    }
	                }
	                if (userRes.equals("no")) {
	                    borrowOn = 0;
	                    System.out.println("exiting the program!");
	                    return;
	                }
	            }
	            //break; // Exit after borrowing
	        }

	        if (appChoice == 4) {
	            System.out.println("Thanks for stopping by!");
	            appInit = 0;
	        }
	    }
	    System.out.println("Session Terminated.");
	    scanner.close();
	}
}
