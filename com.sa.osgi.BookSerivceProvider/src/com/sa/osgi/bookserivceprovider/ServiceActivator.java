package com.sa.osgi.bookserivceprovider;

import java.net.HttpURLConnection;
import java.net.URL;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {
	
	ServiceRegistration<?> serviceRegister;

	public void start(BundleContext bundleContext) throws Exception {		
		
		System.out.println("@ Book Service Provider Started..\n");
		
		IBookServiceProvider bookServiceProviderRemoteDB = new BookServiceProviderImpl();
		IBookServiceProvider bookServiceProviderLocalDB = new BookServiceProviderLocalDBImpl();		
			
		 if(isNetworkAvailable()) {			
			 serviceRegister = bundleContext.registerService(IBookServiceProvider.class.getName(), bookServiceProviderRemoteDB, null);
		 } else {
			 serviceRegister = bundleContext.registerService(IBookServiceProvider.class.getName(), bookServiceProviderLocalDB, null);
		 }
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("\n@ Book Service Provider Stopped..\n");		
		serviceRegister.unregister();
	}
	
	public static boolean isNetworkAvailable() {
		
        try {
        	
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000);
            urlc.connect();
            
            return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
            
        } catch (Exception e) {
        	
        	e.getStackTrace();
        }
        
        return false;
    }
	

}
