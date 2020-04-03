import java.util.*;
import java.io.*;
import java.lang.*;

public class UserInterface {
	final static String MAINMENU =  ""+
	        "SELECT STATE          \n\t"+
	          "1. Client Menu State\n\t"+
	          "2. Clerk Menu State\n\t"+
	          "3. Manager Menu State\n\n";

  public static void main(String args[]){
			//Creating warehouse
      Scanner s = new Scanner(System.in);
      System.out.println(MAINMENU);
      String choice = s.nextLine();
      switch(choice){
        case "1":
          ClientMenuState.processInput();
          break;
        case "2":
          ClerkMenuState.processInput();
          break;
        case "3":
          ManagerMenuState.performMenu();
          //System.out.println("ERROR: manager operations are unavailable at this time");
          break;
        default:
          System.out.println("ERROR: invalid option, exiting warehouse");
      }//end switch
	}//end main
}
