package com.sa.osgi.bookserivceproviderlocaldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceProviderLocalDBImpl implements IBookServiceProviderLocalDB {
	
	private Connection dbcon;
	
	public BookServiceProviderLocalDBImpl() {
		
		this.dbcon = DBConnection.ConnectToDB();
	}

	@Override
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

	@Override
	public List<Book> GetBookByAuthor(String author) {
		
		String sql = "SELECT * FROM books WHERE Author=?";
		List<Book> foundBooks = new ArrayList<Book>();
		Book bookData = new Book();
        
		try {
				PreparedStatement pstmt  = this.dbcon.prepareStatement(sql);	
				pstmt.setString(1, author);
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
		
		List<Book> bookList = GetBookByAuthor(author);
		
		if(!(bookList.isEmpty())) {
			
			for (Book item : bookList) {
				item.printBookRecord();
			}
			
		} else {
			
			System.out.println("Error 404: Author not found");
		}				
	}
	
}