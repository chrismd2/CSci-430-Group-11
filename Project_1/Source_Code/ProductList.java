/*
  File: ProductList.java

CSci 430 Object Oriented Programming
  - Group 11
  - NAMES:
     > Mark Christenson - This file's designer
     > Brent Clapp
     > Sabeen Basnet

  - SPRING 2020 Section 2

  COMMENTS: Manages the products currently tracked by a warehouse
*/

package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ProductList implements Serializable{
  private static List Products = new LinkedList();
  private static int ID;
  public ProductList(){
    ID = 1;
  }
  public void insertProduct(Product product){
    Products.add(product);
  }
  public void showList(){
    //current = Products.get()
    System.out.println(Products);
  }
}

}
