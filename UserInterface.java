/**********************************************************************
UserInterface.java
This file contains code for the user to interact with the Warehouse System via a terminal.
***********************************************************************/
import Source_Code.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class UserInterface
{
	private static Warehouse warehouse;
	private static UserInterface userInterface;
	final static String FILENAME = "WareData";
	final static String MAINMENU =  "	MAIN MENU	\n" +
									"______________________________________________\n" +
									"type e to quit \n" +
									"type c for a list of client actions\n" +
									"type o for a list of order actions \n" +
									"type p for a list of product actions\n" +
									"type s for a list of supplier actions\n";
	final static String CLIENTOPLIST = "CLIENT OPERATIONS:\n" +
										"______________________________________________\n" +
										"Enter m for main menu | Enter e to quit\n" +
										"Enter ADDCLIENT to add a client to the Warehouse\n" +
										"Enter MODIFYCLIENT to modify information about a client\n" +
										"Enter DISPLAYALLCLIENTS to display all clients\n" +
										"Enter DISPLAYCLIENT to find information about an individual client\n" +
										"Enter DISPLAYBALANCE to show a client's balance\n" +
										"Enter MAKEPAYMENT to make a payment for a client.\n" +
										"Enter DISPLAYPAYMENTS to display all payments on the client's account\n" +
										"Enter DISPLAYINVOICES to display all invoices on the client's account\n" +
										"Enter DISPLAYTRANSACTIONS to display all payments and invoices on the client's account\n";
	final static String ORDEROPLIST =   "ORDER OPERATIONS:\n" +
									    "______________________________________________________\n" +
									    "Enter m for main menu | Enter e to quit\n" +
										"Enter ADDTOCART to add a product to a client's cart\n" +
									  	"Enter DISPLAYCART to display a client's cart\n" +
										"Enter PLACEORDER to place an order using the client's current cart\n";
	final static String PRODUCTOPLIST = "PRODUCT OPERATIONS: \n" +
										"______________________________________________\n" +
										"Enter m for main menu | Enter e to quit\n" +
										"Enter ADDPRODUCT for add a product to the Warehouse\n" +
										"Enter DISPLAYALLPRODUCTS to display all products\n" +
										"Enter DISPLAYPRODUCT to find information about an individual product\n";
	final static String SUPPLIEROPLIST = "SUPPLIER OPERATIONS \n" +
										 "______________________________________________\n" +
										 "Enter m for main menu | Enter e to quit\n" +
										 "Enter ADDSUPPLIER to add a supplier to the Warehouse\n" +
										 "Enter CHANGESUPPLIERDESCRIPTION to change the description of an existing supplier\n" +
										 "Enter ADDSHIPMENT to add a shipment for the supplier\n";

	public static void main(String[] args){
		if(args.length > 0){
			switch(args[0]){
				case "UI":
					//Open the Warehouse:
					Scanner input = new Scanner(System.in);
					System.out.println("Open saved warehouse?(Y|N)");
					String opt = input.next();
					if(opt.equals("Y"))
						openWarehouse();
					else{
						warehouse = Warehouse.instance();
						System.out.println("New Warehouse system created");
					}//end else
					//View or change:
					processInput();

					//Close the Warehouse:
					System.out.println("Save changes to warehouse?\n(Y|N)");
					opt = input.next();
					if(opt.equals("Y"))
						saveChanges();
					break;
			}//end switch
		}//end if
		else{
			tester();
		}
	}//end run

	/*************************************************************************
	processInput
	Called every cycle of the testing loop. When user specifies query, this tests to see
	which query was selected, and activates the code for that query.
	*************************************************************************/
	public static void processInput(){
		Scanner input = new Scanner(System.in);
		String inputStr = "";
		System.out.println(MAINMENU);
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			inputStr = input.next();

			switch(inputStr.toUpperCase()){
		/******************** GENERAL ****************************/
				case "EXIT": case "E":
					System.out.println("Exiting warehouse operations\n"); break;
				case "M":
				case "MAIN":
					System.out.println(MAINMENU);
					break;
				case "CLIENT":case "C":	//print list of client operations
					System.out.println(CLIENTOPLIST); break;
				case "ORDER": case "O": //Print list of order operations
					System.out.println(ORDEROPLIST); break;
				case "PRODUCT": case "P": //Print list of product operations
					System.out.println(PRODUCTOPLIST); break;
				case "SUPPLIER": case "S": //Print list of supplier operations
					System.out.println(SUPPLIEROPLIST); break;
		/*************** PRODUCTS ********************************/
				case "ADDPRODUCT":
					addProduct(); break;
				case "DISPLAYPRODUCT":
					displayProduct(); break;
				case "DISPLAYALLPRODUCTS":
					displayAllProducts(); break;
		/****************** CLIENTS *****************************/
				case "ADDCLIENT":
					addClient(); break;
				case "DISPLAYCLIENT":
					displayClient(); break;
				case "DISPLAYALLCLIENTS":
					displayAllClients(); break;
				case "MODIFYCLIENT":
					modifyClient(); break;
				case "DISPLAYBALANCE":
					displayClientBalance(); break;
				case "MAKEPAYMENT":
					makePayment(); break;
				case "DISPLAYPAYMENTS":
					displayPayments(); break;
				case "DISPLAYINVOICES":
					displayInvoices(); break;
				case "DISPLAYTRANSACTIONS":
					displayTransactions(); break;
		/******************** PRODUCTS **************************/
				case "ADDTOCART":
					addToCart(); break;
				case "DISPLAYCART":
					displayCart(); break;
				case "PLACEORDER":
					placeOrder(); break;
		/******************* SUPPLIERS ****************************/
				case "ADDSHIPMENT":
					addShipment(); break;
				case "ADDSUPPLIER":
					addSupplier(); break;
				case "CHANGESUPPLIERDESCRIPTION":
					changeSupplierDescription(); break;
				default:
					System.out.println("Entered text did not match an option; Please try again.");
			}//end switch
			System.out.println("Please enter an option");
		}//end while

	}//end processInput
