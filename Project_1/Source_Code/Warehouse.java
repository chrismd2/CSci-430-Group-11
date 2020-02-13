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
package Project_1.Source_Code;

public class Warehouse{
	OrderList orders;
	ClientList clients;
	SupplierList suppliers;
	ProductList products;
	
	//Default constructor:
	public Warehouse(){
		//Initialize all lists to empty:
		orders = new OrderList();
		clients = new ClientList();
		suppliers = new SupplierList();
		products = new ProductList();
	}//end constructor
	
	
	/*************************************************************
	addClient
	Constructs a new Client object, and adds it to the client lists
	*************************************************************/
	public void addClient(String name, String phone, String address, int id){
		clients.addClient(new Client(id, name, phone, address) );
	}//end addClient






}