/*****************************************************************
ClientMenuState.java
Responsible individual: Sabin Basnet

1.	In the ClientMenuState, the Context has stored the ClientID for the current client; all operations are for that ClientID. The state will have operations for the following:
(a)	Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.
(b)	Show list of products with sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(c)	Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
(d)	Edit client's shopping cart. Change quantities of products in the shopping cart. Facade provides the iterator.
(e)	Add to client's shopping cart. Actor provides the product id and quantity; invoke method on FaÃ§ade.
(f)	Display client's waitlist.
(g)	Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClientMenuState.)
*****************************************************************/

import java.util.Iterator;
import java.util.Scanner;

import Source_Code.*;

public class ClientMenuState{
	private static Warehouse warehouse;
	private int clientId;
	private static ClerkMenuState clerkMenuState;
  final static String MAINMENU =  ""+
        "CLIENT MENU OPTIONS                                                  \n\t"+
          "a. Show Client detail                                        (DISPLAYCLIENTDETAILS)\n\t"+
          "b. Show list of products                                     (DISPLAYPRODUCTSLIST)\n\t"+
          "c. Show client transactions                                  (DISPLAYCLIENTTRANSACTIONS)\n\t"+
          "d. Edit client's shopping cart and change the quantities of product on it (DISPLAYSHOPPINGCART)\n\t"+
          "e. Add client shopping cart                                           (ADDSHOPPINGCART)\n\t"+
          "f. Display the client waitlist                                (DISPLAYCLIENTWAITLIST)\n\t"+
          "g. Logout                                                         \n\n";

  /******************************************************************************

	/*********************************************************************
	getProductId
	Prompts user for product id, retrieves it and returns it
	**********************************************************************/
	public static int getProductId(){
		System.out.print("Please enter a product id: ");
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		while(!warehouse.verifyProduct(id)){
			System.out.print("Invalid id given, re-enter: ");
			id = s.nextInt();
		}//end if
		return id;	
	}//end getProductId()

	/*******************************************************************************
	displayClientDetails
	Displays the details of the client that is logged in
	*******************************************************************************/
	private static void displayClientsDetails(){
		Iterator it = warehouse.getClients();
		Client c = warehouse.findClient(clientId);
		System.out.println(c);
	}//end displayAllProducts

	/*******************************************************************************
	displayProductList
	Displays all products in the system, along with their sale prices
	*******************************************************************************/
	private static void displayProductList(){
		Iterator it = warehouse.getProducts();
		Product p;
		while(it.hasNext() ){
			p = (Product)it.next();
			System.out.println(p.getDescription() + "\tPrice: $" + p.getSalePrice() );
		}//end while
	}//end displayAllClients

  /**************************************************************************
  displayClientTransactions()
  Prompts the user for a client id.
  Asks the user if they'd like to display detailed data (This includes the items
                              that were charged for)
  Displays the date and relevant data for each invoice in the client's history
  ***************************************************************************/
  private void displayClientTransaction(){
    Iterator invoiceIt;
    boolean choice;
    System.out.print("Would you like to display detailed transactions? (Y|N) ");
    choice = new Scanner(System.in).next().equals("Y"); //true if Y
    invoiceIt = warehouse.getInvoiceIt(clientId);
    if(choice)
      while(invoiceIt.hasNext() )
        System.out.println(((invoiceIt.next())).detailedString());
    else
      while(invoiceIt.hasNext() )
        System.out.println(((invoiceIt.next())).toString());
  }//end displayInvoices()

