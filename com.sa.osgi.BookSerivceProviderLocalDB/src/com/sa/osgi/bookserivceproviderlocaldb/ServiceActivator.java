package com.sa.osgi.bookserivceproviderlocaldb;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegister;

	public void start(BundleContext bundleContext) throws Exception {
		
		//System.out.println("Book Service Provider Started..");		
		
		IBookServiceProviderLocalDB bookServiceProviderLocalDB = new BookServiceProviderImpl();
		
		serviceRegister = bundleContext.registerService(IBookServiceProviderLocalDB.class.getName(), bookServiceProviderLocalDB, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Book Service Provider Stopped..");
		
		serviceRegister.unregister();
	}
}
