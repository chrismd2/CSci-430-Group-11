/**********************************************************************
UserInterface.java
This file contains code for the user to interact with the Warehouse System via a terminal.
***********************************************************************/
import java.util.Scanner;
import java.util.Iterator;

public class UserInterface
{
	public static void ProductTester(){

	    ProductList objList = new ProductList();

	    Product obj = new Product();

	    obj.setData();

			for(int i = 0; i < 10; i++){
				    obj.setProductNumber("x");
				    obj.setDescription("some product");
				    obj.setPurchasePrice(15.26+i);
				    obj.setSalePrice(23.34+i);

						objList.insertProduct(obj);
			}


	    objList.showList();

	}
	final static String QUERYLIST = "CLIENT OPERATIONS:\n" +
										"______________________________________________\n" +
										"Enter OPENWARE to open or create the Warehouse\n" +
										"Enter ADDCLIENT to add a client to the Warehouse\n" +
										"Enter REMOVECLIENT to remove a client from the Warehouse\n" +
										"Enter FINDCLIENT to find information about a client\n"+
										"Enter PRODUCT to test product\n";

	public static void main(String[] args){
		Warehouse warehouse = null;

		Scanner input = new Scanner(System.in);

		String inputStr = "notExit";
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			System.out.println("type exit to quit | type q for a list of queries that can be used");
			warehouse = processInput(inputStr, warehouse);
			inputStr = input.next();
		}

	}//end run

	/*************************************************************************
	processInput
	Called every cycle of the testing loop. When user specifies query, this tests to see
	which query was selected, and activates the code for that query.
	*************************************************************************/
	public static Warehouse processInput(String str, Warehouse warehouse){
		switch(str){
			case "query":
			case "q":	//print list of queries
				System.out.println(QUERYLIST);
				break;
			case "OPENWARE":
				warehouse= openWarehouse();
				System.out.println("Warehouse opened");
				break;
			case "ADDCLIENT":
				addClient(warehouse);
				break;
			case "REMOVECLIENT":

				break;
			case "FINDCLIENT":

				break;
			case "PRODUCT":
				ProductTester();
				break;
			default:
				System.out.println("Entered text did not match an option; Please try again.");
		}//end switch


		return warehouse;
	}//end processInput

	/******************************************************************************
	openWarehouse
	Opens the given Warehouse, or creates if it doesn't exist
	Returns the Warehouse object
	*******************************************************************************/
	public static Warehouse openWarehouse(){
									/********************** NOTE ******************************************
									Serializable is not implemented at the moment, so I am just going to create the Warehouse
									every time without checking. This method will need a significant overhaul when that time comes.
									*********************END NOTE **************************************/
		Warehouse w = new Warehouse();
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
		System.out.print("Enter an id for the client: ");				// To be removed once automatic functionality implemented.
		int id = input.nextInt();
		System.out.print("Enter a phone number for the client: ");
		String phone = input.next();
		System.out.print("Enter an address for the client: ");
		String address = input.next();
		w.addClient(name, phone, address, id);
		System.out.println("Client added successfully");
	}//end addClient
}
