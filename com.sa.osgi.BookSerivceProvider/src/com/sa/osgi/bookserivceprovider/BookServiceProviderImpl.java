package com.sa.osgi.bookserivceprovider;

import java.util.ArrayList;
import java.util.List;

public class BookServiceProviderImpl implements IBookServiceProvider {
	
	private List<Book> bookRepository;
	
	public BookServiceProviderImpl() {
		
		bookRepository = new ArrayList<Book>();
                
        bookRepository.add(new Book().setDetails("The Alchemist", 10.25, "Paulo Coelho", 25, "Penguin"));
        bookRepository.add(new Book().setDetails("Mindset", 25.25, "Carol Dweck", 50, "Haper Collins"));
        bookRepository.add(new Book().setDetails("Percy Jackson - The Lightning Thief", 15.75, "Rick Riordan", 45, "Disney"));
        //bookRepository.add(new Book().setDetails("Percy Jackson - The Sea of Monsters", 15.75, "Rick Riordan", 45, "Disney"));
        bookRepository.add(new Book().setDetails("Mathematical Mindsets", 20.25, "Carol Dweck", 25, "Haper Collins"));
	}

	private Book GetBookByTitle(String title) {
		
		for (Book book : bookRepository) {
            if (book.getTitle().equalsIgnoreCase(title)) {
            	return book;
            }
        }
		
		return new Book();
	}
	
	@Override
	public boolean CheckAvailability(String title) {
				
		return (GetBookByTitle(title).getNumOfCopies() > 0);
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
	       
		for (Book book : bookRepository) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
            	book.printBookRecord();
            }
        }				
	}

	@Override
	public void SearchBookByPublisher(String publisher) {
		
		for (Book book : bookRepository) {
            if (book.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
            	book.printBookRecord();
            }
        }	
		
	}

	@Override
	public void AddBook(String title, double price, String author, int numOfCopies, String publisher) {
		
		this.bookRepository.add(new Book().setDetails(title, price, author, numOfCopies, publisher));
	}

	@Override
	public void UpdateBook(String title, double price, String author, int numOfCopies, String publisher) {
		
		DeleteBookByTitle(title);		
		this.bookRepository.add(new Book().setDetails(title, price, author, numOfCopies, publisher));
		
	}

	@Override
	public void DeleteBookByTitle(String title) {
				
		Book delBook = GetBookByTitle(title);
		this.bookRepository.remove(delBook);
		
	}
	
}