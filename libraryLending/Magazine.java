package libraryLending;

import java.time.LocalDate;

public class Magazine  extends LibraryItem{
	protected String publisher;
	protected int issueNum;
	protected String relesedate;
	
	public Magazine (String title, String id, String personBorrow,String publisher, int issueNum, String relesedate) {
		super(title, id, personBorrow);
		this.publisher = publisher;
		this.issueNum = issueNum;
		this.relesedate = relesedate;
	}
	
	public Magazine () {
		
	}
	
	public void setPublisher(String publ) {
		this.publisher = publ;
	}
	
	public void  setIssueNum(int isNum) {
		this.issueNum = isNum;
	}
	public void setRelesedate(String resdate) {
		this.relesedate = resdate;
	}

	
	public String getPublisher() {
		return publisher;
		
	}
	public int getIssueNum() {
		return issueNum;
		
	}
	public String getRelesedate() {
		return relesedate;
		
	}
	
	
	@Override
	public void displayDetails() {
        System.out.println("\nMagazine Title: " + title + ", ID: " + id + ", Publisher: " + publisher +
        		", Issue Number: " + issueNum + ", Relese date: " + relesedate);
    }

	@Override
	public void checkOutItem() {
		// TODO Auto-generated method stub
		if(getIsAvailable()) {
			super.checkOutItem(id);
			itemDueDate = LocalDate.now().plusWeeks(1);
			System.out.println("magazine checked out. Due Date: " + itemDueDate);
		}else {
			System.out.println("The magazine is not available for checkout.");
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
