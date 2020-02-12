/*******************************************************************
PaymentList.java
Responsible Individual: Brent Clapp
Container for the list of payments that a single client has made.
	fields:
		Payments: The list of payments that the client has made.
	methods: 
		Default constructor: Creates an empty PaymentList
		addPayment: Adds a payment to the paymentList
		removePayment: Removes a payment from the paymentList
*******************************************************************/
package Project_1.Source_Code;

import java.util.ArrayList;
import java.util.Iterator;

public class PaymentList{
	ArrayList<Payment> payments;
	
	public PaymentList(){
		payments = new ArrayList<Payment>();
	}//end constructor
	
	public void addPayment(Payment t){
		payments.add(t);
	}//end addPayment
	
	public void removePayment(Payment t){
		payments.remove(t);
	}

	public Iterator<Payment> getIterator(){
		return payments.iterator();
	}//end getIterator
}