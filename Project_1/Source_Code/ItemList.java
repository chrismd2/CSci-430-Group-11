/******************************************************************************
	ItemList.java
	Responsible Individual: Brent Clapp
	Serves as an interface for the a list of items with specified quantities.
	methods:
		void ItemList()
		void addItem(Product newItem, int quantity)
		void removeItem(Product removeProduct)
		void changeQuantity(Product changeProduct, int quantity)
		Iterator getIterator()
******************************************************************************/
package Project_1.Source_Code;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemList{
	ArrayList<OrderedItem> itemList;
	
	public ItemList(){
		itemList = new ArrayList<OrderedItem>(); 
	}//end ItemList constructor
	
	public void addItem(Product newProduct, int quantity){
		itemList.add( new OrderedItem(newProduct, quantity) );
	}
	
	public void removeItem(Product removeProduct){
		itemList.remove(removeProduct);
	}//end removeItem
	
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
}