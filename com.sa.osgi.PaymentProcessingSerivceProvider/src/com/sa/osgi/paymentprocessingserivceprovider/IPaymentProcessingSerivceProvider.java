package com.sa.osgi.paymentprocessingserivceprovider;

public interface IPaymentProcessingSerivceProvider {

	void processCashPayment(double total);
	void processCreditCardPayment(double total, String expireDate, int ccv);
}
