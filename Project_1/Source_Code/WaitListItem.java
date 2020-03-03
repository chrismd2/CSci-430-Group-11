/******************************************************************************
WaitListItem.java
******************************************************************************/
package Source_Code;

import java.util.*;
import java.io.*;
import java.lang.*;
public class WaitListItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Order waitingOrder;
	private int quantity;

	public WaitListItem(Order order, int quantity){
		waitingOrder = order;
		this.quantity = quantity;
	}//end OrderedItem

	public Order getOrder(){
		return waitingOrder;
	}//end getOrder()

	public int getQuantity(){
		return quantity;
	}//end getQuantity()
	
	public String toString(){
		return "Id: " + waitingOrder.getId() + " | Quantity: " + quantity + '\n';
	}//end toString
	
	//Will tell the item to do something to itself for finances
	public void process(){
				
	}//end process
}
