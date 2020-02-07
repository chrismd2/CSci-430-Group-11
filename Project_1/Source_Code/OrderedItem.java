public class OrderedItem{
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