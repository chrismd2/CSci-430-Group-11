/*****************************************************************************
	OrderList.java
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
import java.util.ArrayList;
import java.util.Iterator;

public class OrderList{
	ArrayList<Order> myOrders;
	
	//Default constructor:
	public OrderList(){
		myOrders = new OrderList<Order>();
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
		Client curr;
		boolean done = false;
		while(!done && it.hasNext() ){
			if(curr.getId == id)
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
}