/******************************************************************************
	InvoiceList.java
	Responsible Individual: Brent Clapp
	Contains all of the invoices for a single client.
	fields:
		invoices - The list of all invoices for the clients account
	methods:
		default Constructor: Creates an empty InvoiceList
		addInvoice: Adds the given Invoice to the list.
		removeInvoice: Removes the given Invoice from the list.
		getIterator: Returns an iterator that will iterate across each Invoice object.
******************************************************************************/
package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class InvoiceList implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Invoice> invoices;

	public InvoiceList(){
		invoices = new ArrayList<Invoice>();
	}//end InvoiceList

	public void addInvoice(Invoice in){
		invoices.add(in);
	}//end addInvoice

	public void removeInvoice(Invoice in){
		invoices.remove(in);
	}//end removeInvoice

	public Iterator<Invoice> getIterator(){
		return invoices.iterator();
	}//end getIterator

	public String toString(){
		return invoices.toString();
	}//end toString
}
