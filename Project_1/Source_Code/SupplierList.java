//package Project_1.Source_Code;
package Source_Code;

import warehouse.Supplier;
import java.util.*;
import java.io.*;
public class SupplierList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List Suppliers = new LinkedList();
  private static SupplierList SupplierList;
  
  private SupplierList() {
  }
  public static SupplierList instance() {
    if (SupplierList == null) {
      return (SupplierList = new SupplierList());
    } else {
      return SupplierList;
    }
  }
  public boolean insertSupplier(Supplier Supplier) {
    Suppliers.add(Supplier);
    return true;
  }

  public Iterator getSuppliers(){
     return Suppliers.iterator();
  }
  
  public Supplier search(String SupplierID)
  {
      for (Iterator iterator = Suppliers.iterator(); iterator.hasNext(); )
      {
          Supplier Supplier = (Supplier) iterator.next();
          if (Supplier.getId().equals(SupplierID))
          {
              return Supplier;
          }
      }
      return null;
  }
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(SupplierList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (SupplierList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (SupplierList == null) {
          SupplierList = (SupplierList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return Suppliers.toString();
  }
}


