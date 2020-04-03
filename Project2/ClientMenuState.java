/*****************************************************************
ClientMenuState.java
Responsible individual: Sabin Basnet

1.	In the ClientMenuState, the Context has stored the ClientID for the current client; all operations are for that ClientID. The state will have operations for the following:
(a)	Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.
(b)	Show list of products with sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(c)	Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
(d)	Edit clientâ€™s shopping cart. Change quantities of products in the shopping cart. Facade provides the iterator. 
(e)	Add to clientâ€™s shopping cart. Actor provides the product id and quantity; invoke method on FaÃ§ade.
(f)	Display clientâ€™s waitlist.
(g)	Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClientMenuState.)
*****************************************************************/

import java.util.Iterator;
import java.util.Scanner;

import Source_Code.*;

public class ClientMenuState{
	private static Warehouse warehouse;
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
		return s.nextInt();
	}//end getProductId()
	
	/*******************************************************************************
	displayClientDetails
	Displays all Product objects in the system.
	*******************************************************************************/
	private static void displayClientsDetails(){
		Iterator it = warehouse.getClients();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllProducts

	/*******************************************************************************
	displayProductList
	Displays all client objects in the system.
	*******************************************************************************/
	private static void displayProductList(){
		Iterator it = warehouse.getProducts();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllClients

  /**************************************************************************
  displayClientTransactions()
  Prompts the user for a client id.
  Asks the user if they'd like to display detailed data (This includes the items
                              that were charged for)
  Displays the date and relevant data for each invoice in the client's history
  ***************************************************************************/
  private void displayClientTransaction(){
    int clientId = clerkMenuState.getClientId();
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
        System.out.println(((invoiceIt.next())).toString());
    else
      while(invoiceIt.hasNext() )
        System.out.println(((invoiceIt.next())).toString());
  }//end displayInvoices()

  /******************************************************************************
  showWaitList
  Gets the product id, then displays its wait list
  ******************************************************************************/
  private static void showWaitList(){
     int productID = getProductId();
     Iterator it;
     if(!warehouse.verifyProduct(productID)){
        System.out.println("Error, invalid product id. Aborting operation");
        return;
     }//end if
     it = warehouse.getProductWaitList(productID);
     System.out.println("Product: \n" + warehouse.findProduct(productID).toString());
     if(!it.hasNext())
        System.out.println("Product has no wait list currently");
     else{
        System.out.println("Wait list:\n"+
                           "__________________");
        while(it.hasNext())
           System.out.println(((WaitListItem)it.next()).toString());
     }//end else
  }//end showWaitList

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
	private void addShipment(){
		int supplierId, productId, quantity, quantCount;
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
		if(!warehouse.verifySupplier(supplierId)){
			System.out.println("Error, invalid supplier id. Aborting operation");
			return;
		}//end if
		while(moreProducts){
			//Get product id:
			System.out.print("Enter the received product's id (0 to quit): ");
			scanner = new Scanner(System.in); //flush input buffer
			productId = scanner.nextInt();
         if(productId == 0) //Sentinel value to return with
            return;
			//Verify product id:
			if(!warehouse.verifyProduct(productId)){
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
			quantCount = 0; //Reset quant count for new item
			while(waitList.hasNext() ){
				currItem = (WaitListItem)waitList.next();
				if((currItem.getQuantity() + quantCount)<= warehouse.getStock(productId)){
					System.out.println("Order " + currItem.getOrder().getId() +
						" can be fulfilled with new stock.\n" +
                  " Current stock: " + warehouse.getStock(productId) +
                  " Order quantity needed: " + currItem.getQuantity() +
						"\nFulfill? (Y|N) ");
					scanner = new Scanner(System.in);
					choice = scanner.next().charAt(0);
					if(choice == 'Y'){
                  System.out.println("Fulfilling order");
						warehouse.fulfillWaitListItem(productId, currItem);
						quantCount += currItem.getQuantity();
					}
				}//end if
			}//end while
			warehouse.doneAddingfulfillItems();
		}//end while(moreProducts)
	}//end addShipment

  public static void processInput(){
		Scanner input = new Scanner(System.in);
		String inputStr = "";
		System.out.println(MAINMENU);
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
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
          clerkMenuState.displayAllClients();
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
          break;
        default:
          System.out.print("ERROR: Invalid option\n" + MAINMENU);
          break;
      }//end switch
    }//end while
  }//end processInput
}//end ClientMenuState class