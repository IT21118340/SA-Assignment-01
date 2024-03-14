package com.sa.osgi.bookserivceprovider;

public interface IBookServiceProvider {
	
	public void SearchBookByTitle(String title);
	public boolean CheckAvailability(String title);
	public void SearchBookByAuthor(String author);
	public void SearchBookByPublisher(String publisher);
	public void AddBook(String title, double price, String author, int numOfCopies, String publisher);
	public void UpdateBook(String title, double price, String author, int numOfCopies, String publisher);
	public void DeleteBookByTitle(String title);
}
