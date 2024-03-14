package com.sa.osgi.orderprocessingserivceprovider;

public interface IOrderProcessingServiceProvider {
	void createOrder();
	void inputBookDetails(String title, double bookPrice, int bookQuantity);
	void displayOrder();
}
