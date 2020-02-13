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
package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ClientList implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Client> clients;
	private static ClientList clientList;
	
	//Default Constructor:
	public ClientList(){
		clients = new ArrayList<Client>();
	}//end ClientList
	
	public static ClientList instance() {
		if(clientList == null){
			return (clientList = new ClientList() );
		} else{
			return clientList;
		}//end else	
	}//end instance()

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

	private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }//end try-catch block
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (clientList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientList == null) {
          clientList = (ClientList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }//end try-catch block
  }
		
		
}
