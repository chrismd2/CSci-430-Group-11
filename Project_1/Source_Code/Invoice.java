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
		invoiceItems = new ItemList();
		invoiceAmount = 0.0f;
	}//end Constructor

	public void addItem(OrderedItem item){
		invoiceItems.addItem(item);
		invoiceAmount += item.getQuantity() * item.getProduct().getSalePrice();
	}//end addItem

	//getInvoiceAmount:
	public double getInvoiceAmount(){
		return invoiceAmount;
	}//end getInvoiceAmount

	public void applyInvoice(){
		clientAccount.addInvoice(this);
	}//end applyInvoice

	public String toString(){
		return "\nClient id: " + clientAccount.getId() + " Amount: " + invoiceAmount + " Date " + date;
	}//end toString
	
	/*********************************************************************************
	detailedString()
	Returns a string consisting of the contents of toString() AND the list of products
	on the invoice
	***********************************************************************************/
	public String detailedString(){
		return toString() + '\n' + invoiceItems.toString();
	}//end detailedString()
}
