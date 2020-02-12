/**********************************************************************
UserInterface.java
This file contains code for the user to interact with the Warehouse System via a terminal.
***********************************************************************/
import Project_1.Source_Code.*;
import java.util.Scanner;
import java.util.Iterator;

public class UserInterface
{
	public static void main(String[] args){
		//Warehouse warehouse;

		Scanner input = new Scanner(System.in);

		String inputStr = "notExit";
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			System.out.println("type exit to quit | type client for client testing");
			if(inputStr.equals("client"))
				clientTest();
			inputStr = input.next();
		}

	}//end run
	
	/************************************************************************
	clientTest
	Objects tested:
		clientList
		client
		
	************************************************************************/
	public static void clientTest(){
	
		//Client:
			System.out.println("Now testing client");
			//Instantiation:
			System.out.println("Testing creation of a Client object.");
			Client c;
			c = new Client(1, "Brent", "777-999-0000", "1234 Some street, some city, some zip");
			System.out.println("Client object has been instantiated.");
			System.out.println("Testing setters and getters");
			
			//Name:
			System.out.println("Name:");
			System.out.print("Old client name " + c.getName() );
			c.setClientName("Kyle");
			System.out.println(" New client name " + c.getName() );
			
			//Id:
			System.out.println("id");
			System.out.print("Old client id: " + c.getId() );
			c.setClientId(10);
			System.out.println(" New client id: " + c.getId() );
		
			//Phone:
			System.out.println("Phone");
			System.out.print("Old client phone: " + c.getClientPhone() );
			c.setPhone("444-555-9999");
			System.out.println(" New client phone: " + c.getClientPhone() );
			
			//Address:
			System.out.println("Address");
			System.out.print("Old client address: " + c.getClientAddress() );
			c.setAddress("12345 New street .... ");
			System.out.println("New client address: " + c.getClientAddress() );
		
		//ClientList:
			System.out.println("\nNow testing ClientList");
			//Instantiation:
				ClientList cList;
				System.out.println("Testing instantiation of ClientList");
				cList = new ClientList();
				System.out.println("Empty ClientList has been created");
			
			//Adding a client:
				System.out.println("Testing adding clients");
				Client c1 = new Client(1, "Client1", "111-111-1111", "1111 1st street");
				Client c2 = new Client(2, "Client2", "222-222-2222", "2222 2st street");
				Client c3 = new Client(3, "Client3", "333-333-3333", "3333 3st street");
				cList.addClient(c1);
				cList.addClient(c2);
				cList.addClient(c3);
				System.out.println("Three clients have been added to ClientList with ids 1,2,3");
			
			//Finding a client based upon id
				System.out.println("\nTesting searching of ClientList");
				System.out.println("Searching ClientList for client2 (id is 2)");
				Client foundClient = cList.findClient(2);
				System.out.println("Found client's name is: " + foundClient.getName() );
			
				System.out.println("Searching ClientList for client1 (id is 1)");
				foundClient = cList.findClient(1);
				System.out.println("Found client's name is: " + foundClient.getName() );
				
				System.out.println("Searching ClientList for client3 (id is 3)");
				foundClient = cList.findClient(3);
				System.out.println("Found client's name is: " + foundClient.getName() );
			
			//Testing removing a client
				System.out.println("\nTesting removing client3");
				cList.removeClient( cList.findClient(3) );
				Client removedClient = cList.findClient(3);
				if(removedClient == null)
					System.out.println("Client successfully removed");
				else
					System.out.println("Client removal not successful");
				
			//Testing getting the clientList iterator
				System.out.println("\nTesting getting an iterator for ClientList");
				Iterator<Client> testIt = cList.getIterator();
				System.out.println("Printing address for each client: ");
				while(testIt.hasNext() )
					System.out.println( testIt.next().getClientAddress() );
				System.out.println("All of clientList has been iterated through");
				
			
	}//end clientTest
}
