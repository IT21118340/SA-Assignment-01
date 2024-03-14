package com.sa.osgi.customerrequestserviceprovider;

public interface ICustomerRequestServiceProvider {
	
	void setUserDetails(String name, String email);
	void inputBookDetails(String title, String author, String publisher);
	void displayRequest();
	void displayMostRequestedBooks();
	void clearRequestList();
}
