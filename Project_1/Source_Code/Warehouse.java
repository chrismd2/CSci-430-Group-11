/******************************************************************************
	Warehouse.java
	No primary responsible party. Structure written by Brent Clapp.
	All functionality is added as finished by group members

	General Methods:
		Warehouse(): Default Constructor. Creates an empty Warehouse with empty lists.

	Client Methods:
		addClient(String name, String phone, String address, int id): Adds new client to Warehouse
		removeClient(int id): Removes client with given id from Warehouse



******************************************************************************/
package Source_Code;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Warehouse implements Serializable{
	private OrderList orders;
	private ClientList clients;
	private SupplierList suppliers;
	private ProductList products;
	private static Warehouse warehouse;
	
	//Default constructor:
	public Warehouse(){
		//Initialize all lists to empty:
		orders = new OrderList();
		clients = new ClientList();
		suppliers = new SupplierList();
		products = new ProductList();
	}//end constructor

	//Instance method:
	public static Warehouse instance(){
		if(warehouse == null) {
			return (warehouse = new Warehouse() );
		} else {
			return warehouse;
		}//end if-else
	}//end instance
	
	public static Warehouse retrieveData(String filename){
		try {
		  FileInputStream file = new FileInputStream(filename);
		  ObjectInputStream in = new ObjectInputStream(file);
		  //List all objects that need to be read.
		  in.readObject();
		  ClientIdServer.retrieve(in);
		  OrderIdServer.retrieve(in);
		  ProductIdServer.retrieve(in);
		  SupplierIdServer.retrieve(in);
		  return warehouse;
		} catch(IOException ioe) {
		  ioe.printStackTrace();
		  return null;
		} catch(ClassNotFoundException cnfe) {
		  cnfe.printStackTrace();
		  return null;
		}
	}//end retrieveData
	
	public static boolean saveData(String filename){
		try {
		  FileOutputStream file = new FileOutputStream(filename);
		  ObjectOutputStream out = new ObjectOutputStream(file);
		  //List all objects that need to be written
		  out.writeObject(warehouse);
		  out.writeObject(ClientIdServer.instance());
		  out.close(); //Close both data streams
		  file.close();
		  return true;
		} catch(IOException ioe) {
		  ioe.printStackTrace();
		  return false;
		}//end try-cach block
		
	}//end saveData
	
	/*************************************************************
	addClient
	Constructs a new Client object, and adds it to the client lists
	*************************************************************/
	public void addClient(String name, String phone, String address){
		clients.addClient(new Client(name, phone, address) );
	}//end addClient


	/*************************************************************
	Read/Write serialization methods:
	*************************************************************/
	private void writeObject(java.io.ObjectOutputStream output){
		try{
			output.defaultWriteObject();
			output.writeObject(warehouse);
		} catch(IOException ioe){
			ioe.printStackTrace();
		}//end try-catch block
	}//end writeObject
	
	private void readObject(java.io.ObjectInputStream input){
		try{
			if(warehouse != null){
				return;
			} else{
				input.defaultReadObject();
				if(warehouse == null){
					warehouse = (Warehouse) input.readObject();
				} else {
					input.readObject();
				}//end if-else
			}//end if-else
		} catch (IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}//end try-catch block
	
	}//end readObject
	/************* End Serialization methods **************************************/
	
	public String toString(){
		return "Clients: \n" + clients.toString() +
				  "\nOrders: \n" + orders.toString() +
				  "\nProducts: \n" + products.toString() +
				  "\nSuppliers:\n" + suppliers.toString();
	}//end toString
}//end Warehouse class
