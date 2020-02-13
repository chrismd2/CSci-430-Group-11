/*****************************************************************************
	OrderList.java
	Responsible Individual: Brent Clapp
	Contains all of the Orders in the Warehouse's system.
	fields:
		orders: A list of all Order objects present in the system
	methods:
		Order()	- Default constructor - Creates an empty OrderList
		void addOrder(Order o) - Adds the given Order to the OrderList
		void removeOrder(Order o) - Removes the given Order from the OrderList
		void findOrder(int id) - Finds the order with the given id. Returns null if not found.
		Iterator getIterator() - Returns an iterator that will Iterate through all Order objects.
*****************************************************************************/
package Source_Code;

import java.util.*;
import java.io.*;
import java.lang.*;

public class OrderList implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Order> myOrders;
	private static OrderList orderList;
	
	public static OrderList instance() {
		if(orderList == null){
			return (orderList = new orderList() );
		} else{
			return orderList;
		}//end if-else
	}//end instance()
	
	//Default constructor:
	public OrderList(){
		myOrders = new ArrayList<Order>();
	}//end OrderList
	
	//addOrder:
	public void addOrder(Order o){
		myOrders.add(o);
	}//end addOrder

	//removeOrder:
	public void removeOrder(Order o){
		myOrders.remove(o);
	}//end removeOrder

	//findOrder:
	public Order findOrder(int id){
		Iterator it = myOrders.iterator();
		Order curr = null;
		boolean done = false;
		while(!done && it.hasNext() ){
			curr = (Order)it.next();
			if(curr.getId() == id)
				done = true;
		}//end while
		if(!done){
			System.out.println("Order not found.");
			curr = null;
		}//end if
		return curr;
	}//end findOrder

	//getIterator:
	public Iterator getIterator(){
		return myOrders.iterator();
	}//end getIterator

	private void writeObject(java.io.ObjectOutputStream output){
		try{
			output.defaultWriteObject();
			output.writeObject(orderList);
		} catch(IOException ioe){
			ioe.printStackTrace();
		}//end try-catch IOException
	}//end writeObject
	
	private void readObject(java.io.ObjectInputStream input){
		try{
			if(orderList != null){
				return;
			} else {
				input.defaultReadObject();
				if(orderList == null) {
					orderList = (OrderList) input.readObject();
				} else {
					input.readObject();
				} //end else
			}//end else
		} catch( IOException ioe) {
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end try-catch
	
	}
	
	public String toString(){
		return myOrders.toString();
	}//end toString
}
