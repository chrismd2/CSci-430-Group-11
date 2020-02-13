/*
  File: Product.java

CSci 430 Object Oriented Programming
Project 1 - Warehouse
  - Group 11
  - NAMES:
     > Mark Christenson - This file's designer
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
    +  void: setProductNumber(String)
    +  void: setDescription(String)
    +  void: setPurchasePrice(double)
    +  void: setSalePrice(double)

*/
//package Project_1.Source_Code;
package Source_Code;

public class Product{
  String productID;
  String description;
  double purchasePrice;
  double salePrice;

  public Product(){
    productID = "";
    description = "";
    purchasePrice = 0;
    salePrice = 0;
  }

  public String getProductNumber(){
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

  public String getData(){
    String data = productID;
    data += " ";
    data += description;
    data += "\n\tPurchase Price: ";
    data += purchasePrice;
    data += "\n\tSale Price: ";
    data += salePrice;
    return data;
  }

  public void setProductNumber(String input){
    productID = input;
  }

  public void setDescription(String input){
    description = input;
  }

  public void setData(){
    System.out.println("To use data Setter input data members in this order\n\tString productID\n\tString description\n\tdouble purchasePrice\n\tdouble salePrice");
  }

  public void setData(String _productID, String _description, double _purchasePrice, double _salePrice){
    productID       =       _productID;
    description     =     _description;
    purchasePrice   =   _purchasePrice;
    salePrice       =       _salePrice;
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
}
