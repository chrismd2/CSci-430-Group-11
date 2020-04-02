/******************************************************************************
ClientIdServer.java
Implements automatic Id generation for clients, and prevents duplicate ids from being created.

******************************************************************************/
package Source_Code;

import java.io.*;
import java.lang.*;
import java.util.*;

public class ClientIdServer implements Serializable{
	private int nextId;
	private static ClientIdServer thisServer;
	private ClientIdServer(){
		nextId = 1;
	}
	public static ClientIdServer instance() {
		if(thisServer == null)
			return (thisServer = new ClientIdServer());
		else
			return thisServer;
	}//end instance()
	
	public int getId(){
		return nextId++;
	}//end getId
	
	public String toString(){
		return ("ClientIdServer. nextId: " + nextId + '\n');
	}//end toString
	
	public static void retrieve(ObjectInputStream in){
		try{
			thisServer = (ClientIdServer) in.readObject();
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}//end try-catch
	}//end retrieve()
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		try{
			out.defaultWriteObject();
			out.writeObject(thisServer);
		} catch(IOException e){
			e.printStackTrace();
		}//end try-catch
	}//end writeObject
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		try{
			in.defaultReadObject();
			if(thisServer == null){
				thisServer = (ClientIdServer) in.readObject();
			} else{
				in.readObject();
			}//end if-else
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} //end try-catch
	}//end readObject
}//end ClientIdServer class