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
package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ItemList implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<OrderedItem> itemList;
	private static ItemList items;
	
	public static ItemList instance() {
		if(items == null){
			return (items = new ItemList() );
		} else {
			return items;
		}//end else
	
	}//end instance()
	
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

	 private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(items);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (items != null) {
        return;
      } else {
        input.defaultReadObject();
        if (items == null) {
          items = (ItemList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
}
