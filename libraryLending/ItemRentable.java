package libraryLending;



public interface ItemRentable {
	
	void checkOutItem();//check out an item
	void returnItem();
	boolean isOverdue(); // Method to check if the item is overdue
    double calculateLateFee();
    boolean renewItem();
	

}
