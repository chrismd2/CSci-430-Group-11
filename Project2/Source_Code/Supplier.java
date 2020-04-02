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
	  return description + '\n' + suppliedProducts.toString();
  }//end toString
}

