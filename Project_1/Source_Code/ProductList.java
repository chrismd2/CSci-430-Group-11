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

//package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ProductList implements Serializable{
  private static List Products = new LinkedList();
  Product aProduct = new Product();

  public ProductList(){
  }
  public void insertProduct(Product P){
    Products.add(P);
  }
  public Iterator getProduct(){
    return Products.iterator();
  }
  public void showList(){
    for(Iterator current = Products.iterator(); current.hasnext()){
      Product P = (Product) currnent.next();
      System.out.println(P.getData());
    }
  }
  public void removeProduct(string PID){
    int i = 0;
    for(Iterator current = Products.iterator(); current.hasnext()){
      i++;
      Product P = (Product) currnent.next();
      if(P.getProductNumber == PID){
        Products.remove(i);
      }
    }
  }
  public void findProduct(string PID){
    for(Iterator current = Products.iterator(); current.hasnext()){
      i++;
      Product P = (Product) currnent.next();
      if(P.getProductNumber == PID){
        return P;
      }
    }
    return null;
  }
}
