package libraryLending;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class LibraryManagement {
    private HashMap<String, List<Book>> categorizedBooks; // Category -> List of Books
    private HashMap<String, User> users; // User ID -> User object

    // Path to the books file
    String booksPath = "/Users/marliyatukamara/Documents/java_week12/inputFilesForJava/books.csv"; 
    
    public LibraryManagement() {
        categorizedBooks = new HashMap<>();
        users = new HashMap<>();
    }

    // Method to load books from a file and categorize them by genre
    public void loadBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(booksPath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String category = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim()); // Prices should be a double
                    String year = parts[4].trim();

                    // Generate a unique ID for each book 
                    String id = UUID.randomUUID().toString(); 
                    String personBorrow = null; // Initially, no one has borrowed the book

                    // Creating a new Book object 
                    Book book = new Book(title, id, author, personBorrow, price, year);

                    // Add the book to the its category
                    categorizedBooks
                            .computeIfAbsent(category, k -> new ArrayList<>())
                            .add(book);
                }
            }
            //System.out.println("Books loaded and categorized successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Method to find item by ID
    public LibraryItem findItemById(String itemId) {
        // Loop through all categories in categorizedBooks
        for (Map.Entry<String, List<Book>> entry : categorizedBooks.entrySet()) {
            List<Book> books = entry.getValue();

            // Search for the book in the current category
            for (Book book : books) {
                if (book.getId().equals(itemId)) {
                    return book; // Found the book, return it
                }
            }
        }
        
        // If book with the given ID is not found
        System.out.println("Book with ID " + itemId + " not found.");
        return null; // Return null if not found
    }
    
    // Method to find item by title
    public LibraryItem findItemByTitle(String title) {
        // Loop through all categories in categorizedBooks
        for (Map.Entry<String, List<Book>> entry : categorizedBooks.entrySet()) {
            List<Book> books = entry.getValue();

            // Search for the book by title in the current category
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book; // Found the book by title, return it
                }
            }
        }
        
        // If book with the given title is not found
        System.out.println("Book with title \"" + title + "\" not found.");
        return null; // Return null if not found
    }
  
    // Method to display all categorized books
    public void displayBooksByCategory(String selectedCategory) {
        if (categorizedBooks.isEmpty()) {//if it's empty print
            System.out.println("No books in the library.");
            return;
        }
        // Display only the books in the selected category
        List<Book> booksInCategory = categorizedBooks.get(selectedCategory);
        if (booksInCategory != null) {
            System.out.println("\nBooks in " + selectedCategory + " category:");
            for (Book book : booksInCategory) {
                System.out.println("  " + book);
            }
        } else {
            System.out.println("No books found in the " + selectedCategory + " category.");
        }
    }
    
 // Add a book to a category
    public void addBookToCategory(String category, Book book) {
        categorizedBooks.computeIfAbsent(category, k -> new ArrayList<>()).add(book);
    }


    // Add a user to the system
    public void addUser(User user) {
        users.put(user.getId(), user);
    }
    
    // Find user by ID
    public User findUserByid(String userId) {
        return users.get(userId);
    }
    
    // Borrow an item for a user
    public void borrowItem(String userId, String category, String bookTitle) {
        User user = users.get(userId);
        List<Book> books = categorizedBooks.get(category);

        if (user != null && books != null) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookTitle) && book.getIsAvailable()) {
                    user.borrowItem(book);
                    book.setIsAvailable(false);
                    System.out.println(user.getName() + " borrowed the book: " + bookTitle);
                    return;
                }
            }
            System.out.println("Book not found or already borrowed.");
        } else {
            System.out.println("Invalid user ID or category.");
        }
    }

    // Return an item for a user
    public void returnItem(String userId, String category, String bookTitle) {
        User user = users.get(userId);
        List<Book> books = categorizedBooks.get(category);

        if (user != null && books != null) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookTitle) && !book.getIsAvailable()) {
                    user.returnItem(book);
                    book.setIsAvailable(true);
                    System.out.println(user.getName() + " returned the book: " + bookTitle);
                    return;
                }
            }
            System.out.println("Book not found or already available.");
        } else {
            System.out.println("Invalid user ID or category.");
        }
    }

    // Save all users to a file
    public void saveUsersToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // Overwrite the file (no append mode)
            for (User user : users.values()) {
                writer.write(user.getName() + "," + user.getId());
                writer.newLine();
            }
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    // Load users from the system
    public void loadUserFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0];
                    String id = parts[1];
                    User user = new User(name, id);
                    addUser(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user from file: " + e.getMessage());
        }
    }

    // Display all users
    public void displayAllUsers() {
        System.out.println("All registered users:");
        for (User user : users.values()) {
            System.out.println("User: " + user.getName());
        }
    }

    // Get users
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

 // This method retrieves books from the selected category
    public List<Book> getBooksByCategory(String selectedCategory) {
    	
        return categorizedBooks.getOrDefault(selectedCategory, new ArrayList<>());
    }

 
// Method to retrieve a book based on ID or title
    public List<Book> getBook(String userInput) {
        List<Book> allBooks = new ArrayList<>();

        // Iterate through all categories (keys) and add all books (values) to the list
        for (List<Book> books : categorizedBooks.values()) {
            allBooks.addAll(books);
        }
        String normalizedInput = userInput.trim().toLowerCase();

        // Return the list of books that match the input by ID or normalized title
        return allBooks.stream()
                .filter(book -> 
                    book.getId().equalsIgnoreCase(normalizedInput) || 
                    book.getTitle().trim().toLowerCase().equals(normalizedInput)
                )
                .collect(Collectors.toList());
    }

	
	
}
