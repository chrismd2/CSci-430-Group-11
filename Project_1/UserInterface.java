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
	final static String QUERYLIST = "CLIENT OPERATIONS:\n" +
										"______________________________________________\n" +
										"Enter DISPLAY to call the toString method for the WH\n" +
										"Enter ADDCLIENT to add a client to the Warehouse\n" +
										"Enter FINDCLIENT to find information about a client\n";

	public static void main(String[] args){
		Warehouse warehouse;

		Scanner input = new Scanner(System.in);
		
		System.out.println("Open saved warehouse?\n (Y|N)");
		String opt = input.next();
		if(opt.equals("Y"))
			warehouse = openWarehouse();
		else
			warehouse = new Warehouse();
		
		processInput(warehouse);

		System.out.println("Save changes to warehouse?\n(Y|N)");
		opt = input.next();
		if(opt.equals("Y"))
			saveChanges(warehouse);
		
	}//end run

	/*************************************************************************
	processInput
	Called every cycle of the testing loop. When user specifies query, this tests to see
	which query was selected, and activates the code for that query.
	*************************************************************************/
	public static void processInput(Warehouse warehouse){
		Scanner input = new Scanner(System.in);
		String inputStr = "";
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			System.out.println("type exit to quit | type q for a list of queries that can be used: ");
			inputStr = input.next();
	
			switch(inputStr){
				case "query":
				case "q":	//print list of queries
					System.out.println(QUERYLIST);
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
				default:
					System.out.println("Entered text did not match an option; Please try again.");
			}//end switch
		}//end while
			
	}//end processInput

	/******************************************************************************
	openWarehouse
	Opens the given Warehouse, or creates if it doesn't exist
	Returns the Warehouse object
	*******************************************************************************/
	public static Warehouse openWarehouse(){
		Warehouse w = null;
		try{
	
			FileInputStream inFile = new FileInputStream("WareData");
			ObjectInputStream inObj = new ObjectInputStream(inFile);
			
			w = (Warehouse) inObj.readObject();
			
			inObj.close();
			inFile.close();
			
			System.out.println("Read in saved warehouse successfully\n");
		
		}  catch(ClassNotFoundException cnfe){
			System.out.println("Class not found exception throw in reading input file\n");
		} catch(IOException ioe){
			System.out.println("IO Exception thrown in reading input file.");
		}//end try-catch block
		return w;
	}//end openWarehouse

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
	saveChanges
	Saves any changes made to the warehouse.
	******************************************************************************/
	public static void saveChanges(Warehouse w){
		try{
			FileOutputStream outFile = new FileOutputStream("WareData");
			ObjectOutputStream outObj = new ObjectOutputStream(outFile);
			
			outObj.writeObject(w);
			
			outObj.close();
			outFile.close();
			
			System.out.println("File has been saved");
		} catch(IOException ioe){
			System.out.println("Exception thrown in saving changes\n");
		}//end try-catch block
	}//end saveChanges
}
