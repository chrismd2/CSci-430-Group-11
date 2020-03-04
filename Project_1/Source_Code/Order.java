/******************************************************************************
	Order.java
	Responsible Individual: Brent Clapp
	Class containing all of the fields and methods regarding an individual Order placed by a Client
	fields:
		orderNumber	- int
		date	- String
		clientAccount - Client
		itemsOrdered - ItemList
	methods:
		Order(Client c, int orderNumber) - Constructor
		setters:
			void addItem(OrderedItem i, int quantity)
			void removeItem(OrderedItem i)
			void modifyItemQuantity(OrderedItem i, int quantity)
		getters:
			Iterator<OrderedItem> getItemList()
			int getItemQuantity(Product p)
			int getId()
*******************************************************************************/
package Source_Code;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private int orderNumber;
	private String date;
	private Client clientAccount;
	private ItemList itemsOrdered;

	public Order(Client c){
		clientAccount = c;
		orderNumber = (OrderIdServer.instance() ).getId();
		itemsOrdered = new ItemList();
		fillOrder(); //Add all items from client's cart to order
		date = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
		processOrder();
	}//end Order constructor

	/****** Setters **********/
	//Adding/Removing items from itemsOrdered:
	public void addItem(Product p, int quantity){
		itemsOrdered.addItem(p, quantity);
	}//end addItem

	public void removeItem(Product p){
		itemsOrdered.removeItem(p);
	}//end removeItem

	public void modifyItemQuantity(Product p, int quantity){
		itemsOrdered.changeQuantity(p, quantity);
	}//end modifyItemQuantity

	private void fillOrder(){
		Iterator it = clientAccount.getCart();
		while(it.hasNext()){
			itemsOrdered.addItem((OrderedItem)it.next());
		}//end while
	}//end fillOrder
	/****** END SETTERS ********/


	/****** Getters *************/
	//Getting an iterator from itemsOrdered
	public Iterator getItemList(){
		return itemsOrdered.getIterator();
	}//end getItemList

	//Getting the quantity of an ordered item
	public int getItemQuantity(Product p){
		Iterator<OrderedItem> it = itemsOrdered.getIterator();
		OrderedItem curr;
		boolean done = false;
		while(it.hasNext() && !done){
			curr = it.next();
			if(curr.getProduct() == p)
				return curr.getQuantity();
		}//end while
		return 0;	//If not found, then quantity is 0.
	}//end getItemQuantity

	public int getId(){
		return orderNumber;
	}//end getId
	
	public Client getClient(){
		return clientAccount;
	}
	/******* END GETTERS *********/
	
	//toString:
	public String toString(){
		return "Order Number: " + orderNumber + " Client Number: " + clientAccount.getId() + " Date: " + date
					+ "\n" + itemsOrdered.toString();
	}//end toString

	/********************* Processing methods *********************************/
	/****************************************************************************
	processOrder
	Will go through each item in the order, and attempt to fulfill it.
	Successfully fulfilled items will be added to an invoice
	Items that are not fulfilled are added to a waitlist corresponding to that 
	product
	The invoice is told to calculate its total when finished going through the list,
	and is added to the Client.
	Client's cart is cleared
	*****************************************************************************/
	public void processOrder(){
		clientAccount.clearCart(); //Clear client's cart
		Iterator orderIt = itemsOrdered.getIterator(); //Get iterator for all items
		Invoice invoice = new Invoice(clientAccount, this); //Create the invoice
		OrderedItem currItem;
		while(orderIt.hasNext() ){
			currItem = (OrderedItem) orderIt.next(); //Get next item in the list
			//Check if we can fulfill it with current stock
			if(currItem.getProduct().getStock() >= currItem.getQuantity() ){
				//Decrement stock, add it to the invoice
				currItem.getProduct().removeStock(currItem.getQuantity() );
				invoice.addItem(currItem);
			} else{	//Not enough in stock, add it to the wait list
				currItem.getProduct().waitListItem(this, currItem.getQuantity());
				System.out.println("Waitlisting item \"" + currItem.getProduct().getDescription() + "\" due to insufficient inventory.");
			}//end else
		}//end while		
		//Apply the invoice with the items that are currently being fulfilled
		invoice.applyInvoice();
	}//end processOrder

}//end Order Class
