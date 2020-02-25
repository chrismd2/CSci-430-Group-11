/******************************************************************************
	Invoice.java
	Responsible Individual: Brent Clapp
	Contains all fields and methods associated with a Invoice.
	fields:
		clientInvoice: The amount of the Invoice that the client has made.
		clientAccount: A reference to the Client's account object
		date: The date of this Invoice
		Order: A reference to the order that this Invoice was given for.
	methods:
		sendToAccount: Sends the Invoice to the client's account, applying it to their balance.
*******************************************************************************/
package Source_Code;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
	private double invoiceAmount;
	private Client clientAccount;
	private String date;
	private ItemList invoiceItems;

	//Constructor:
	public Invoice(Client clientAccount, Order order){
		this.clientAccount = clientAccount;
		date = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
		invoicesItems = new ItemList();
		invoiceAmount = 0.0f;
	}//end Constructor

	/************************************************************
		sendToAccount

		Sends the Invoice to the client's account, modifies the applied flag to specify that a Invoice has been made.

		If the applied flag is set to true when called, then the method won't apply the balance.
	*************************************************************/
	public void sendToAccount(){
			clientAccount.addInvoice(this);
	}//end sendToAccount

	public void addItem(OrderedItem item){
		invoiceItems.add(item);
	}//end addItem

	//getInvoiceAmount:
	public double getInvoiceAmount(){
		return invoiceAmount;
	}//end getInvoiceAmount

	public void applyInvoice(){
		Iterator it = invoiceItems.getIterator();
		while(it.hasNext() ){
			invoiceAmount += it.next().getProduct().getSalePrice();
		}//end while
		clientAccount.addInvoice(this);
	}//end applyInvoice

	public String toString(){
		return "\nClient id: " + clientAccount.getId() + " Amount: " + invoiceAmount + " Date " + date;
	}//end toString
	
}
