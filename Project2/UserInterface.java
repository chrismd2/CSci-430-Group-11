import java.util.*;
import java.io.*;
import java.lang.*;

public class UserInterface {
  public static void main(String args[]){
			//Creating warehouse
      Scanner s = new Scanner(System.in);
      String choice = s.nextLine();
      switch(choice){
        case "1":
          System.out.println("ERROR: client operations are unavailable at this time");
          break;
        case "2":
          ClerkMenuState.processInput();
          break;
        case "3":
          //ManagerMenuState.performMenu();
          System.out.println("ERROR: manager operations are unavailable at this time");
          break;
        default:
          System.out.println("ERROR: invalid option, exiting warehouse");
      }//end switch
	}//end main
}
