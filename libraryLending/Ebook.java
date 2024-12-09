package libraryLending;

public class Ebook extends Book {
	protected String fileFormat;
	
	public Ebook(String title, String id, String author,String personBorrow, double prices, String yearOfPublication, String fileFormat) {
		super(title, id, author, personBorrow, prices, yearOfPublication);
		this.fileFormat = fileFormat;
	}
	public Ebook() {
		//default constructor
	}
	
	//getters and setters
	public void setFileFormat(String format) {
		this.fileFormat = format;
	}
	
	public String getFileFormat() {
		return fileFormat;
		
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

	
	@Override
	public void displayDetails() {
	    System.out.println("\nEbook Title: " + title + ", Book ID: " + id +
	            ", Author: " + getAuthor() + ", Year published: " + getYearOfPublication() +
	            ", FileFormat"+ fileFormat  + ", Available: " + getIsAvailable()+
	            (itemDueDate != null ? ", Due Date: " + itemDueDate : ""));
	}

}
