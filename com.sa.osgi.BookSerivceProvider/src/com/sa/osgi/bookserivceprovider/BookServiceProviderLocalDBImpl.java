package com.sa.osgi.bookserivceprovider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceProviderLocalDBImpl implements IBookServiceProvider {
	
	private Connection dbcon;
	
	public BookServiceProviderLocalDBImpl() {
		
		this.dbcon = DBConnection.ConnectToDB();
	}

	public Book GetBookByTitle(String title) {
		
		String sql = "SELECT * FROM books WHERE Title=?";
		Book bookData = new Book();
        
        try{
        		PreparedStatement pstmt  = this.dbcon.prepareStatement(sql);	
        		pstmt.setString(1, title);
        		ResultSet rs = pstmt.executeQuery();        		 	
        			
        			if (rs.next()) { 
            			bookData.setDetails(
            					rs.getString("Title"),
            					rs.getDouble("Price"), 
            					rs.getString("Author"), 
            					rs.getInt("NumOfCopies"), 
            					rs.getString("Publisher"));
        		}        		        		        	            
        		
        } catch (SQLException e) {
        	
            System.out.println("Error fetching book by title: " + e.getMessage());
        }
        
        return bookData;
	}

	@Override
	public boolean CheckAvailability(String title) {
		return (GetBookByTitle(title).getNumOfCopies() > 0);
	}

	public List<Book> GetBookByAuthorOrPublisher(String name, String type) {
		
		String sql = null;
		
		if (type.equalsIgnoreCase("author")) {
			sql = "SELECT * FROM books WHERE Author=?";
		}
		
		if (type.equalsIgnoreCase("publisher")) {
			sql = "SELECT * FROM books WHERE Publisher=?";
		}
		
		List<Book> foundBooks = new ArrayList<Book>();
		Book bookData = new Book();
        
		try {
				PreparedStatement pstmt  = this.dbcon.prepareStatement(sql);	
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
								
        		while (rs.next()) { 
        			bookData.setDetails(rs.getString("Title"), rs.getDouble("Price"), rs.getString("Author"), rs.getInt("NumOfCopies"), rs.getString("Publisher"));
        			foundBooks.add(bookData);
        			bookData = new Book();
        		}            
        		
        } catch (SQLException e) {
        	
            System.out.println(e.getMessage());
        }
        
        return foundBooks;
    }

	@Override
	public void SearchBookByTitle(String title) {
		
		Book book = GetBookByTitle(title);
		
		if(	book.getTitle() != null) {
			book.printBookDetails();
		} else {
			System.out.println("Error 404: Item not found");
		}
	}

	@Override
	public void SearchBookByAuthor(String author) {		
		
		List<Book> bookList = GetBookByAuthorOrPublisher(author, "author");
		
		if(!(bookList.isEmpty())) {
			
			for (Book item : bookList) {
				item.printBookRecord();
			}
			
		} else {
			
			System.out.println("Error 404: Author not found");
		}				
	}

	@Override
	public void SearchBookByPublisher(String publisher) {

		List<Book> bookList = GetBookByAuthorOrPublisher(publisher, "publisher");
		
		if(!(bookList.isEmpty())) {
			
			for (Book item : bookList) {
				item.printBookRecord();
			}
			
		} else {
			
			System.out.println("Error 404: Publisher not found");
		}	
		
	}

	@Override
	public void AddBook(String title, double price, String author, int numOfCopies, String publisher) {
	    String sql = "INSERT INTO books (Title, Price, Author, NumOfCopies, Publisher) VALUES (?, ?, ?, ?, ?)";
	    
	    try {
	        PreparedStatement pstmt = this.dbcon.prepareStatement(sql);
	        pstmt.setString(1, title);
	        pstmt.setDouble(2, price);
	        pstmt.setString(3, author);
	        pstmt.setInt(4, numOfCopies);
	        pstmt.setString(5, publisher);
	        
	        pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	    	
	        System.out.println("Error adding book: " + e.getMessage());
	    }
	}

	@Override
	public void UpdateBook(String title, double price, String author, int numOfCopies, String publisher) {
	    String sql = "UPDATE books SET Price=?, Author=?, NumOfCopies=?, Publisher=? WHERE Title=?";
	    
	    try {
	        PreparedStatement pstmt = this.dbcon.prepareStatement(sql);
	        pstmt.setDouble(1, price);
	        pstmt.setString(2, author);
	        pstmt.setInt(3, numOfCopies);
	        pstmt.setString(4, publisher);
	        pstmt.setString(5, title);
	        
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error updating book: " + e.getMessage());
	    }
	}


	@Override
	public void DeleteBookByTitle(String title) {
	    String sql = "DELETE FROM books WHERE Title=?";
	    
	    try {
	        PreparedStatement pstmt = this.dbcon.prepareStatement(sql);
	        pstmt.setString(1, title);
	        
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error deleting book: " + e.getMessage());
	    }
	}

	
}