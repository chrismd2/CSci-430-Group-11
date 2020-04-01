package Source_Code;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Supplier implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String description;

  public Supplier(String description){
    id = (SupplierIdServer.instance() ).getId();
    this.description = description;
  }
  
  public int getId(){
	return id;
  }//end getId

  public void setDescription(String description){
	  this.description = description;
  }//end setDescription
  
  public String getDescription(){
	  return description;
  }//end getDescription
}

