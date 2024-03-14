package com.sa.osgi.orderprocessingserivceprovider;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessingServiceProviderImpl implements IOrderProcessingServiceProvider {

	private double total;
	private List<BookOrder> bookOrders;
	
	@Override
	public void createOrder() {		
		
		this.total = 0;
		this.bookOrders = new ArrayList<BookOrder>();
	}

	@Override
	public void inputBookDetails(String title, double bookPrice, int bookQuantity) {

		BookOrder order = new BookOrder();
		order.setBookDetails(title, bookPrice, bookQuantity);
		bookOrders.add(order); // add book
		this.total = this.total + order.orderSum();	// calc total
	}
	
	@Override
	public void displayOrder() {
		
		System.out.println("Order Total: " + this.total);
		
		System.out.println("Order: ");
		for (BookOrder item : this.bookOrders) {
			item.printDetails();
		}
		
		writeBookOrder();

	}
	
	private void writeBookOrder() {
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = now.format(formatter);
       
        String fileName = "D:/Workspace/_RathnaBookStore/order_" + formattedDateTime + ".txt";
        
        try (FileWriter fWriter = new FileWriter(fileName)) {
        	
        	fWriter.write("# Order Details: \n\n"); 
        	fWriter.append("* Order Total: LKR. " + this.total);

        	fWriter.append("\n-----Order Items-----\n]n");
            for (BookOrder item : this.bookOrders) {
            	fWriter.append(item.getDetails() + " | \n");
    		}
            fWriter.close(); 
            
        } catch (IOException e) {
        	
            System.out.print("An error occurred while writing to the file: " + e.getMessage());
        }

	}
}
