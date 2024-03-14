package com.sa.osgi.orderprocessingserivceprovider;

public class BookOrder {
	private String bookTitle;
	private double bookPrice;
	private int bookQuantity;
	
	public void setBookDetails(String title, double bookPrice, int bookQuantity) {
		this.bookTitle = title;
		this.bookPrice = bookPrice;
		this.bookQuantity = bookQuantity;
	}
	
	public void printDetails() {
		System.out.println(getDetails());	
	}
	
	public String getDetails() {
		return " - " + this.bookTitle + " | " + this.bookPrice + " | " + this.bookQuantity;	
	}
	
	public double orderSum() {
		return this.bookPrice * this.bookQuantity;		
	}
	
}