  /******************************************************************************
  showWaitList
  Displays the waitlist for the client
  Poor design choices in the back end have made this sloppy
  ******************************************************************************/
  private static void showWaitList(){
     Iterator productIt, waitListIt;
	 WaitListItem currItem;
	 boolean productFound = false;
	 //Get the list of products
	 productIt = warehouse.getProducts();
     //For each product, check its waitlist
	 while(productIt.hasNext(){
		waitListIt = ((Product)productIt.next()).getWaitList();
		//For each item in waitlist, check if it belongs to this client
		while(waitListIt.hasNext()){
			currItem = (WaitListItem)waitListIt.next();
			//If it does belong to the client, then display it
			if(currItem.getOrder().getClient().getId() == clientId){
				System.out.println(currItem.getProduct().getDescription() + "\tQt: " + currItem.getQuantity());
				productFound = true;
			}//end if
		}//end while
	 }//end while
	 if(!productFound)
		System.out.println("Client has no waitlisted items");
  }//end showWaitList
	       
	       
/*	        //editing and adding the products in the shopping cart
	public class shop {
    public void main(String[] args) {
	   
	  Scanner sc = new Scanner(System.in);
	    ArrayList<ItemList> cart = new ArrayList<ItemList>();

	    ItemList item;
	    int itemID;
	    String itemName;
	    double itemPrice;
	    String itemDescription;
	    int itemQuantity;
	    double itemTax;
	    int ch;
	    String choice;

	    ProductList shoppingCart = new ProductList();

	    while (true) {
	        System.out.println("Menu:");
	        System.out.println("0) Exit " + "\n"
	                + "1) Add item in shopping cart" + "\n"
	                + "2) Remove item from shpping cart");
	        ch = sc.nextInt();

	        switch (ch) {
	        case 0:
	            System.out.println("\n" + "Good bye!");
	            System.exit(0);

	        case 1:
	            System.out.println("Enter item ID: ");
	            itemID = sc.nextInt();

	            System.out.println("Enter item name: ");
	            itemName = sc.next();

	            System.out.println("Enter item price: ");
	            itemPrice = sc.nextDouble();

	            System.out.println("Enter short description of item: ");
	            itemDescription = sc.next();

	            System.out.println("Enter quantity: ");
	            itemQuantity = sc.nextInt();

	            System.out.println("Enter tax rate:");
	            itemTax = sc.nextDouble();


	            shoppingCart.add(itemID, itemName, itemPrice, itemDescription, itemQuantity,       itemTax);

	            break;

	        case 2:
	            System.out.println("Enter name of the item that you would like to remove: ");
	            choice = sc.next();
	            shoppingCart.remove(choice);

	            break;
	        }

	    }
	}

	}
	       */
	       
	       
	       
	       

  public static void processInput(Warehouse w){
		warehouse = w;
		
		//Get client id:
		System.out.print("Enter your client id: ");
		Scanner input = new Scanner(System.in);
		clientID = input.nextInt();
	    if(!warehouse.verifyClient(clientId)){
			System.out.println("Error, invalid client id. Aborting operation");
			return;
		}//end if
		
		//Perform loop:
		String inputStr = "";
		System.out.println(MAINMENU);
		while(!inputStr.equals("exit") && !inputStr.equals("g")){
			inputStr = input.next();
			
			switch(inputStr.toUpperCase()){
				case "EXIT":
					System.out.println("Exiting warehouse operations\n");
          break;
        case "A":
				case "DISPLAYCLIENTDETAILS":
				case "SHOWCLIENTDETAIL  ":
					displayClientsDetails();
          break;
        case "B":
        case "DISPLAYPRODUCTSLIST":
        case "SHOWLISTOFPRODUCTS":
          displayProductList();
          break;
        case "C":
        case "DISPLAYCLIENTTRANSACTIONS":
        case "SHOWCLIENTTRANSACTIONS":
          //clerkMenuState.displayAllClients();
					System.out.println("ERROR: clerkMenuState.displayAllClients() is a private clerk function");
          break;
        case "D":
        case "DISPLAYSHOPPINGCART":
        case "EDITCLIENTSSHOPPINGCARTANDCHANGETHEQUANTITIESOFPTODUCTSONIT":
          //displayShoppingCart();
          break;
        case "E":
          break;
        case "F":
        case "Add":
        case "DISPLAYTHECLIENTWAITLIST":
          showWaitList();
          break;
        case "G":
					System.out.println("Logging out of client\n");
          break;
        default:
          System.out.print("ERROR: Invalid option\n" + MAINMENU);
          break;
      }//end switch
    }//end while
  }//end processInput
}//end ClientMenuState class
