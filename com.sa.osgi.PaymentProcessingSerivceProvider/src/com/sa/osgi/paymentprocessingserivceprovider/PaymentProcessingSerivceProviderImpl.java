package com.sa.osgi.paymentprocessingserivceprovider;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PaymentProcessingSerivceProviderImpl implements IPaymentProcessingSerivceProvider {

	@Override
	public void processCashPayment(double total) {
		
		String paymentStatus = (total > 0)? "completed": "not completed";								
		
		if(total > 0)
			writePaymentReceipt(total);
		
		System.out.println("\n$ Processing cash payment of LKR " + total + " is " + paymentStatus);	
	}

	@Override
	public void processCreditCardPayment(double total, String expireDate, int ccv) {
		
		boolean result = true;
		DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");							
		  		
	    try {
	    	
	        YearMonth expiryDate = YearMonth.parse(expireDate, ccMonthFormatter);
	        
	        if (YearMonth.now(ZoneId.systemDefault()).isAfter(expiryDate)) {
	            System.out.print("\n$ Credit card has expired");
	            result = false;
	        }
	        
	    } catch (DateTimeParseException dtpe) {
	    	
	        System.out.print("\n$ Invalid expiry date: " + expireDate);
	        result = false;
	    }
	    
	    String paymentStatus = (result)? "completed": "not completed";	
	    if(result)
			writePaymentReceipt(total);
	    System.out.println("\n$ Processing credit card payment of LKR " + total + " is " + paymentStatus);
	    
   
	}	
	
	private void writePaymentReceipt(double amount) {
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = now.format(formatter);
       
        String fileName = "D:/Workspace/_RathnaBookStore/payment_receipt_" + formattedDateTime + ".txt";
        String msg = "Payment of LKR " + amount + " at " + formattedDateTime + " to Rathna Store was successful.";
        
        try (FileWriter fWriter = new FileWriter(fileName)) {
        	
        	fWriter.write("Dear Customer, \n\n");
            fWriter.append("\t" + msg + System.lineSeparator());
            fWriter.close(); 
            
        } catch (IOException e) {
        	
            System.out.print("An error occurred while writing to the file: " + e.getMessage());
        }
        
    }
}
