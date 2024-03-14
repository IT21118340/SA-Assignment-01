package com.sa.osgi.customerrequestserviceprovider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRequestServiceProviderImpl implements ICustomerRequestServiceProvider {

	private String name;
	private String email;
	private List<BookRequest> bookRequests;
	private Connection dbcon;
	
	public CustomerRequestServiceProviderImpl(){
		bookRequests = new ArrayList<BookRequest>();
		this.dbcon = DBConnection.ConnectToDB();
	}
	
	@Override
	public void setUserDetails(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public void inputBookDetails(String title, String author, String publisher) {
		
		BookRequest request = new BookRequest();
		request.setBookDetails(title, author, publisher);
		bookRequests.add(request);
		
		String sql = "INSERT INTO book_requests (title, author, publisher, amount) VALUES (?, ?, ?, 1) ON CONFLICT(title) DO UPDATE SET amount = amount + 1";
		
		try {
	        PreparedStatement pstmt = this.dbcon.prepareStatement(sql);
	        pstmt.setString(1, title);
	        pstmt.setString(2, author);
	        pstmt.setString(3, publisher);
	        
	        pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	    	
	        System.out.println("Error adding book request: " + e.getMessage());
	    }
	}

	@Override
	public void displayRequest() {		
		
		System.out.println("Customer Name: " + this.name);
		System.out.println("Customer Email: " + this.email);
		
		System.out.println("Request: ");
		for (BookRequest item : this.bookRequests) {
			item.printDetails();
		}
	}

	@Override
	public void clearRequestList() {

		this.bookRequests.clear();
	}

	@Override
	public void displayMostRequestedBooks() {

		String sql = "SELECT * FROM book_requests ORDER BY amount DESC";
		BookRequest bookData = new BookRequest();
        
        try{
        		PreparedStatement pstmt  = this.dbcon.prepareStatement(sql);
        		ResultSet rs = pstmt.executeQuery();        		 	
        			
        		while (rs.next()) { 
            			bookData.setBookDetails( rs.getString("Title"), rs.getString("Author"), rs.getString("Publisher"));
            			this.bookRequests.add(bookData);
            			bookData = new BookRequest();
        		}        		        		        	            
        		
        } catch (SQLException e) {
        	
            System.out.println("Error fetching book by title: " + e.getMessage());
        }  
        
		for (BookRequest item : this.bookRequests) {
			item.printDetails();
		}
		
	}		
}
