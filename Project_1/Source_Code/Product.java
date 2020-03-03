/*
  File: Product.java

CSci 430 Object Oriented Programming
Project 1 - Warehouse
  - Group 11
  - NAMES:
     > Mark Christenson
     > Brent Clapp
     > Sabeen Basnet

  - SPRING 2020 Section 2

  COMMENTS: This program class manages the data for product objects

  IMPLEMENTATION REQUIRED:
    +  void: setData()

  AVAILABLE FUNCTIONALITY:
    +  String: getProductNumber()
    +  String: getDescription()
    +  String: getData()
    +  double: getPurchasePrice()
    +  double: getSalePrice()
    +  void: setDescription(String)
    +  void: setPurchasePrice(double)
    +  void: setSalePrice(double)

*/
package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private int productID;
  private String description;
  private double purchasePrice;
  private double salePrice;
  private int stock;
  private List<WaitListItem> waitList = new ArrayList<WaitListItem>();

  public Product(){
    productID = ProductIdServer.instance().getId();
    description = "";
    purchasePrice = 0;
    salePrice = 0;
	stock = 0;
  }

  public int getProductNumber(){
    return productID;
  }

  public String getDescription(){
    return description;
  }

  public double getPurchasePrice(){
    return purchasePrice;
  }

  public double getSalePrice(){
    return salePrice;
  }

  public int getStock(){
	return stock;
  }
  public void removeStock(int r){
	stock -= r;
  }//end removeStock
  
  public void addShippedItem(int quantity){
	  stock += quantity;
  }//end addShippedItem

  public String getData(){
    String data = "";
	data += productID;
    data += " ";
    data += description;
    data += "\n\tPurchase Price: ";
    data += purchasePrice;
    data += "\n\tSale Price: ";
    data += salePrice;
    return data;
  }

  public void setDescription(String input){
    description = input;
  }

  public void setData(){
    System.out.println("To use data Setter input data members in this order\n\tString description\n\tdouble purchasePrice\n\tdouble salePrice");
  }

  public void setData(String _description, double _purchasePrice, double _salePrice, int _stock){
    description     =     _description;
    purchasePrice   =   _purchasePrice;
    salePrice       =       _salePrice;
	stock 			=           _stock;
  }

  public void setPurchasePrice(double input){
    purchasePrice = input;
  }

  public void setSalePrice(double input){
    salePrice = input;
  }

  public String toString(){
    return getData();
  }
  
  public void waitListItem(Order o, int quantity){
	waitList.add(new WaitListItem(o, quantity));
  }//end waitListItem
  
  public Iterator getWaitList(){
	  return waitList.iterator();	  
  }//end getWaitList
  
  public void fulfillWaitListItem(WaitListItem item){
	  item.process();
	  waitList.remove(item);
  }//end fulfillWaitListItem
	  
}
