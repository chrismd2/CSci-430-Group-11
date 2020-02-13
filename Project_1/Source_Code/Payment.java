/******************************************************************************
	Payment.java
	Responsible Individual: Brent Clapp
	Contains all fields and methods associated with a Payment.
	fields:
		clientPayment: The amount of the payment that the client has made.
		clientAccount: A reference to the Client's account object
		date: The date of this Payment
		PaymentNumber: The unique identifier number given to this Payment.
		applied: Specifies whether or not the payment has been applied to the Client's balance.
	methods:
		sendToAccount: Sends the payment to the client's account, applying it to their balance.
		removeFromAccount: Removes the payment from the client's account, removing it from their balance.
*******************************************************************************/
//package Project_1.Source_Code;
package Source_Code;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment{
	double clientPayment;
	Client clientAccount;
	String date;
	int paymentNumber;
	boolean applied;

	//Constructor:
	public Payment(double paymentMade, Client clientAccount, int paymentNumber){
		clientPayment = paymentMade;
		this.clientAccount = clientAccount;
		this.paymentNumber = paymentNumber;
		date = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
		applied = false;
	}//end Constructor

	/************************************************************
		sendToAccount

		Sends the payment to the client's account, modifies the applied flag to specify that a payment has been made.

		If the applied flag is set to true when called, then the method won't apply the balance.
	*************************************************************/
	public void sendToAccount(){
		if(applied == false){
			clientAccount.addPayment(this);
			applied = true;
		}//end if
		else
			System.out.println("Payment has already been applied to account; Cannot apply again");

	}//end sendToAccount

	/**************************************************************
		removeFromAccount
		Removes the payment from the Client's account.
		Modifies the applied flag to specify that it is not currently applied to client's balance

		If applied flag is set to false when called, then the method won't remove the balance.
	*****************************************************************/
	public void removeFromAccount(){
		if(applied){
			clientAccount.removePayment(this);
			applied = false;
		}//end if
		else
			System.out.println("Payment has is not currently applied to client's account;\nNo payment to be removed.");

	}//end removeFromAccount

	//getPaymentAmount:
	public double getPaymentAmount(){
		return clientPayment;
	}//end getPaymentAmount

}
