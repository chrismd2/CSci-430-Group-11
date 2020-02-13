/******************************************************************************
	Invoice.java
	Responsible Individual: Brent Clapp
	Contains all fields and methods associated with a Invoice.
	fields:
		clientInvoice: The amount of the Invoice that the client has made.
		clientAccount: A reference to the Client's account object
		date: The date of this Invoice
		Order: A reference to the order that this Invoice was given for.
		applied: Specifies whether or not the Invoice has been applied to the Client's balance.
	methods:
		sendToAccount: Sends the Invoice to the client's account, applying it to their balance.
		removeFromAccount: Removes the Invoice from the client's account, removing it from their balance.
*******************************************************************************/
//package Project_1.Source_Code;
package Source_Code;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Invoice{
	double invoiceAmount;
	Client clientAccount;
	String date;
	Order order;
	boolean applied;

	//Constructor:
	public Invoice(Client clientAccount, Order order){
		this.clientAccount = clientAccount;
		date = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
		this.order = order;
		applied = false;
		/***** Modify in the future to calculate the invoice: *****/
		invoiceAmount = 0.0f;
	}//end Constructor

	/************************************************************
		sendToAccount

		Sends the Invoice to the client's account, modifies the applied flag to specify that a Invoice has been made.

		If the applied flag is set to true when called, then the method won't apply the balance.
	*************************************************************/
	public void sendToAccount(){
		if(applied == false){
			clientAccount.addInvoice(this);
			applied = true;
		}//end if
		else
			System.out.println("Invoice has already been applied to account; Cannot apply again");

	}//end sendToAccount

	/**************************************************************
		removeFromAccount
		Removes the Invoice from the Client's account.
		Modifies the applied flag to specify that it is not currently applied to client's balance

		If applied flag is set to false when called, then the method won't remove the balance.
	*****************************************************************/
	public void removeFromAccount(){
		if(applied){
			clientAccount.removeInvoice(this);
			applied = false;
		}//end if
		else
			System.out.println("Invoice has is not currently applied to client's account;\nNo Invoice to be removed.");

	}//end removeFromAccount

	//getInvoiceAmount:
	public double getInvoiceAmount(){
		return invoiceAmount;
	}//end getInvoiceAmount

}
