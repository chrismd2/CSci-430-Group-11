/**********************************************************************
UserInterface.java
This file contains code for the user to interact with the Warehouse System via a terminal.
***********************************************************************/
import Project_1.Source_Code.*;
import java.util.Scanner;
import java.util.Iterator;

public class UserInterface
{
	final static String QUERYLIST = "CLIENT OPERATIONS:\n" +
										"______________________________________________\n" +
										"Enter OPENWARE to open or create the Warehouse\n" +
										"Enter ADDCLIENT to add a client to the Warehouse\n" +
										"Enter REMOVECLIENT to remove a client from the Warehouse\n" +
										"Enter FINDCLIENT to find information about a client\n";
										
	public static void main(String[] args){
		//Warehouse warehouse;

		Scanner input = new Scanner(System.in);

		String inputStr = "notExit";
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			System.out.println("type exit to quit | type q for a list of queries that can be used");
			processInput(inputStr);
			inputStr = input.next();
		}

	}//end run
	
	/*************************************************************************
	processInput
	Called every cycle of the testing loop. When user specifies query, this tests to see
	which query was selected, and activates the code for that query.
	*************************************************************************/
	public static void processInput(String str){
		switch(str){
			case "query":
			case "q":	//print list of queries
				System.out.println(QUERYLIST);
				break;
			case "OPENWARE":
				//do something to open the warehouse, or create if doesn't exist
				break;
			case "ADDCLIENT":
				
				break;
			case "REMOVECLIENT":
				
				break;
			case "FINDCLIENT":
				
				break;
			default:
				System.out.println("Entered text did not match an option; Please try again.");
		}//end switch
	
	
	}//end processInput
	
}


