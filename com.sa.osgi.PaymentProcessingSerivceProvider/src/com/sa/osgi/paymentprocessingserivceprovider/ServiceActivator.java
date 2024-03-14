package com.sa.osgi.paymentprocessingserivceprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegister;

	public void start(BundleContext bundleContext) throws Exception {

		//System.out.println("Book Service Provider Started..");
		
		IPaymentProcessingSerivceProvider serviceProvider = new PaymentProcessingSerivceProviderImpl();			
		serviceRegister = bundleContext.registerService(IPaymentProcessingSerivceProvider.class.getName(), serviceProvider, null);
								
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Payment Processing Service Provider Stopped..");		
		serviceRegister.unregister();
	}

}
