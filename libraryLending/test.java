package libraryLending;

import java.io.*;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        LibraryManagement library = new LibraryManagement();
        

      
        System.out.println("\nWelcome to Library system management");
        System.out.println("\nWould you like to register? (yes/no): ");
        String userResponse = scanner.next().toLowerCase();
        
        if(userResponse.equals("yes")) {
        	System.out.println("Enter your Username: ");
        	String userName = scanner.next();
        	
        	String userId = "U" + String.format("%04d", library.getUsers().size() + 1);
        	
        	//creating new user and add to  library
        	User  newUser = new User(userName, userId);
        	library.addUser(newUser);
        	
        	 System.out.println("User registered successfully! Welcome, " + userName + " (your Id is: " + userId + ")");
        } else {
            System.out.println("You chose not to register.");
        }
        
        //display all users
        
        System.out.println("\nRegistered Users:");
        library.getUsers().forEach(user -> System.out.println(user.getName() + " (ID: " + user.getId() + ")"));
        library.displayAllUsers();

        System.out.println("\nLoading library items from file...\n");

        // Load items from file directory
        String fileName = "/Users/marliyatukamara/Documents/java_week12/inputFilesForJava/libraryItemsColection.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    switch (parts[0]) {
                        case "Book":
                            library.addLibraryItem(new Book(
                                parts[1].trim(), // Title
                                parts[2].trim(), // ID
                                parts[3].trim(), // Author
                                parts[4].trim().equals("null") ? null : parts[4], // Borrower
                                Double.parseDouble(parts[5]), // Price
                                parts[6].trim() // Year
                            ));
                            break;

                        case "Ebook":
                            library.addLibraryItem(new Ebook(
                                parts[1].trim(), // Title
                                parts[2].trim(), // ID
                                parts[3].trim(), // Author
                                parts[4].trim().equals("null") ? null : parts[4], // Borrower
                                Double.parseDouble(parts[5]), // Price
                                parts[6].trim(), // Year
                                parts[7].trim() // Format
                            ));
                            break;

                        case "Movie":
                            library.addLibraryItem(new Movie(
                                parts[1].trim(), // Title
                                parts[2].trim(), // ID
                                parts[3].trim().equals("null") ? null : parts[3], // Borrower
                                parts[4].trim(), // Genre
                                parts[5].trim(), // Director
                                Integer.parseInt(parts[6]), // Duration
                                parts[7].trim() // Year
                            ));
                            break;

                        case "Magazine":
                            library.addLibraryItem(new Magazine(
                                parts[1].trim(), // Title
                                parts[2].trim(), // ID
                                parts[3].trim().equals("null") ? null : parts[3], // Borrower
                                parts[4].trim(), // Publisher
                                Integer.parseInt(parts[5]), // Pages
                                parts[6].trim() // Issue Date
                            ));
                            break;

                        default:
                            System.out.println("Unknown item type: " + parts[0].trim());
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

        System.out.println("\n\nDisplaying items below.........");
        library.displayAllItems();

        System.out.println("\nBorrowing items######");
        library.borrowItem("U001", "B001"); // Alice borrows the book
        library.returnItem("U001", "B001"); // Alice returns the book

        System.out.println("\nChecking item out");
        library.checkOutItem("E002", "Marli");

        System.out.println("\nDisplaying details after checkout below.........");
        library.displayAllItems(); // displaying details after checkout

        library.removeById("B001");// this is remove therefore it wont be printed in the available item in collection
        System.out.println("\nDisplaying all available items below.........");

        library.displayAllItems();

        
        System.out.println("\nAttempting to renew...");
        library.renewItem("E002");

        System.out.println("\nEbook details after renewal...");
        library.findItemById("E002").displayDetails(); // Verify item details after renewal

        scanner.close();
    }
}
