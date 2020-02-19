/******************************************************************************
SupplierIdServer.java
Implements automatic Id generation for suppliers, and prevents duplicate ids from being created.

******************************************************************************/
package Source_Code;

import java.io.*;
import java.lang.*;
import java.util.*;

public class SupplierIdServer implements Serializable{
	private int nextId;
	private static SupplierIdServer thisServer;
	private SupplierIdServer(){
		nextId = 1;
	}
	public static SupplierIdServer instance() {
		if(thisServer == null)
			return (thisServer = new SupplierIdServer());
		else
			return thisServer;
	}//end instance()
	
	public int getId(){
		return nextId++;
	}//end getId
	
	public String toString(){
		return ("SupplierIdServer. nextId: " + nextId + '\n');
	}//end toString

	public static void retrieve(ObjectInputStream in){
		try{
			thisServer = (SupplierIdServer) in.readObject();
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
				thisServer = (SupplierIdServer) in.readObject();
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