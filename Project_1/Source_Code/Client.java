/*****************************************************************
Client.java
Responsible individual: Brent Clapp
Contains all of an individual client's information, and all of the methods to view
and modify that information.
	Fields:
		clientName: The name of the client
		balance: The client's current balance.
		phoneNumber: The phone number of the client
		address: The client's primary street address
		myPayments: The list of Payments on record for this client
		myInvoices: The list of invoices on record for this client.
		shoppingCart: The list of items that the client has placed in their shopping cart,
								but has not purchased yet.
	Methods:
		Constructor:
			Client(String name, String phoneNumber, String address)
		Setters:
			void setClientName(String name)
			void setPhone(String phone)
			void setAddress(String address)
			void addPayment(Payment p)
			void addInvoice(Invoice i)
		Getters:
			String getName()
			int getId()
			String getClientPhone()
			String getClientAddress()
			Iterator getInvoices()
			Iterator getPayments()
			Iterator getCart()
*****************************************************************/
package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	private String clientName;
	private int clientId;
	private double balance;
	private String phoneNumber;
	private String address;
	private PaymentList myPayments;
	private InvoiceList myInvoices;
	private ItemList shoppingCart;

	//Client Constructor
	public Client(String name, String phoneNumber, String address){
		balance = 0.0f; //Balance is empty by default
		shoppingCart = new ItemList();
		myInvoices = new InvoiceList();
		myPayments = new PaymentList();
		clientId = (ClientIdServer.instance() ).getId();
		this.phoneNumber = phoneNumber;
		clientName = name;
		this.address = address;
	} //end constructor

	/**************** BEGIN SETTERS ***********************/
	//Client name:
	public void setClientName(String name){
		clientName = name;
	}//end setClientName

	/***********************
		balance:
		Can only be modified when a Payment object is added to the list
		of Payments, or when an Invoice object is added to the list of Invoices.
		No public use is permitted. Must add a Payment or Invoice.
		Parameter:
			double change: The change in balance.
	***********************/
	private void modifyBalance(double change){
		balance += change;
	}//end addPayment

	//phoneNumber:
	public void setPhone(String phone){
		phoneNumber = phone;
	}//end setPhone

	//address:
	public void setAddress(String address){
		this.address = address;
	}//end setAddress

	/***************************
		myPayments:
		Can add or remove Payments.
	****************************/
	public void addPayment(Payment newPayment){
		balance -= newPayment.getPaymentAmount();
		myPayments.addPayment(newPayment);
	}//end addPayment

	/***************************
		myInvoices:
		Can add or remove Invoices
	*************************/
	public void addInvoice(Invoice newInvoice){
		balance += newInvoice.getInvoiceAmount();
		myInvoices.addInvoice(newInvoice);
	}//end addInvoice

	/*************************************************************************
		shoppingCart methods:
		Can add an item
		Can change the quantity of an item
		Can clear all items from the cart
	*************************************************************************/

	/*******************************************************
		addToCart
		Given a product and a quantity, this method adds that
		item to the ShoppingCart
	********************************************************/
	public void addToCart(Product p, int quantity){
		shoppingCart.addItem(p, quantity);
	}//end endToCart
	
	/********************************************************
		changeItemQuantity
		Given a product that is already in the cart and a 
		quantity, this methods changes the quantity of that
		item.
	*******************************************************/
	public void changeItemQuantity(Product p, int quantity){
		shoppingCart.changeQuantity(p, quantity);
	}//end changeQuantity

	/********************************************************
		clearCart
		Removes all items from the shoppingCart.
		This method should be called whenever an order is placed.
	*********************************************************/
	public void clearCart(){
		shoppingCart.clear();
	}//end clearCart
	/**************** END SETTERS ************************/

	/****************** BEGIN GETTERS *********************/
	//clientName:
	public String getName(){
		return clientName;
	}//end getName

	//clientId:
	public int getId(){
		return clientId;
	}//end getId

	//balance:
	public double getClientBalance(){
		return balance;
	}//end getClientBalance

	//phoneNumber:
	public String getClientPhone(){
		return phoneNumber;
	}//end getClientPhone

	//address:
	public String getClientAddress(){
		return address;
	}//end getClientAddress

	//myPayments:
	public Iterator<Payment> getPayments(){
		return myPayments.getIterator();
	}//end getPayments

	//myInvoices:
	public Iterator<Invoice> getInvoices(){
		return myInvoices.getIterator();
	}//end getInvoices

	//shoppingCart:
	public Iterator<OrderedItem> getCart(){
		return shoppingCart.getIterator();
	}//end getCart
	/****************** END GETTERS ***********************/
	
	/**************************** Process functions ******************************/

	
	public String toString(){
		return "\nName: " + clientName + " | Id: " + clientId + " | Phone: " + phoneNumber + "\nAddress: " + address
               + "\nBalance: $" + balance;
	}//end toString
}//end Client class