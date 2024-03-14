package com.sa.osgi.customerrequestserviceconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import com.sa.osgi.customerrequestserviceprovider.ICustomerRequestServiceProvider;

public class Activator implements BundleActivator {

	private ServiceReference<?> serviceReference;
    private ICustomerRequestServiceProvider iSerivceProvider;
	private ServiceTracker<ICustomerRequestServiceProvider, ICustomerRequestServiceProvider> serviceTracker;

	public void start(BundleContext bundleContext) throws Exception {
		
		// Track service
		serviceTracker = new ServiceTracker<>(bundleContext, ICustomerRequestServiceProvider.class, null);
		serviceTracker.open();        
		iSerivceProvider = serviceTracker.getService();
		
		Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
        	
        	System.out.println("\n----- Rathna Book Store -----\n");
            System.out.println("# Main Menu:\n");
            System.out.println("1. Create a request for books");
            System.out.println("2. View books by request frequency");
            System.out.println("0. Exit");
            
            System.out.print("\n> Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (choice == 1) {
                createBookRequest(scanner);
            }  else if (choice == 2) {
                viewBooksByRequestFrequency(scanner);
            } else if (choice == 0) {
                exit = true;
            } else {
                System.out.println("$ Invalid choice. Please try again.");
            }
        }

        scanner.close();			
	}

	private void createBookRequest(Scanner scanner) {
		
		System.out.println("\n# Create Request:\n");
        System.out.print("> Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("> Enter customer email: ");
        String customerEmail = scanner.nextLine();
        
        iSerivceProvider.setUserDetails(customerName, customerEmail);

        System.out.println("\n## Enter Book Details:");
       
        boolean done = false;
        while (!done) {
        	
            System.out.print("\n> Title: ");
            String title = scanner.nextLine();

            System.out.print("> Author: ");
            String author = scanner.nextLine();

            System.out.print("> Publisher: ");
            String publisher = scanner.nextLine();
            
            iSerivceProvider.inputBookDetails(title, author, publisher);
            
            System.out.print("> Want add more books? : ");
            done = (scanner.nextLine().equalsIgnoreCase("No"))? true : false;            
            
        }
        
        System.out.println("\n-----Request Details----\n");
        iSerivceProvider.displayRequest();
        
        System.out.println("\n\n> Press Enter to goto main menu..");
        iSerivceProvider.clearRequestList();
        scanner.nextLine(); 
        
    }
	
	private void viewBooksByRequestFrequency(Scanner scanner) {
		System.out.println("\n----- Books by Request Frequency -----\n");
		iSerivceProvider.displayMostRequestedBooks();
		System.out.println("\n\n> Press Enter to goto main menu..");
        scanner.nextLine(); 
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("\n$ Customer Request Service Consumer Stopped...");		
		bundleContext.ungetService(serviceReference);
	}

}
