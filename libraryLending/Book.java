package libraryLending;

import java.time.LocalDate;

public class Book extends LibraryItem implements ItemRentable {
	
	protected String author;
	protected double prices;
	protected String yearOfPublication;
	
	
	public Book(String title, String id, String author, String personBorrow, double prices, String yearOfPublication) {
		super(title, id, personBorrow);
		this.author = author;
		this.prices = prices;
		this.yearOfPublication =  yearOfPublication;
	}
	public Book() {
		
	}
	//getter and setter
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setprices(double prices){
		this.prices = prices;
	}
	public void setYearOfPublicationr(String yop) {
		this.yearOfPublication = yop;
	}
	
	public String getYearOfPublication() {
		return yearOfPublication;
	}
	public double getPrices() {
		return prices;
	}
	public String getAuthor() {
		return author;
	}
	
	
	@Override
	public void displayDetails() {
	    System.out.println("\nBook Title: " + title + ",Book ID: " + id +
	            ", Author: " + author + ", Year published : " + yearOfPublication +
	            ",Person Borrowed: " + getPersonBorrow() +", Available: " + getIsAvailable() + 
	            (itemDueDate != null ? ", Due Date: " + itemDueDate : ""));
	}
	
	@Override
	public void checkOutItem() {
		// TODO Auto-generated method stub
		if(getIsAvailable()) {
			super.checkOutItem(id);
			itemDueDate = LocalDate.now().plusWeeks(4);
			System.out.println("Book checked out. Due Date: " + itemDueDate);
		}else {
			System.out.println("The book is not available for checkout.");
		}
		
	}
	
	
	@Override
	public boolean renewItem() {
	    if (!getIsAvailable() && itemDueDate != null) { // Item must be checked out
	        itemDueDate = itemDueDate.plusWeeks(2); // Extend due date
	        System.out.println("Item  with id: " + id + " renewed successfully. New Due Date: " + itemDueDate);
	        return true;
	    } else {
	        System.out.println("Item cannot be renewed. It is either not checked out or overdue.");
	        return false;
	    }
	}



}
