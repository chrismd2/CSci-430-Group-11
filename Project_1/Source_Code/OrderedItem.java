/******************************************************************************
	OrderedItem.java
	Responsible Individual: Brent Clapp
	Represents an individual instance of a Product with a specified quantity of that product.
	Fields:
		Product productOrdered	- The specific instance of productOrdered
		int quantity	 - The quantity of productOrdered
	Methods:
		Constructor:
			OrderedItem(Product item, int quantity)
		Setters:
			void changeQuantity(int quantity)
		Getters:
			Product getProduct()
			int getQuantity()
*******************************************************************************/
package Source_Code;

import java.util.*;
import java.io.*;
public class OrderedItem implements Serializable{
	private static final long serialVersionUID = 1L;
	Product productOrdered;
	int quantity;

	public OrderedItem(Product item, int quantity){
		productOrdered = item;
		this.quantity = quantity;
	}//end OrderedItem

	public void changeQuantity(int quantity){
		this.quantity = quantity;
	}//end changeQuantity

	public Product getProduct(){
		return productOrdered;
	}//end getProduct()

	public int getQuantity(){
		return quantity;
	}//end getQuantity()
}
