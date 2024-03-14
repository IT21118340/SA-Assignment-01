package com.sa.osgi.bookserivceconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import java.util.Scanner;

import com.sa.osgi.bookserivceprovider.IBookServiceProvider;

public class Activator implements BundleActivator {

	private ServiceTracker<IBookServiceProvider, IBookServiceProvider> bookServiceTracker; 
    private ServiceReference<?> serviceReference;
    private IBookServiceProvider bookServiceProvider;       

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("@ Book Service Consumer Started..");				         
        
		// Track book service
        bookServiceTracker = new ServiceTracker<>(bundleContext, IBookServiceProvider.class, null);
        bookServiceTracker.open();
        
        // Check if book service is available
        if (bookServiceTracker.getService() != null) {

            bookServiceProvider = bookServiceTracker.getService();
            
        } else {
        	
            System.out.println("!!! Service unavailable...");
            
            try {
				stop(bundleContext);
			} catch (Exception e) {				
				e.printStackTrace();
			}
        }

        mainMenuUI(bundleContext);
        
	}
			
	public void stop(BundleContext bundleContext) throws Exception {
	
		System.out.println("\n@ Book Service Consumer Stopped... \n");
			
		bundleContext.ungetService(serviceReference);
	}
	
	private void mainMenuUI(BundleContext bundleContext) {
		
		Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
        	System.out.println("\n===== Rathna Book Store =====");
    		System.out.println("\n-----Main Menu -----");
            System.out.println("1. Search Books By Author");
            System.out.println("2. Search Books By Publisher");
            System.out.println("3. Search Book By Title");
            System.out.println("4. Check Book Availability");
            System.out.println("5. Add new book");
            System.out.println("6. Update book by title");
            System.out.println("7. Delete book by title");
            System.out.println("8. Exit");
            System.out.print("\n> Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    searchBookByAuthor(scanner);
                    break;
                case 2:
                    searchBookByPublisher(scanner);
                    break;
                case 3:
                	searchBookByTitle(scanner);
                    break;
                case 4:
                    checkBookAvailability(scanner);
                    break;
                case 5:
                    addNewBook(scanner);
                    break;
                case 6:
                    updateBookByTitle(scanner);
                    break;
                case 7:
                	deleteBookByTitle(scanner);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("$ Invalid choice. Please try again.");
            }
        }      
        
        if(exit)
        	scanner.close();
        	try {
				stop(bundleContext);
			} catch (Exception e) {						
				e.printStackTrace();
			}
	}

	private void searchBookByAuthor(Scanner scanner) {
        
		System.out.println("\n-----Search Books by Author-----");
		System.out.print("\n> Enter author's name: ");
        String author = scanner.nextLine();
        
        System.out.println("$ Book by the author: ");
        
        this.bookServiceProvider.SearchBookByAuthor(author);
        
        System.out.println("\n> Press Enter to return to main menu...");
        scanner.nextLine();
    }	
	
	private void searchBookByPublisher(Scanner scanner) {

		System.out.println("\n-----Search Books by Publisher-----");
		System.out.print("\n> Enter publisher's name: ");
        String publisher = scanner.nextLine();
        
        System.out.println("$ Book by the Publisher: ");
        
        this.bookServiceProvider.SearchBookByPublisher(publisher);
        
        System.out.println("\n> Press Enter to return to main menu...");
        scanner.nextLine();
		
	}
	
	private void searchBookByTitle(Scanner scanner) {
       
		System.out.println("\n-----Search Book by Title-----");
		System.out.print("\n> Enter book title: ");
        String title = scanner.nextLine();
        
        System.out.println("$ Book Details: ");
        
        this.bookServiceProvider.SearchBookByTitle(title);
        
        System.out.println("\n> Press Enter to return to main menu...");
        scanner.nextLine();
    }
	
	private void checkBookAvailability(Scanner scanner) {
        
		System.out.println("\n-----Check Book Availability-----");
		System.out.print("\n> Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("$ Book Availability: ");
        System.out.println((this.bookServiceProvider.CheckAvailability(title))? "Available" : "Not Availble");
                     
        System.out.println("\n> Press Enter to return to main menu...");
        scanner.nextLine();
    }
		
	private void deleteBookByTitle(Scanner input) {
		
	    System.out.println("\n-----Delete Book by Title-----");
		System.out.print("\n> Enter book title: ");
        String title = input.nextLine();
        
        this.bookServiceProvider.DeleteBookByTitle(title);
        
        System.out.println("\n> Book deleted successfully.");
        System.out.println("\n> Press Enter to return to main menu...");
        input.nextLine();        
		
	}

	private void updateBookByTitle(Scanner input) {
		
	    System.out.println("\n-----Update Book by Title-----");
	    
	    System.out.print("\n> Enter book title: ");
	    String title = input.nextLine();	    

	    System.out.print("\n> Enter author: ");
	    String author = input.nextLine();	    	    	    	 
	    
	    System.out.print("\n> Enter publisher: ");
	    String publisher = input.nextLine();
	    
	    System.out.print("\n> Enter new price: ");
	    double price = input.nextDouble();	    
	    
	    System.out.print("\n> Enter number of copies: ");
	    int numOfCopies = input.nextInt();
	    
	    this.bookServiceProvider.UpdateBook(title, price, author, numOfCopies, publisher);
	    
	    System.out.println("\n> Book updated successfully.");
	    System.out.println("\n> Press Enter to return to main menu...");
	    input.nextLine(); // Consume newline left-over

	}


	private void addNewBook(Scanner input) {
		
	    System.out.println("\n-----Add New Book-----");
	    
	    System.out.print("\n> Enter book title: ");
	    String title = input.nextLine();
	    
	    System.out.print("\n> Enter author: ");
	    String author = input.nextLine();	    	    	    	 
	    
	    System.out.print("\n> Enter publisher: ");
	    String publisher = input.nextLine();
	    
	    System.out.print("\n> Enter new price: ");
	    double price = input.nextDouble();
	    
	    System.out.print("\n> Enter number of copies: ");
	    int numOfCopies = input.nextInt();
	    
	    this.bookServiceProvider.AddBook(title, price, author, numOfCopies, publisher);
	    
	    System.out.println("\n> New book added successfully.");
	    System.out.println("\n> Press Enter to return to main menu...");
	    input.nextLine(); // Consume newline left-over
	}

}
