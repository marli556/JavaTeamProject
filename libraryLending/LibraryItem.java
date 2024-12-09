package libraryLending;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public abstract class LibraryItem implements ItemRentable {
	
    protected String title;
    protected String id;
    protected boolean isAvailable;
    protected LocalDate itemDueDate;
    protected String personBorrow;
    protected int renewCount;

    // Constructor with its parameter
    public LibraryItem(String title, String id, String personBorrow) {
        this.title = title;
        this.id = id;
        this.isAvailable = true;
        this.itemDueDate = null;
        this.personBorrow = personBorrow;
        this.renewCount = 0;
    }

    // Default constructor
    public LibraryItem() {
       
    }

    // Getters and setters
    public void setId(String id) {
        this.id = id;
    }
    
    public int getRenewCount() {
        return renewCount;
    }


    public void setPersonBorrow(String personBorrow) {
        this.personBorrow = personBorrow;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public String getPersonBorrow() {
        return personBorrow;
    }
    public LocalDate getItemDueDate() {
        return itemDueDate;
    }

    public void setItemDueDate(LocalDate itemDueDate) {
        this.itemDueDate = itemDueDate;
    }
    
    //method to increment the renewCount
    public void incrementRenewCount() {
    	this.renewCount++;
    	System.out.println("Incremented renewCount to: " + renewCount);
    }

    //methos to check if item were checkout or not
    public void checkOutItem(String borrowerName) { // Passing borrowerName as parameter 
        if (isAvailable) {
            isAvailable = false; // Mark as checked out
            itemDueDate = LocalDate.now().plusWeeks(2);// set to 2weeks
            this.personBorrow = borrowerName; // Assign borrower
            System.out.println("Item: "+ title + "  with id: " + id + " was checked out by: " + borrowerName + " on " + LocalDate.now() + ". Due date: " + itemDueDate);
        } else {
            System.out.println("Item is not available for checkout.");
        }
    }

    public void returnItem() {
        if (!isAvailable) {
            isAvailable = true; // Mark item as available
            itemDueDate = null; // Reset due date
            personBorrow = null; // Clear borrower info
            System.out.println("Item  with  id : "+  id + " has been returned and is now available.");
        } else {
            System.out.println("Item is already available and ready to be borrowed.");
        }
    }

    // Check for overdue items
    public boolean isOverdue() {
        if (!isAvailable && itemDueDate != null) {
            return LocalDate.now().isAfter(itemDueDate);
        }
        return false;
    }

    // Calculate late fee
    public double calculateLateFee() {
        if (isOverdue()) {
            long daysLate = ChronoUnit.DAYS.between(itemDueDate, LocalDate.now());
            return daysLate * 1.0; // $1 per day
        }
        return 0;
    }
    
 // Renew item
    public boolean renewItem() {
        if (renewCount >= 2) { // Check maximum renewal limit
            System.out.println("Item with id: " + id + " cannot be renewed anymore. Maximum renewals reached.");
            return false;
        }
        if (!isAvailable && itemDueDate != null && !isOverdue()) { // Ensure item is checked out and not overdue
            itemDueDate = itemDueDate.plusWeeks(2); // Extend due date
            incrementRenewCount(); // Increment renewal count
            System.out.println("Item with id: " + id + " renewed successfully. New Due Date: " + itemDueDate + " (Renewed " + renewCount + " times)");
            return true;
        } else if (isOverdue()) { 
            System.out.println("Item cannot be renewed because it is overdue.");
            return false;
        } else {
            System.out.println("Item cannot be renewed. It is either not checked out or has invalid data.");
            return false;
        }
    }

  
    
    @Override
    public String toString() {
        return "Title: " + title + ", ID: " + id + ", Available: " + isAvailable
            + (isAvailable ? "" : ", Borrower: " + personBorrow + ", Due Date: " + itemDueDate)
            + ", Renew Count: " + renewCount;
    }


    public abstract void displayDetails(); // Display item details
}
