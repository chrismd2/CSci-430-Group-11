/******************************************************************************
	ItemList.java
	Responsible Individual: Brent Clapp
	Serves as an interface for the a list of items with specified quantities.
	methods:
		void ItemList()
		void addItem(Product newItem, int quantity)
		void removeItem(Product removeProduct)
		void changeQuantity(Product changeProduct, int quantity)
		void clear()
		Iterator getIterator()
******************************************************************************/
package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ItemList implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<OrderedItem> itemList;
	
	public ItemList(){
		itemList = new ArrayList<OrderedItem>();
	}//end ItemList constructor

	public void addItem(Product newProduct, int quantity){
		itemList.add( new OrderedItem(newProduct, quantity) );
	}

	public void addItem(OrderedItem item){
		itemList.add(item);
	}

	public void removeItem(Product removeProduct){
		Iterator<OrderedItem> it = itemList.iterator();
		OrderedItem curr;
		boolean done = false;
		while(it.hasNext() && !done){
			curr = it.next();
			if(curr.getProduct() == removeProduct){
				it.remove();
				done = true;
			}//end if
		}//end while()
	}//end removeItem

	public void clear(){
		itemList = new ArrayList<OrderedItem>();
		//Gives itemList a blank list to refer to
		//Old list will be cleared by the garbage collector.
	}//end clear()

	public void changeQuantity(Product changeProduct, int quantity){
		Iterator<OrderedItem> it = itemList.iterator();
		OrderedItem curr;
		boolean done = false;
		while(it.hasNext() && !done){
			curr = it.next();
			if(curr.getProduct() == changeProduct){
				curr.changeQuantity(quantity);
				done = true;
			}//end if
		}//end while()
	}//end changeInQuantity

	public Iterator<OrderedItem> getIterator(){
		return itemList.iterator();
	}//end getIterator
	
  public String toString(){
	String returnedString = "";
	Iterator curr = itemList.iterator(); 
	while(curr.hasNext())
		returnedString = returnedString.concat(curr.next().toString() + '\n');
	return returnedString;
  }//end toString

}
