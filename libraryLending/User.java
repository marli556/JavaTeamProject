package libraryLending;


import java.util.ArrayList;
import java.util.Objects;


public class User {
	protected String  name;
	protected String id;
	private static final int BORROW_LIMIT = 3;
	private ArrayList<LibraryItem> borrowedItems = new ArrayList<>();
	
	public User(String name, String userId) {
		this.name = name;
		this.id = userId;
		
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
	    if (borrowedItems.size() >= BORROW_LIMIT) {
	        System.out.println("Borrow limit reached. Return an item before borrowing more.");
	        return;
	    }
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
	    System.out.println("Borrowed items for user " + name + " (ID: " + id + "):");
	    if (borrowedItems.isEmpty()) {
	        System.out.println("No items borrowed.");
	    } else {
	        System.out.println("Title\t\tDue Date");
	        System.out.println("-------------------------");
	        for (LibraryItem item : borrowedItems) {
	            System.out.println(item.getTitle() + "\t\t" + item.getItemDueDate());
	        }
	    }
	}

   
   //equals method to compare User objects by ID
   @Override
   public boolean equals(Object obj) {
	   if(this == obj) return true;
	   if(obj == null || getClass() != obj.getClass()) return false;
	   User user = (User) obj;
	   return id.equals(user.id);
	   
   }
   
   //hashCod
   
   public int hashCode() {
	return Objects.hash(id);
	   
   }
public static int getBorrowLimit() {
	return BORROW_LIMIT;
}

}

