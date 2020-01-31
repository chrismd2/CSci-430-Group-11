/*
  File: ProjectTester.java

  Group 11
  NAMES:
    Hunter
    Brent
    Sabin
    Christenson, Mark

  SPRING 2020 Section 2

  COMMENTS: This program runs projects for CSci 430, Object Oriented with options
            numbered for each program
            #1 Project 1
*/
import test.AverageList;

public class ProjectTester
{


   public static void main(String[] args)
   {
      for(int i = 0; i < args.length; i++){
        switch(args[i]){
          case "1":
            System.out.println("Running Project 1");
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
