import java.util.ArrayList;
import java.util.Iterator;

public class ItemList{
	ArrayList itemList;
	
	public ItemList(){
		itemList = new ArrayList<OrderedItem>(); 
	}//end ItemList constructor
	
	public void addItem(Product newProduct, int quantity){
		itemList.add( new OrderedItem(newProduct, quantity) );
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
	
}