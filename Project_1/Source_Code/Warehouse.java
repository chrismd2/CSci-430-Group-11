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
	

	/*************************************************************
	addClient
	Constructs a new Client object, and adds it to the client lists
	*************************************************************/
	public void addClient(String name, String phone, String address, int id){
		clients.addClient(new Client(id, name, phone, address) );
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
}//end Warehouse class
