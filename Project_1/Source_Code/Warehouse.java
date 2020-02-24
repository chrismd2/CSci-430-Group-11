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
			OrderIdServer.instance();
			ClientIdServer.instance();
			ProductIdServer.instance();
			SupplierIdServer.instance();
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
		  out.writeObject(OrderIdServer.instance());
		  out.writeObject(ProductIdServer.instance());
		  out.writeObject(SupplierIdServer.instance());
		  return true;
		} catch(IOException ioe) {
		  ioe.printStackTrace();
		  return false;
		}//end try-cach block

	}//end saveData

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

	/****** FORMATTING METHODS: *******************/
	public String toString(){
		return "Clients: \n" + clients.toString() +
				  "\nOrders: \n" + orders.toString() +
				  "\nProducts: \n" + products.toString() +
				  "\nSuppliers:\n" + suppliers.toString();
	}//end toString

	/********** END FORMATTING METHODS *******************/

	/************** CLIENT METHODS ***********************/

	/*************************************************************
	addClient
	Constructs a new Client object, and adds it to the client lists
	*************************************************************/
	public void addClient(String name, String phone, String address){
		clients.addClient(new Client(name, phone, address) );
	}//end addClient

	/*****************************************************************
	findClient
	Returns a reference to the client object with the given id.
	Parameters:
		id(int) - the id of the requested client object
	Returns:
		client(Client) - A reference to the client object with the given
						id. Returns null if the client cannot be found.
	*********************************************************************/
	public Client findClient(int id){
		Iterator it = clients.getIterator();
		Client c;
		while(it.hasNext()){
			c = (Client)it.next();
			if(c.getId() == id)
				return c;
		}//end while
		return null;
	}//end findClient

	/*********************************************************************
	verifyClient
	Returns true if the client exists in clientList; false otherwise.
	*********************************************************************/
	public boolean verifyClient(int id){
		return !(clients.findClient(id) == null);
	}//end verifyClient
	
	/****************************************************************
	getClients
	Returns an iterator to the list of Clients in the system.
	The UI can use this iterator to find whatever information
	that is needed
	*****************************************************************/
	public Iterator getClients(){
		return clients.getIterator();
	}//end getClients
	
	/************************************************************
	addToCart
	Given a client id, a product id and a quantity,
	adds the given product to that client's cart.
	*************************************************************/
	public void addToCart(int cId, int pId, int quantity){
		Client myClient = clients.findClient(cId);
		Product myProduct = products.findProduct(pId);
		myClient.addToCart(myProduct, quantity);
	}//end addToCart
	
	/*************************************************************
	getCart
	Given a client id, returns an iterator to that client's cart
	**************************************************************/
	public Iterator getCart(int cId){
		return clients.findClient(cId).getCart();
	}//end getCart

	/***************** END CLIENT METHODS ********************/

	/****************   PRODUCT METHODS   ********************/
	public void addProduct(String _description, double _purchasePrice, double _salePrice){
		products.insertProduct(_description, _purchasePrice, _salePrice);
	}
  public Iterator getProducts(){
    return products.getProduct();
  }
	public void showProductlist(){
		products.showList();
	}
	public Product findProduct(int id){
		return products.findProduct(id);
	}//end findProduct
	public void removeProducts(int PID){
		products.removeProduct(PID);
	}
	/********************************
		verifyProduct
		Returns true if the product exists in productList. False otherwise.
	********************************/
	public boolean verifyProduct(int id){
		//null when not in list, so want to return false when null
		return !(products.findProduct(id) == null);
	}//end verifyProduct

	/**************** END PRODUCT METHODS ********************/
}//end Warehouse class
