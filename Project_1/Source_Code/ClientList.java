/*****************************************************************************
	ClientList.java
	Responsible Individual: ClientList
	Contains all of the Clients registered with the Warehouse
	fields:
		clients - The list of all clients
	methods:
		ClientList() - Default Constructor - Constructs an empty ClientList
		void addClient(Client c) - Adds the passed Client to the list
		void removeClient(Client c) - Removes the referenced Client from the list
		Client findClient(int id) - Returns the Client object with the given id. Returns null if not found.
		Iterator getIterator() - Returns an iterator that will iterate across each Client object.
******************************************************************************/
//package Project_1.Source_Code;
package Source_Code;

import java.util.Iterator;
import java.util.ArrayList;

public class ClientList{
	ArrayList<Client> clients;

	//Default Constructor:
	public ClientList(){
		clients = new ArrayList<Client>();
	}//end ClientList

	//addClient:
	public void addClient(Client c){
		clients.add(c);
	}//end addClient()

	//removeClient:
	public void removeClient(Client c){
		clients.remove(c);
	}//end removeClient()

	//findClient:
	public Client findClient(int id){
		Iterator it = clients.iterator();
		Client curr = null;
		boolean done = false;
		while(!done && it.hasNext() ){
			curr = (Client)it.next();
			if(curr.getId() == id)
				done = true;
		}//end while
		if(!done){
			System.out.println("Client not found.");
			curr = null;
		}//end if
		return curr;
	}//end findClient

	//getIterator:
	public Iterator<Client> getIterator(){
		return clients.iterator();
	}//end getIterator
}
