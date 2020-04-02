package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Supplier implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String description;
  private ArrayList<SupplierItem> suppliedProducts;

  public Supplier(String description){
    id = (SupplierIdServer.instance() ).getId();
    this.description = description;
	suppliedProducts = new ArrayList<SupplierItem>();
  }
  
  public void addProduct(Product p, double purchasePrice){
	  suppliedProducts.add(new SupplierItem(this, p, purchasePrice));
  }//end addProduct
  
  public void setPurchasePrice(Product p, double purchasePrice){
	  //Verify that p is in the list of supplied products
	  Iterator it = suppliedProducts.iterator();
	  boolean notFound = true;
	  SupplierItem modifiedProduct = null;
	  while(it.hasNext() && notFound){
			modifiedProduct = (SupplierItem) it.next();
			if(modifiedProduct.getProduct() == p)
				notFound = false;
	  }//end while
	if(notFound){
		System.out.println("Specified product not currently supplied by supplier");
		return;
	}//end if
	//Set the new price for p
	modifiedProduct.setPurchasePrice(purchasePrice);
  }//end setPurchasePrice
  
  public Iterator getProducts(){
	  return suppliedProducts.iterator();
  }//end getProducts
  
  public int getId(){
	return id;
  }//end getId

  public void setDescription(String description){
	  this.description = description;
  }//end setDescription
  
  public String getDescription(){
	  return description;
  }//end getDescription
  
  public String toString(){
	  return description + "\tId: " + id + '\n';
  }//end toString
  
  /**************************************************************
  searchProduct
	Given a product id, this method will search for that product in
	the supplier's list of products. If found, it will return a string
	containing the supplier's id, name and purchase price of the product
	If not found, it will return an empty string
  **************************************************************/
  public String searchProduct(int id){
	  Iterator it = suppliedProducts.iterator();
	  SupplierItem currSupplierItem;
	  while(it.hasNext()){
		  currSupplierItem = (SupplierItem)it.next();
		  if(currSupplierItem.getProduct().getProductNumber() == id)
			  return "Supplier id: " + id + " Name: " + description + '\n' +
						"Purchase Price: " + currSupplierItem.getPurchasePrice();
	  }//end while
	  return "";
  }//end searchProduct
  
  /******************************************************************
  hasProduct
	Given a product id, this method will return true if the supplier
	stocks that product. False otherwise.
  ********************************************************************/
  public boolean hasProduct(int id){
	 Iterator it = suppliedProducts.iterator();
	 while(it.hasNext())
		 if(((SupplierItem)it.next()).getProduct().getProductNumber() == id)
			 return true;
	 return false;
  }//end hasProduct
}

