/*
  File: ProjectTester.java

CSci 430 Object Oriented Programming
  - Group 11
  - NAMES:
     > Brent Clapp
     > Sabeen Basnet
     > Mark Christenson

  - SPRING 2020 Section 2

  COMMENTS: This program runs projects for CSci 430, Object Oriented with options
            numbered for each program
            #1 Project 1: Warehouse
              - p ProductTester
            #2 Project 2
*/
import test.AverageList;
import Project_1.Source_Code.Product;

public class ProjectTester
{
  static void showOptionsList(){
    System.out.print("Current Options:\n\t");
    System.out.print("1: Run Warehouse\n\t\t");
    System.out.print("1p: Test Product from Warehouse\n\t");
    System.out.print("avg: test with averaging project\n\t");
  }

  static void ProductTester(){
    Project_1.Source_Code.Product obj = new Project_1.Source_Code.Product();
    obj.setData();
    obj.setProductNumber("1A");
    obj.setDescription("some product");
    obj.setPurchasePrice(15.26);
    obj.setSalePrice(23.34);
    System.out.println(obj.getData());
    obj.setData(obj.getProductNumber() + ".1B", obj.getDescription()+" collected with some other product data to test the getters", obj.getPurchasePrice() + 15.33, obj.getSalePrice() + 33.2);
    System.out.println(obj.getData());
  }


   public static void main(String[] args)
   {
      if(args.length == 0){
        System.out.println("ERROR: Options are required");
        showOptionsList();
      }

      for(int i = 0; i < args.length; i++){
        switch(args[i]){
          case "1":
            System.out.println("Running Project 1: Warehouse");
            break;
          case "1p":
            System.out.println("Testing Product from Warehouse");
            ProductTester();
            break;
          case "2":
            System.out.println("Running Project 2");
            break;
          case "avg":
            String arr[] = new String [1];
            test.AverageList obj = new test.AverageList();
            arr[0] = "1";
            obj.avg(arr);
            break;
          default:
            System.out.println("ERROR: Undefined option");
        }
      }

   } // close main
} // close ProjectTester
