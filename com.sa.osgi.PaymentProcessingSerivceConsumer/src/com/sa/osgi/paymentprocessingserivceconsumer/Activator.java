package com.sa.osgi.paymentprocessingserivceconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import com.sa.osgi.paymentprocessingserivceprovider.IPaymentProcessingSerivceProvider;

import java.util.Scanner;

public class Activator implements BundleActivator {

	private ServiceReference<?> serviceReference;
    private IPaymentProcessingSerivceProvider paymentProcessingSerivceProvider;
	private ServiceTracker<IPaymentProcessingSerivceProvider, IPaymentProcessingSerivceProvider> paymentServiceTracker;

	public void start(BundleContext bundleContext) throws Exception {
		
		// Track payment service
		paymentServiceTracker = new ServiceTracker<>(bundleContext, IPaymentProcessingSerivceProvider.class, null);
		paymentServiceTracker.open();        
		paymentProcessingSerivceProvider = paymentServiceTracker.getService();
		
		Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
        	System.out.println("\n----- Rathna Book Store -----\n");
            System.out.println("# Main Menu:\n");
            System.out.println("1. Choose Payment type");
            System.out.println("0. Exit");
            System.out.print("\n> Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handlePaymentType(scanner);
                    break;
                case 0:
                    keepRunning = false;
                    System.out.println("\n$ Exiting the system...");
                    break;
                default:
                    System.out.println("\n$ Invalid choice. Please try again...");
                    break;
            }
        }
        scanner.close();
	}

	private void handlePaymentType(Scanner scanner) {
		
		System.out.println("\n-----------------------------");
		System.out.println("\n# Choose Payment type:\n");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.print("\n> Enter your choice: ");
        int paymentType = scanner.nextInt();

        switch (paymentType) {
            case 1:
                handleCashPayment(scanner);
                break;
            case 2:
                handleCreditCardPayment(scanner);
                break;
            default:
                System.out.println("\n$ Invalid choice. Please try again...");
                break;
        }
    }

    private void handleCashPayment(Scanner scanner) {
    	
    	System.out.println("\n-----------------------------");
    	System.out.println("\n# Process Cash Payment:\n");
        System.out.print("> Enter total price for cash payment: ");
        double totalPrice = scanner.nextDouble();
        
        paymentProcessingSerivceProvider.processCashPayment(totalPrice);
        
        System.out.println("\n> Press any key to return to main menu.");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void handleCreditCardPayment(Scanner scanner) {
    	
    	System.out.println("\n-----------------------------");
    	System.out.println("\n# Process Credit Card Payment:\n");
        System.out.print("> Enter total price for credit card payment: ");
        double totalPrice = scanner.nextDouble();
        
        System.out.print("\n> Enter expiry date (MM/YY): ");
        String expiryDate = scanner.next();
        
        System.out.print("\n> Enter CCV number: ");
        int ccvNumber = scanner.nextInt();
        
        paymentProcessingSerivceProvider.processCreditCardPayment(totalPrice, expiryDate, ccvNumber);       
        
        System.out.println("\n> Press any key to return to main menu.");
        scanner.nextLine();
        scanner.nextLine();
    }
    
	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("\n$ Payment Processing Service Consumer Stopped...");		
		bundleContext.ungetService(serviceReference);
	}
	
	

}
