/*
  File: ProductList.java

CSci 430 Object Oriented Programming
  - Group 11
  - NAMES:
     > Mark Christenson
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
  private List<Product> products = new LinkedList<Product>();
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

  public void insertProduct(String _description, double _purchasePrice, double _salePrice){
    Product P = new Product();
    P.setData(_description, _purchasePrice, _salePrice);
    products.add(P);
  }
  public Iterator getProduct(){
    return products.iterator();
  }
  public void showList(){
    for(Iterator current = products.iterator(); current.hasNext();){
      Product P = (Product) current.next();
      System.out.println(P.getData());
    }
  }
  public void removeProduct(int PID){
    int i = 0;
    for(Iterator current = products.iterator(); current.hasNext();){
      i++;
      Product P = (Product) current.next();
      if(P.getProductNumber() == PID){
        products.remove(i);
      }
    }
  }
  public Product findProduct(int PID){
    for(Iterator current = products.iterator(); current.hasNext();){
      Product P = (Product) current.next();
      if(P.getProductNumber() == PID){
        return P;
      }
    }
    return null;
  }

  private static void writeObject(java.io.ObjectOutputStream output) {
	try{
		output.defaultWriteObject();
		output.writeObject(pList);
	} catch(IOException ioe){
		ioe.printStackTrace();
	} //end try-catch block
  }//end writeObject

  private static void readObject(java.io.ObjectInputStream input){
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

  public String toString(){
	String returnedString = "";
	Iterator curr = products.iterator();
	while(curr.hasNext())
		returnedString = returnedString.concat(curr.next().toString() + '\n');
	return returnedString;
  }//end toString
 }
