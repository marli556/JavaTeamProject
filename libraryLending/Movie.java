package libraryLending;

import java.time.LocalDate;

public class Movie  extends LibraryItem{
	protected String genre;
	protected String director;
	protected int duration;
	protected String yearOfRelease;
	
	public Movie(String title, String id, String personBorrow, String genre,String director,int duration, String yearOfRelease) {
		super(title, id, personBorrow);
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.yearOfRelease = yearOfRelease;
	}
	
	public Movie() {
		//default
	}
	
	//setters and getters
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setYearOfRelease(String yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	public String getGenre() {
		return genre;
	}
	
	public String getDirector() {
		return director;
	}
	public String getYearOfRelease() {
		return yearOfRelease;
		
	}
	
	
	@Override
	public void displayDetails() {
		// TODO Auto-generated method stub
		System.out.println("\nMovie Title:" + title + ", Id: " + id + ", Duration: "+ duration +
				", Director: " + director +", Genre: "+ genre + ",Year Release: " + yearOfRelease + 
				", Available: " + getIsAvailable() + (itemDueDate != null ? ", Due Date: " + itemDueDate : ""));
		
	}

	@Override
	public void checkOutItem() {
		// TODO Auto-generated method stub
		if(getIsAvailable()) {
			super.checkOutItem(id);
			itemDueDate = LocalDate.now().plusWeeks(2);
			System.out.println("movie checked out. Due Date: " + itemDueDate);
		}else {
			System.out.println("The movie is not available for checkout.");
		}
		
	}

	@Override
	public boolean renewItem() {
		// TODO Auto-generated method stub
		if(itemDueDate != null && isOverdue()) {
			itemDueDate = LocalDate.now().plusWeeks(2);
			System.out.println("Movie date.  renew Due Date: " + itemDueDate);
			return true;
			
		}else {
			System.out.println("item cannot be renew. Item is either overdue or not checked out.");
		}
		return false;
	}
	

}
