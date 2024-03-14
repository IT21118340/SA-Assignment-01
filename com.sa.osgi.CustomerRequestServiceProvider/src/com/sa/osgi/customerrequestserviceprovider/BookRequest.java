package com.sa.osgi.customerrequestserviceprovider;

public class BookRequest {
	
	private String bookTitle;
	private String bookAuthor;
	private String bookPublisher;
	
	public void setBookDetails(String title, String author, String publisher) {
		this.bookTitle = title;
		this.bookAuthor = author;
		this.bookPublisher = publisher;
	}
	
	public void printDetails() {
		System.out.println(" - " + this.bookTitle + " | " + this.bookAuthor + " | " + this.bookPublisher + " | ");	
	}

}
