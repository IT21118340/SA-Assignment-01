package com.sa.osgi.orderprocessingserivceconsumer;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import com.sa.osgi.orderprocessingserivceprovider.IOrderProcessingServiceProvider;

public class Activator implements BundleActivator {

	private ServiceReference<?> serviceReference;
	private IOrderProcessingServiceProvider iSerivceProvider;
	private ServiceTracker<IOrderProcessingServiceProvider, IOrderProcessingServiceProvider> serviceTracker;
    
    public void start(BundleContext bundleContext) throws Exception {
        
    	// Track service
    	serviceTracker = new ServiceTracker<>(bundleContext, IOrderProcessingServiceProvider.class, null);
    	serviceTracker.open();        
    	iSerivceProvider = serviceTracker.getService();

        if (iSerivceProvider != null) {
            mainUI();
        } else {
            System.out.println("$ Service not available. Cannot create book order.");
        }
    }
	
	private void mainUI() {
		Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
        	
        	System.out.println("\n----- Rathna Book Store -----\n");
            System.out.println("# Main Menu:\n");
            System.out.println("1. Create order");
            System.out.println("0. Exit");
            
            System.out.print("\n> Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (choice == 1) {
                createBookOrder(scanner);
            } else if (choice == 0) {
                exit = true;
            } else {
                System.out.println("$ Invalid choice. Please try again.");
                scanner.nextLine();
            }
        }

        scanner.close();
	}
	
	private void createBookOrder(Scanner scanner) {
		
		System.out.println("\n# Create Order:\n");
        
        iSerivceProvider.createOrder();

        System.out.print("## Enter Book Details:");       
        
        boolean done = false;
        while (!done) {
        	        	        	
            System.out.print("\n\n> Title: ");
            String title = scanner.nextLine();

            System.out.print("> Price: ");
            double price = scanner.nextDouble();

            System.out.print("> Quantity: ");
            int quantity = scanner.nextInt();
            
            iSerivceProvider.inputBookDetails(title, price, quantity);
            
            System.out.print("> Want add more books? (1. Yes / 0. No):");
            done = (scanner.nextInt() == 0)? true : false;     
            
            
        }
        
        System.out.println("\n-----Order Details----\n");
        iSerivceProvider.displayOrder();
        
        System.out.println("\n\nPress Enter to goto main menu..\n");
        scanner.nextLine(); 
	}

    public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("\n$ Order Processing Service Consumer Stopped...");		
		bundleContext.ungetService(serviceReference);
	}

}
