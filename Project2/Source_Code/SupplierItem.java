package Source_Code;

import java.util.*;
import java.lang.*;

public class SupplierItem{
    private static final long serialVersionUID = 1L;
	private final Supplier supplier;
	private final Product product;
	private double purchasePrice;
	
	public SupplierItem(Supplier s, Product p, double price){
		supplier = s;
		product = p;
		purchasePrice = price;
	}//end constructor
	
	public void setPurchasePrice(double price){
		purchasePrice = price;
	}//end setPurchasePrice
	
	public double getPurchasePrice(){
		return purchasePrice;
	}//end getPurchasePrice
	
	public Product getProduct(){
		return product;
	}//end getProduct
	
	public Supplier getSupplier(){
		return supplier;
	}//end getSupplier
	
	public String toString(){
		return "Product: " + product.getDescription() +
				"\nPrice: " + purchasePrice + "\n";
	}//end toString
}//end SupplierItem class