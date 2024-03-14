package com.sa.osgi.bookserivceproviderlocaldb;

import java.util.List;

public interface IBookServiceProviderLocalDB {
	
	public Book GetBookByTitle(String title);
	public void SearchBookByTitle(String title);
	public boolean CheckAvailability(String title);
	public List<Book> GetBookByAuthor(String author);
	public void SearchBookByAuthor(String author);
}