/********************* PRODUCT METHODS *******************************/
	/******************************************************************************
	addProduct
	Code to prompt user for necessary information to add a new product to the Warehouse
	*******************************************************************************/
	public static void addProduct(){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a description for the product: ");
		String description = input.nextLine();
		System.out.print("Enter a purchase price for the product: ");
		String purchasePrice = input.nextLine();
		System.out.print("Enter an sale price for the product: ");
		String salePrice = input.nextLine();
		System.out.print("Enter a stock for the product: ");
		int stock = input.nextInt();
		warehouse.addProduct(description, Double.valueOf(purchasePrice), Double.valueOf(salePrice), stock);
		System.out.println("Product added successfully");
	}//end addProduct

	/*****************************************************************************
	displayProduct
	Prompts user for an id number, then displays information about that Product
	******************************************************************************/
	public static void displayProduct(){
		System.out.print("Enter Product id: ");
		Scanner s = new Scanner(System.in);
		Product p = warehouse.findProduct(s.nextInt() );
		if(p == null)
			System.out.println("No Product found with given id");
		else
			System.out.println(p.toString());
	}//end displayProduct

	/*******************************************************************************
	displayAllProducts
	Displays all Product objects in the system.
	*******************************************************************************/
	public static void displayAllProducts(){
		Iterator it = warehouse.getProducts();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllProducts
/*********************** CLIENT METHODS *************************************/
	/******************************************************************************
	addClient
	Code to prompt user for necessary information to add a new client to the Warehouse
	*******************************************************************************/
	public static void addClient(){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a name for the client: ");
		String name = input.nextLine();
		System.out.print("Enter a phone number for the client: ");
		String phone = input.nextLine();
		System.out.print("Enter an address for the client: ");
		String address = input.nextLine();
		warehouse.addClient(name, phone, address);
		System.out.println("Client added successfully");
	}//end addClient

	/*****************************************************************************
	displayClient
	Prompts user for an id number, then displays information about that client
	******************************************************************************/
	public static void displayClient(){
		System.out.print("Enter client id: ");
		Scanner s = new Scanner(System.in);
		Client c = warehouse.findClient(s.nextInt() );
		if(c == null)
			System.out.println("No client found with given id");
		else
			System.out.println(c.toString());
	}//end displayClient

	/*******************************************************************************
	modifyClient
	Asks the user which element (name,phone,address) that they would like to modify,
	and gets the new information.
	*******************************************************************************/
	public static void modifyClient(){
		System.out.print("Enter the client's id number: ");
		Scanner s = new Scanner(System.in);
		Client c = warehouse.findClient(s.nextInt());
		if(c == null)
			System.out.println("Given id does not match a client; Aborting operation.");
		else{
			s = new Scanner(System.in); //Essentially, flush the input buffer
			System.out.println("Select an element to modify.\n" +
								"Enter n for name\n" +
								"Enter p for phone\n" +
								"Enter a for address");
			switch(s.nextLine() ){
				case "n":
					System.out.println("Enter a new name for the client:");
					c.setClientName(s.nextLine() );
					System.out.println("Client name updated");
					break;
				case "p":
					System.out.println("Enter a new phone number for the client: ");
					c.setPhone(s.nextLine() );
					System.out.println("Client phone updated");
					break;
				case "a":
					System.out.println("Enter a new address for the client: ");
					c.setAddress(s.nextLine() );
					System.out.println("Client address updated");
					break;
				default:
					System.out.println("Invalid option given; Aborting operation");
					break;
			}//end switch
		}//end else
	}//end modfiyClient

	/*******************************************************************************
	displayAllClients
	Displays all client objects in the system.
	*******************************************************************************/
	public static void displayAllClients(){
		Iterator it = warehouse.getClients();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllClients

	/********************************************************************************
	displayClientBalance
	Displays the balance of a client
	********************************************************************************/
	public static void displayClientBalance(){
		int clientId = getClientId();
		if(!warehouse.verifyClient(clientId)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		System.out.println("Client Balance: " + warehouse.getClientBalance(clientId));
	}//end displayClientBalance

	/*********************************************************************************
	makePayment
	Prompts user for a client id and a payment amount. Makes a payment to the client's
	account for that amount
	*********************************************************************************/
	public static void makePayment(){
		//Get client id
		int clientId = getClientId();
		if(!warehouse.verifyClient(clientId)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		//Get payment amount
		System.out.print("Enter a payment amount: ");
		double amount = Double.valueOf(new Scanner(System.in).nextLine());
		if(amount <= 0.0){
			System.out.println("Error, payment must be a positive value. Aborting operation");
			return;
		}//end if
		warehouse.makePayment(clientId, amount);
		System.out.println("Payment received successfully");
	}//end makePayment

	/**************************************************************************
	displayPayments()
	Prompts the user for a client id.
	Displays the payment date and amount for all payments that the client has
	made.
	***************************************************************************/
	public static void displayPayments(){
		int clientId = getClientId();
		Iterator paymentIt;
		if(!warehouse.verifyClient(clientId)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		paymentIt = warehouse.getPaymentIt(clientId);
		while(paymentIt.hasNext() )
			System.out.println( ((Payment)(paymentIt.next())).toString() );
	}//end displayPayments

	/**************************************************************************
	displayInvoices()
	Prompts the user for a client id.
	Asks the user if they'd like to display detailed data (This includes the items
															that were charged for)
	Displays the date and relevant data for each invoice in the client's history
	***************************************************************************/
	public static void displayInvoices(){
		int clientId = getClientId();
		Iterator invoiceIt;
		boolean choice;
		if(!warehouse.verifyClient(clientId)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		System.out.print("Would you like to display detailed transactions? (Y|N) ");
		choice = new Scanner(System.in).next().equals("Y"); //true if Y
		invoiceIt = warehouse.getInvoiceIt(clientId);
		if(choice)
			while(invoiceIt.hasNext() )
				System.out.println(((Invoice)(invoiceIt.next())).detailedString() );
		else
			while(invoiceIt.hasNext() )
				System.out.println(((Invoice)(invoiceIt.next())).toString());
	}//end displayInvoices()

	/**************************************************************************
	displayTransactions()
	Prompts the user for a client id.
	Displays all invoices and payments that are in the client's account history.
	***************************************************************************/
	public static void displayTransactions(){
		int clientId = getClientId();
		Iterator paymentIt, invoiceIt;
		if(!warehouse.verifyClient(clientId)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		invoiceIt = warehouse.getInvoiceIt(clientId);
		paymentIt = warehouse.getPaymentIt(clientId);
		System.out.println("INVOICES\n" +
						   "_____________________");
		while(invoiceIt.hasNext() )
			System.out.println(((Invoice)(invoiceIt.next())).toString());
		System.out.println("PAYMENTS\n" +
						   "_____________________");
		while(paymentIt.hasNext() )
			System.out.println( ((Payment)(paymentIt.next())).toString() );
	}//end displayTransactions


/********************** ORDER METHODS *******************************************/

	/*******************************************************************************
	addToCart
	Prompts the user for a product id, then asks for the quantity. Adds that item
	to the cart.
	********************************************************************************/
	public static void addToCart(){
		int productid, clientid, quantity;
		//Get the client:
		clientid = getClientId();
		if(!warehouse.verifyClient(clientid)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if

		//Get the product:
		productid = getProductId();
		if(!warehouse.verifyProduct(productid) ){
			System.out.println("Error, invalid product id. Aborting operation");
			return;
		}//end if

		//Should now have a valid id in choice
		System.out.print("Enter a quantity: ");
		Scanner s = new Scanner(System.in); //flush buffer
		quantity = s.nextInt(); //get quantity
		warehouse.addToCart(clientid, productid, quantity); //Warehouse takes over from here
		System.out.println("Item added to cart.");
	}//end addToCart

	/*******************************************************************************
	displayCart
	Will prompt for a client's id, and will display that client's cart if client exists
	********************************************************************************/
	public static void displayCart(){
		System.out.print("Please enter a client id: ");
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		Iterator it;
		if(warehouse.verifyClient(id)){
			it = warehouse.getCart(id);
			while(it.hasNext()) //Display every item in cart
				System.out.println( ((OrderedItem)it.next()).toString() );
		}//end if
		else
			System.out.println("Error: Invalid id given");
	}//end displayCart

	/*****************************************************************************
	placeOrder()
	Will prompt operator for a client id and verify. Then will place an order using
	that client's current cart.
	Order will be created, and status of every item will be displayed.
	Displays that an invoice is created, and displays the price. Does not include
	waitlisted items.
	Invoice is applied to client's balance.
	*****************************************************************************/
	public static void placeOrder(){
		//Get client id:
		int clientId = getClientId();
		//Verify client id:
		if(!warehouse.verifyClient(clientId) ){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		//Client id now verified
		//Tell warehouse to place that client's order
		warehouse.placeOrder(clientId);
		System.out.println("Order placed successfully");
	}//end placeOrder

	
/***************************** SUPPLIER METHODS *************************************/
	/**************************************************************************
	addSupplier()
	Will prompt operator for a supplier description, and the supplier will be added
	to the Warehouse
	***************************************************************************/
	public static void addSupplier(){
		String description;
		Scanner s = new Scanner(System.in);
		description = s.nextLine();
		warehouse.addSupplier(description);
	}//end addSupplier
	
	/*************************************************************************
	changeSupplierDescription()
	Will prompt the user for a supplier ID. Verifies it.
	Will prompt for a new description, and sets the supplier's description to that
	************************************************************************/
	public static void changeSupplierDescription()}{
		String description;
		int id;
		Scanner s = new Scanner(System.in);
		System.out.print("Enter a supplier id: ");
		id = s.nextInt();
		if(!warehouse.verifySupplier(id) ){
			System.out.println("Error, invalid supplier id. Aborting operation");
			return;
		}//end if
		s = new Scanner(System.in); //flush input buffer
		System.out.println("Enter a new description for this supplier:");
		description = s.nextLine();
		
	}//end changeSupplierDescription
	
	/**************************************************************************
	addShipment()
	Will prompt operator for a supplier ID for the shipment being taken in.
	Verifies that id
	Will repeatedly prompt operator for a product id
		Verifies that id
		Prompts for a quantity
		Increases that product's quantity
	Repeats until user enters a sentinel key to exit
	**************************************************************************/
	public static void addShipment(){
		int supplierId, productId, quantity;
		Scanner scanner;
		boolean moreProducts = true;
		Iterator waitList;
		WaitListItem currItem;
		char choice;
		//Get supplier id:
		System.out.print("Please enter a supplier id: ");
		scanner = new Scanner(System.in);
		supplierId = scanner.nextInt();
		//Verify supplier id:
		if(!warehouse.verifySupplierId(supplierId)){
			System.out.println("Error, invalid supplier id. Aborting operation");
			return;
		}//end if
		while(moreProducts){
			//Get product id:
			System.out.print("Enter the received product's id: ");
			scanner = new Scanner(System.in); //flush input buffer
			productId = scanner.nextInt();
			//Verify product id:
			if(!warehouse.verifyProductId(productId)){
				System.out.println("Error, invalid product id. Aborting operation");
				return;
			}//end if
			//If valid, get product quantity
			System.out.print("Enter a quantity for the product: ");
			scanner = new Scanner(System.in); //flush buffer
			quantity = scanner.nextInt();
			//Increment product's quantity
			waitList = warehouse.addShippedItem(productId, quantity);
			//Receive waitList, prompt for any quantities that can be fulfilled.
			while(waitList.hasNext() ){
				currItem = waitList.next();
				if(currItem.getQuantity() < warehouse.getStock(productId)){
					System.out.println("Order " + currItem.getOrder().getId() + 
						" can be fulfilled with new stock.\n" +
						"Fulfill? (Y|N) ");
					scannner = new Scanner(System.in);
					choice = scanner.nextChar();
					if(choice == 'Y'){
						warehouse.fulfillWaitListItem( );
					} else{
						
					}//end else
				}//end if
			}//end while
		}//end while(moreProducts)
	}//end addShipment
	
/*************************** Generic prompt methods ******************************/
	/*** For prompts that are used many times in many applications ******************/

	/*********************************************************************
	getClientId
	Prompts user for client id, retrieves it and returns it
	**********************************************************************/
	public static int getClientId(){
		System.out.print("Please enter a client id: ");
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}//end getClientId()

	/*********************************************************************
	getProductId
	Prompts user for product id, retrieves it and returns it
	**********************************************************************/
	public static int getProductId(){
		System.out.print("Please enter a product id: ");
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}//end getProductId()


/************************** File reading methods **********************************/

	/******************************************************************************
	openWarehouse
	Opens the given Warehouse, or creates if it doesn't exist
	Returns the Warehouse object
	*******************************************************************************/
	public static void openWarehouse(){
			Warehouse w = Warehouse.retrieveData(FILENAME);
			if(w == null){
				System.out.println("Warehouse not found in file. Creating new Warehouse.");
				warehouse = Warehouse.instance();
			} else{
				System.out.println("Warehouse successfully read from file.");
				warehouse = w;
			}//end else
	}//end openWarehouse

	/******************************************************************************
	saveChanges
	Saves any changes made to the warehouse.
	******************************************************************************/
	public static void saveChanges(){
		if(warehouse.saveData(FILENAME) )
				System.out.println("Saved successfully");
		else
			System.out.println("Save failed. Error occured");
	}//end saveChanges

	/******************************************************************************
	instance()
	Called to create an instance of the UserInterface
	*****************************************************************************/
	public static UserInterface instance() {
		if(userInterface == null)
			return userInterface = new UserInterface();
		else
			return userInterface;
	}//end instance()

	public static void tester(){
		System.out.println("Running tester\n");
		ChristensonTesting CT = new ChristensonTesting();
		//CT.testProductList();
		CT.testAcceptShipment();
	}
}
