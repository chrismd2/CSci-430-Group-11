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
										"Enter DISPLAY to call the toString method for the WH\n" +
										"Enter ADDCLIENT to add a client to the Warehouse\n" +
										"Enter FINDCLIENT to find information about a client - NOT IMPLEMENTED\n" + 
										"Enter ADDTOCART to add a product to a client's cart\n";
	final static String ORDEROPLIST = "ORDER OPERATIONS:\n" + 
									  "______________________________________________________\n" +
									  "Enter m for main menu | Enter e to quit\n" +
									  " options.... \n";
	final static String PRODUCTOPLIST = "PRODUCT OPERATIONS: \n" +
										"______________________________________________\n" +
										"Enter m for main menu | Enter e to quit\n" +
										"options.... \n";
	final static String SUPPLIEROPLIST = "SUPPLIER OPERATIONS \n" +
										 "______________________________________________\n" +
										 "Enter m for main menu | Enter e to quit\n" +
										 "options...\n";
										
	public static void main(String[] args){
		//Open the Warehouse:
		Scanner input = new Scanner(System.in);		
		System.out.println("Open saved warehouse?(Y|N)");
		String opt = input.next();
		if(opt.equals("Y"))
			openWarehouse();
		else{
			warehouse = new Warehouse();
			System.out.println("New Warehouse system created");
		}//end else
		//View or change:
		processInput();
		
		//Close the Warehouse:
		System.out.println("Save changes to warehouse?\n(Y|N)");
		opt = input.next();
		if(opt.equals("Y"))
			saveChanges();
		
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
	
			switch(inputStr){
				case "client":
				case "c":	//print list of queries
					System.out.println(CLIENTOPLIST);
					break;
				case "order":
				case "o":
					System.out.println(ORDEROPLIST);
					break;
				case "product":
				case "p":
					System.out.println(PRODUCTOPLIST);
					break;
				case "supplier":
				case "s":
					System.out.println(SUPPLIEROPLIST);
					break;
				case "ADDCLIENT":
					addClient(warehouse);
					break;
				case "FINDCLIENT": 

					break;
				case "DISPLAY":
					System.out.println(warehouse.toString() );
				case "exit":
				case "e":
					System.out.println("Exiting warehouse operations\n");
					break;
				case "m":
				case "M":
				case "main":
					System.out.println(MAINMENU);
					break;
				default:
					System.out.println("Entered text did not match an option; Please try again.");
			}//end switch
		}//end while
			
	}//end processInput

	/******************************************************************************
	addClient
	Code to prompt user for necessary information to add a new client to the Warehouse
	*******************************************************************************/
	public static void addClient(Warehouse w){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a name for the client: ");
		String name = input.next();
		System.out.print("Enter a phone number for the client: ");
		String phone = input.next();
		System.out.print("Enter an address for the client: ");
		String address = input.next();
		w.addClient(name, phone, address);
		System.out.println("Client added successfully");
	}//end addClient

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
}