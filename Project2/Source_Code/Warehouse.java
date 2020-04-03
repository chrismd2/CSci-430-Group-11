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
	private List<WaitListItem> pendingWaitListRemoval = new LinkedList<>();
	private List<Product> pendingWaitListProducts = new LinkedList<>();

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

	public double getClientBalance(int id){
		return clients.findClient(id).getClientBalance();
	}//end getClientBalance

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

   /*************************************************************
   clearCart
   Given a client id, clears that client's cart
   *************************************************************/
   public void clearCart(int id){
      clients.findClient(id).clearCart();
   }//end clearCart

   /************************************************************
   modifyCartItem
   Given a client id, product id and quantity, modifies the
   quantity of that product. A quantity of 0 will remove it from the cart
   *************************************************************/
   public void modifyCartItem(int clientId, int productId, int quantity){
      clients.findClient(clientId).changeItemQuantity(products.findProduct(productId), quantity);
   }//end modifyCartItem


	/**************************************************************
	makePayment
	Given a client id and a positive payment amount, this method
	create a payment for that client's account
	***************************************************************/
	public void makePayment(int clientId, double amount, String description){
		Payment p = new Payment(amount, clients.findClient(clientId), description);
	}//end makePayment

	/***************************************************************
	getPaymentIt
	Given a client id, this method returns an iterator to that client's
	list of payments
	*****************************************************************/
	public Iterator getPaymentIt(int id){
		return clients.findClient(id).getPayments();
	}//end getPaymentIt

	/***************************************************************
	getInvoiceIt
	Given a client id, this method returns an iterator to that client's
	list of invoices
	*****************************************************************/
	public Iterator getInvoiceIt(int id){
		return clients.findClient(id).getInvoices();
	}//end getPaymentIt

	/***************** END CLIENT METHODS ********************/

	/******************** ORDER METHODS *******************************/
	/*************************************************************
	placeOrder
	Given a client id
	Places the order for that client.
	*************************************************************/
	public void placeOrder(int clientId){
		//Create an order corresponding to the client, will clear client's cart
		Order order = new Order(clients.findClient(clientId) );
		//Add the order to the orderList
		orders.addOrder(order);
	}//end placeOrder

	/****************   PRODUCT METHODS   ********************/
	public void addProduct(String _description, double purchasePrice, double _salePrice, int _stock, int supplier){
		System.out.println("WARNING: purchasePrice, purchasePrice, and supplier are not being added to product");
		products.insertProduct(_description, _salePrice, _stock);
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

	public int getStock(int productId){
		return products.findProduct(productId).getStock();
	}//end getStock

   /*******************************************************************
   getProductWaitList
   Returns an iterator the product with the given id's wait list
   ********************************************************************/
   public Iterator getProductWaitList(int productId){
      return products.findProduct(productId).getWaitList();
   }//end getProductWaitList
	/**************** END PRODUCT METHODS ********************/

	/******************* SUPPLIER METHODS ************************/
	/*********************************************************
	addSupplier
	Given a supplier description, creates a new supplier object
	**********************************************************/
	public void addSupplier(String description){
		Supplier s = new Supplier(description);
		suppliers.insertSupplier(s);
	}//end addSupplier

	/***************************************************************
	setSupplierDescription
	Given a description and and id, changes the description for
	that supplier
	****************************************************************/
	public void setSupplierDescription(int id, String description){
		suppliers.search(id).setDescription(description);
	}//end setSupplierDescription

	/********************************************************
	findSupplier
	Given a supplier ID, returns a reference to the corresponding
	client. Returns null if doesn't exist
	*********************************************************/
	public Supplier findSupplier(int id){
		Iterator it = suppliers.getSuppliers();
		Supplier s;
		while(it.hasNext()){
			s = (Supplier)it.next();
			if(s.getId() == id)
				return s;
		}//end while
		return null;
	}//end findSupplier

	/******************************************************************************
		verifySupplier
		Returns true if the supplier exists in the supplierList. False otherwise
	*******************************************************************************/
	public boolean verifySupplier(int id){
		return !(suppliers.search(id) == null);
	}//end verifySupplier

	/***************************************************************
	getSuppliers
	Returns an iterator to the list of suppliers
	***************************************************************/
	public Iterator getSuppliers(){
		return suppliers.getSuppliers();
	}//end getSuppliers

	/**************************************************************
	addSupplierItem
	Given a Supplier, Product, Purchase price
	Adds the product to the list of items that the supplier stocks
	****************************************************************/
	public void addSupplierItem(int supplierId, int productId, double purchasePrice){
		suppliers.search(supplierId).addProduct(products.search(productId).get(0), purchasePrice);
	}//end addSupplierItem

	/***************************************************
	addShippedItem
	Given a productId and a quantity, adds the given product
	to the stock. Returns an iterator to the waitlist.
	****************************************************/
	public Iterator addShippedItem(int productId, int quantity){
		Product p = products.findProduct(productId);
		//Prompt product to add the item
		p.addShippedItem(quantity);
		return p.getWaitList();
	}//end addShippedItem

	/*********************************************************
	fulfillWaitListItem
	UI will pass in a Product id and a waitlist item reference,
	and will use them to fulfill the waitlisted item
	*********************************************************/
	public void fulfillWaitListItem(int productId, WaitListItem item){
		pendingWaitListProducts.add(products.findProduct(productId));
		pendingWaitListRemoval.add(item);
	}//end fulfillWaitListItem

	public void doneAddingfulfillItems(){
		while(pendingWaitListRemoval.size() > 0){
			pendingWaitListProducts.get(0).fulfillWaitListItem(pendingWaitListRemoval.get(0));
         //Remove these items, dealt with now
         pendingWaitListProducts.remove(0);
         pendingWaitListRemoval.remove(0);
      }
	}

}//end Warehouse class
