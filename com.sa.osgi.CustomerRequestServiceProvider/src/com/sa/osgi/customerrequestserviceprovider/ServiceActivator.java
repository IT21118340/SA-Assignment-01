package com.sa.osgi.customerrequestserviceprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegister;

	public void start(BundleContext bundleContext) throws Exception {
		
		//System.out.println("Customer Request Service Provider Started..");
		
		ICustomerRequestServiceProvider serviceProvider = new CustomerRequestServiceProviderImpl();
		serviceRegister = bundleContext.registerService(ICustomerRequestServiceProvider.class.getName(), serviceProvider, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Customer Request Service Provider Stopped..");		
		serviceRegister.unregister();
	}

}
