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
			Client(int id, String name, String phoneNumber, String address)
		Setters:
			void setClientName(String name)
			void setClientId(int id)
			void setPhone(String phone)
			void setAddress(String address)
			void addPayment(Payment p)
			void removePayment(Payment p)
			void addInvoice(Invoice i)
			void removeInvoice(Invoice i)
		Getters:
			String getName()
			int getId()
			String getClientPhone()
			String getClientAddress()
			Iterator getInvoices()
			Iterator getPayments()
			Iterator getCart()
*****************************************************************/
import java.util.Iterator;
public class Client{
	String clientName;
	int clientId;
	double balance;
	String phoneNumber;
	String address;
	PaymentList myPayments;
	InvoiceList myInvoices;
	ItemList shoppingCart;
	
	//Client Constructor
	public Client(int id, String name, String phoneNumber, String address){
		balance = 0.0f; //Balance is empty by default
		shoppingCart = new ItemList();
		myInvoices = new InvoiceList();
		myPayments = new PaymentList();
		clientId = id;
		this.phoneNumber = phoneNumber;
		clientName = name;
		this.address = address;
	} //end constructor
	
	/**************** BEGIN SETTERS ***********************/
	//Client name:
	public void setClientName(String name){
		clientName = name;
	}//end setClientName
	
	//clientId:
	public void setClientId(int id){
		clientId = id;
	}//end setClientId
	
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
	}//end addPayment
	
	public void removePayment(Payment removedPayment){
		balance += removedPayment.getPaymentAmount();
	}//end removePayment
	
	/***************************
		myInvoices:
		Can add or remove Invoices
	*************************/
	public void addInvoice(Invoice newInvoice){
		balance += newInvoice.getInvoiceAmount();
	}//end addInvoice

	public void removeInvoice(Invoice removedInvoice){
		balance -= removedInvoice.getInvoiceAmount();
	}//end removeInvoice
	
	/*******************************************************
		shoppingCart:
		Can add an item or item list
		Can remove an item or item list
		Can change the quantity of an item
		Can clear all items from the cart
	*******************************************************/
		
/**************** COME BACK LATER TO DO THESE, MORE DETAILS PREFERRED ************/	
		
		
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
	
}//end Client class