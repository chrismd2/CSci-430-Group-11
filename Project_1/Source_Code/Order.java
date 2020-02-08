/******************************************************************************
	Order.java
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
		
*******************************************************************************/
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order{
	int orderNumber;
	String date;
	Client clientAccount;
	ItemList itemsOrdered;
	
	public Order(Client c, int orderNumber){
		clientAccount = c;
		this.orderNumber = orderNumber;
		itemsOrdered = new ItemList();
		date = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
	}//end Order constructor
	
	/****** Setters **********/
	//Adding/Removing items from itemsOrdered:
	public void addItem(OrderedItem i, int quantity){
		itemsOrdered.add(i, quantity);
	}//end addItem
	
	public void removeItem(OrderedItem i){
		itemsOrdered.remove(i);
	}//end removeItem
	
	public void modifyItemQuantity(OrderedItem i, int quantity){
		itemsOrdered.changeQuantity(i, quantity);
	}//end modifyItemQuantity
	
	
	/****** END SETTERS ********/
	
	
	/****** Getters *************/
	//Getting an iterator from itemsOrdered
	public Iterator getItemList(){
		return itemsOrdered.getIterator();
	}//end getItemList
	
	//Getting the quantity of an ordered item
	public int getItemQuantity(Product p){
		Iterator<OrderedItem> it = itemList.iterator();
		OrderedItem curr;
		boolean done = false;
		while(it.hasNext && !done){
			curr = it.next();
			if(curr.getProduct() == p)
				return curr.getQuantity();
		}//end while
		return 0;	//If not found, then quantity is 0.
	}//end getItemQuantity
	
	/******* END GETTERS *********/

}