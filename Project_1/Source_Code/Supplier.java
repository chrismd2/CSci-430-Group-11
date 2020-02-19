package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Supplier implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private int quantity;
  private double price;

  public Supplier(int quantity, double price){
    id = (SupplierIdServer.instance() ).getId();
    this.quantity = quantity;
    this.price = price;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public double getPrice()
  {
    return price;
  }
  
  public int getId(){
	return id;
  }//end getId

  public void setNewQuantity(int q)
  {
    this.quantity = quantity - q;
  }
}

