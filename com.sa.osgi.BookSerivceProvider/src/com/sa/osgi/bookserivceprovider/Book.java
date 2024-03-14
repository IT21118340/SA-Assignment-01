package com.sa.osgi.bookserivceprovider;

public class Book {
	
	// Properties
	private String title;
	private double price;
	private String author;
	private int numOfCopies;
	private String publisher;
	

	// Methods
	
	public Book setDetails(String title, double price, String author, int numOfCopies, String publisher) {
		this.title = title;
		this.price = price;
		this.author = author;
		this.numOfCopies = numOfCopies;
		this.publisher = publisher;
		
		return this;
	}

	public String getTitle() {
		return this.title;		
	}
	
	public int getNumOfCopies() {
		return this.numOfCopies;		
	}
	
	public String getAuthor() {
		return this.author;
	}	
	
	public String getPublisher() {
		return this.publisher;
	}	
	
	public double getPrice() {
		return this.price;		
	}
	
	public void decreaseQuantity(int num) {
		this.numOfCopies = numOfCopies - num;
	}
	
	public void printBookRecord() {
		System.out.println(" - " + this.title + " | " + this.author + " | " + this.price + " | " + this.numOfCopies + " | " + this.publisher);
	}
	
	public void printBookDetails() {
	    System.out.println("Title: " + this.title);
	    System.out.println("Author: " + this.author);
	    System.out.println("Price: " + this.price);
	    System.out.println("Number of Copies: " + this.numOfCopies);
	    System.out.println("Publisher: " + this.publisher);
	}
}
