/*******************************************************************************
ManagerMenuState.java
Contains all options for the managers that are utilizing the UI
Written by: Brent Clapp		Date: 04/01/2020
********************************************************************************/
import Source_Code.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class ManagerMenuState{
	private final static String MENUOPTIONS = "		MANAGER MENU	\n" +
									"_____________________________________" +
									"Enter a to add a product\n" +
									"Enter s to add a supplier\n" +
									"Enter v to view all suppliers\n" +
									"Enter l to view suppliers of a product\n" +
									"Enter p to view all products of a suppliers\n" +
									"Enter n to add a supplier for a product\n" +
									"Enter m to modify the purchase price of a product from a supplier\n" +
									"Enter c to login as a salesclerk\n" +
									"Enter q to logout\n";
	private static Warehouse warehouse;
 	final static String FILENAME = "WareData";
	private static ManagerMenuState ManagerMenuState;

	/*****************************************************************
	performMenu
	Continuously displays the menu for the user and receives their choice.
	Sends that input to processUserChoice()
	*****************************************************************/
	public static void performMenu(){
		boolean getNextOption = true;
		Scanner s = new Scanner(System.in);
		String choice;
		openWarehouse();
		while(getNextOption){
			//Print the menu
			System.out.println(MENUOPTIONS);
			//Get the user"s selection
			choice = s.nextLine();
			//Process that chosen option
			getNextOption = processUserChoice(choice);
		}//end getNextOption
	}//end performMenu

	private static boolean processUserChoice(String choice){
		boolean iterateAgain = true;
		switch(choice.toLowerCase()){
			case "a":
				addProduct(); break;
			case "s":
				addSupplier(); break;
			case "v":
				viewAllSuppliers(); break;
			case "l":
				viewSuppliersOfProduct(); break;
			case "p":
				viewProductsOfSupplier(); break;
			case "n":
				addSupplierForProduct(); break;
			case "m":
				modifyPurchasePrice(); break;
			case "c":
				loginAsClerk(); break;
			case "q":
				System.out.println("Logging out");
				iterateAgain = false; break;
			default:
				System.out.println("Invalid input; Try again");
		}//end switch
		return iterateAgain;
	}//end processUserChoice

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

	/**************************************************************************
	addProduct
	Provides necessary prompts for the user to add a product to the Warehouse
	Allows user to repeat as many times as needed
	**************************************************************************/
	private static void addProduct(){
	  boolean adding = true;
      Scanner input;
      while(adding){
   		input = new Scanner(System.in);
         System.out.print("Enter the id of the supplier that stocks this product: ");
         int supplier = input.nextInt();
         if(!warehouse.verifySupplier(supplier)){
            System.out.println("Invalid supplier id. Teminating operation");
            return;
        }//end if
         input = new Scanner(System.in);//clear buffer
   		System.out.print("Enter a description for the product: ");
   		String description = input.nextLine();
   		System.out.print("Enter a purchase price for the product: ");
   		String purchasePrice = input.nextLine();
   		System.out.print("Enter an sale price for the product: ");
   		String salePrice = input.nextLine();
   		System.out.print("Enter a stock for the product: ");
   		int stock = input.nextInt();
   		warehouse.addProduct(description, Double.valueOf(purchasePrice), Double.valueOf(salePrice), stock, supplier);
   		System.out.println("Product added successfully");
         //Check to see if they want to add another product
         System.out.print("Add another product? (Y|N) ");
         input = new Scanner(System.in);
         adding = (input.next() == "Y");
      }//end while
	}//end addProduct

	/**********************************************************************************
	addSupplier
	Code to prompt user for necessary information to add a new supplier to the warehouse
	*********************************************************************************/
	private static void addSupplier(){
		String description;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a description for the supplier:");
		description = s.nextLine();
		warehouse.addSupplier(description);
		System.out.println("Supplier added successfully");
	}//end addSupplier

	/*********************************************************************
	viewAllSuppliers
	Displays all suppliers registered in the warehouse
	**********************************************************************/
	private static void viewAllSuppliers(){
		Iterator it = warehouse.getSuppliers();
		System.out.println("Suppliers:");
		System.out.println("____________________");
		while(it.hasNext())
			System.out.println(it.next().toString());
	}//end viewAllSuppliers

	/*********************************************************************
	viewSuppliersOfProduct
	User passes in the id of the product.
	Interface displays all suppliers that supply that product, along with
	their sale price for the product.
	**********************************************************************/
	private static void viewSuppliersOfProduct(){

	}//end viewSuppliersOfProduct

	private static void viewProductsOfSupplier(){

	}//end viewProductsOfSupplier

	private static void addSupplierForProduct(){

	}//end addSupplierForProduct

	private static void modifyPurchasePrice(){

	}//end modifyPurchasePrice

	private static void loginAsClerk(){

	}//end loginAsClerk

	/*************************************************************************
	instance()
	Used to implement class as singleton. Returns an instance of the ManagerMenuState
	**************************************************************************/
	public static ManagerMenuState instance() {
		if(ManagerMenuState == null)
			return ManagerMenuState = new ManagerMenuState();
		else
			return ManagerMenuState;
	}//end ManagerMenuState
}//end class
