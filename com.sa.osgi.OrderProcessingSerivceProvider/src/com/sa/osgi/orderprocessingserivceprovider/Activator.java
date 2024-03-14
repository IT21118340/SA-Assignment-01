package com.sa.osgi.orderprocessingserivceprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration<?> serviceRegister;

	public void start(BundleContext bundleContext) throws Exception {
		
		//System.out.println("Order Processing Service Provider Started..");
		
		IOrderProcessingServiceProvider serviceProvider = new OrderProcessingServiceProviderImpl();
		serviceRegister = bundleContext.registerService(IOrderProcessingServiceProvider.class.getName(), serviceProvider, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Order Processing Service Provider Stopped..");		
		serviceRegister.unregister();
	}

}
