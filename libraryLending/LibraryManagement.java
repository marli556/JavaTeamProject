package libraryLending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryManagement {

    private ArrayList<LibraryItem> collection; // Collection to hold items
    private HashMap<String, User> users; 

    public LibraryManagement() {
        collection = new ArrayList<>();
        users = new HashMap<>();
    }

    // Method to add an item to the collection
    public void addLibraryItem(LibraryItem item) {
        collection.add(item);
        System.out.println("Item added to Library: " + item.getTitle());
    }
    
    
    //add a user to system
    public void addUser(User user) {
    	users.put(user.getId(), user);
    	System.out.println("\nNew user was added: " + user.getName());
    }

    // Method to get an item by ID
    public LibraryItem findItemById(String id) {
        for (LibraryItem item : collection) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        System.out.println("Item with ID " + id + " not found.");
        return null;
    }

    // Method to find an item by title
    public LibraryItem findItemByTitle(String title) {
        for (LibraryItem item : collection) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        System.out.println("Couldn't find Item with title: " + title);
        return null;
    }

    // Method to check out a library item by ID
    public void checkOutItem(String id, String borrowerName) {
        LibraryItem item = findItemById(id);
        if (item != null) {
            item.checkOutItem(borrowerName);
        }
    }

    // Method to return an item by ID
    public boolean returnItem(String id) {
        LibraryItem item = findItemById(id); // Find the item by ID
        if (item != null && !item.getIsAvailable()) {
            item.setIsAvailable(true); // Mark the item as available
            item.setPersonBorrow(null); // Clear the borrower
            item.setItemDueDate(null); // Clear the due date
            System.out.println("Item with ID: " + id + " has been returned and is now available.");
            return true;
        } else {
            System.out.println("Item with ID: " + id + " is either not checked out or does not exist.");
            return false;
        }
    }

    // Method to remove an item by ID
    public void removeById(String id) {
        LibraryItem item = findItemById(id);
        if (item != null) {
            collection.remove(item); // Remove item
            System.out.println("Item with ID " + id + " has been removed from collection.");
        } else {
            System.out.println("Item with ID " + id + " not found. Cannot remove.");
        }
    }

    // Method to display all items in the collection
    public void displayAllItems() {
        if (collection.isEmpty()) {
            System.out.println("There is no item in the collection.");
        } else {
            for (LibraryItem item : collection) {
                item.displayDetails();
                
            }
        }
    }

    // Method to renew an item by ID
    public void renewItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item != null) {
            if (!item.renewItem()) { // Renew and check for success
                System.out.println("Renewal failed for item ID: " + itemId);
            }
        } else {
            System.out.println("Item with ID: " + itemId + " not found.");
        }
    }

    
    
 // Find user by ID
    public User findUserById(String userId) {
        return users.get(userId);
    }
    
 // Borrow an item for a user
    public void borrowItem(String userId, String itemId) {
        User user = findUserById(userId);
        LibraryItem item = findItemById(itemId);
        if (user != null && item != null  && item.getIsAvailable()) {
            user.borrowItem(item);
        } else {
            System.out.println("Cannot borrow item.");
        }
    }

    // Return an item for a user
    public void returnItem(String userId, String itemId) {
        User user = findUserById(userId);
        LibraryItem item = findItemById(itemId);
        if (user != null && item != null) {
            user.returnItem(item);
        } else {
            System.out.println("Invalid user or item ID.");
        }
    }

    // Display all users
    public void displayAllUsers() {
        System.out.println("All registered users:");
        for (User user : users.values()) {
            System.out.println(user.getName() + " (ID: " + user.getId() + ")");
        }
    }

	public List<User>getUsers(){
		return new ArrayList<>(users.values());
		
	}
}
    
    
    
