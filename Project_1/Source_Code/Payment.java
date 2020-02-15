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
package Source_Code;

import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	private double clientPayment;
	private Client clientAccount;
	private String date;
	private int paymentNumber;

	//Constructor:
	public Payment(double paymentMade, Client clientAccount, int paymentNumber){
		clientPayment = paymentMade;
		this.clientAccount = clientAccount;
		this.paymentNumber = paymentNumber;
		date = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
	}//end Constructor

	/************************************************************
		sendToAccount

		Sends the payment to the client's account, modifies the applied flag to specify that a payment has been made.

		If the applied flag is set to true when called, then the method won't apply the balance.
	*************************************************************/
	public void sendToAccount(){
			clientAccount.addPayment(this);
	}//end sendToAccount


	//getPaymentAmount:
	public double getPaymentAmount(){
		return clientPayment;
	}//end getPaymentAmount
	
	public String toString(){
		return "\nDate: " + date + " Amount: " + clientPayment;
	}//end toString

}
