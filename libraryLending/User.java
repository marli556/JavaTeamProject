package libraryLending;
import java.util.ArrayList;



public class User {
	protected String  name;
	protected String id;
	 private ArrayList<LibraryItem> borrowedItems = new ArrayList<>();
	
	public User(String n, String userId) {
		this.id = userId;
		this.name = n;
		
	}
	public User() {
		//default
	}
	
	//getter and setters
	
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
		
	}
	public String getName() {
		return name;
		
	}
	
	public ArrayList<LibraryItem> getBorrowedItems(){
		return borrowedItems;
		
	}
	

	public void borrowItem(LibraryItem item) {
        if (item != null && item.getIsAvailable()) {
        	item.checkOutItem(this.name);
            borrowedItems.add(item);
            
        } else {
            System.out.println("Cannot borrow item.");
        }
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
        	item.returnItem();
            borrowedItems.remove(item);
            
        } else {
            System.out.println("Item not borrowed by  user.  " + name);
        }
    }

    // Display borrowed items
   public void displayBorrowedItems() {
        System.out.println("Borrowed items for user " + name + ":");
        if (borrowedItems.isEmpty()) {
            System.out.println("No items borrowed.");
        } else {
            for (LibraryItem item : borrowedItems) {
                item.displayDetails(); 
            }
        }
    }

}

