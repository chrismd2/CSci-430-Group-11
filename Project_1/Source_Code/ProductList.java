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
   private static final long serialVersionUID = 1L;
  private static List<Product> Products = new LinkedList<Product>();
  private Product aProduct = new Product();
  private static ProductList pList; //Used for serialize methods
  
  public ProductList(){
  }
  
  public static ProductList instance() {
	if(pList == null){
		return (pList = new ProductList());
	} else{
		return pList;
	}//end if-else
	
  }//end instance()
  
  public void insertProduct(Product P){
    Products.add(P);
  }
  public Iterator getProduct(){
    return Products.iterator();
  }
  public void showList(){
    for(Iterator current = Products.iterator(); current.hasNext();){
      Product P = (Product) current.next();
      System.out.println(P.getData());
    }
  }
  public void removeProduct(String PID){
    int i = 0;
    for(Iterator current = Products.iterator(); current.hasNext();){
      i++;
      Product P = (Product) current.next();
      if(P.getProductNumber() == PID){
        Products.remove(i);
      }
    }
  }
  public Product findProduct(String PID){
    for(Iterator current = Products.iterator(); current.hasNext();){
      Product P = (Product) current.next();
      if(P.getProductNumber() == PID){
        return P;
      }
    }
    return null;
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
	try{
		output.defaultWriteObject();
		output.writeObject(pList);
	} catch(IOException ioe){
		ioe.printStackTrace();
	} //end try-catch block
  }//end writeObject
  
  private void readObject(java.io.ObjectInputStream input){
	try{
		if(pList != null)
			return;
		else{
			input.defaultReadObject();
			if(pList == null){
				pList = (ProductList) input.readObject();
			} else {
				input.readObject();
			}//end if-else
		}//end if-else
	} catch (IOException ioe){
		ioe.printStackTrace();
	} catch(ClassNotFoundException cnfe) {
		cnfe.printStackTrace();
	}//end try-catch block
  }//end readObject
}