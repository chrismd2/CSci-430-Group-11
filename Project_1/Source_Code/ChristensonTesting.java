/*
  File: ChristensonTesting.java

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

public class ChristensonTesting {
  public void testProductList(){
    int itemCount = 10;
    System.out.println("Testing Product List");
    ProductList PList = new ProductList();
    for(int i = 0; i < itemCount; i++){
      PList.insertProduct("Item " + Integer.toString(i), 10 + i/4, 15 + i/2);
    }//finished inserting itemCount number of products

    System.out.println("\nDisplaying data with a getData function from iterator.");
    Iterator product = PList.getProduct();
    while(product.hasNext()){
      Product tProduct = (Product)product.next();
      System.out.println( tProduct.getData() );
    }

    System.out.println("\nDisplaying data with functions from iterator.");
    product = PList.getProduct();
    while(product.hasNext()){
      Product tProduct = (Product)product.next();
      System.out.println( Integer.toString(tProduct.getProductNumber()) + ": " + tProduct.getDescription() + " Profit = " + Double.toString(tProduct.getSalePrice()-tProduct.getPurchasePrice()) );
    }

    System.out.println("\nUpgrading the first product.");
    product = PList.getProduct();
    Product tProduct = (Product)product.next();
    tProduct.setDescription(tProduct.getDescription() + ".2");
    tProduct.setPurchasePrice(100.0);
    tProduct.setSalePrice(150.0);

    System.out.println("\nDisplaying data with showList function.");
    PList.showList();
  }//end test
}//end ChristensonTesting
