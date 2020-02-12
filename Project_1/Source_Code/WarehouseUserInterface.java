/**********************************************************************
WarehouseUserInterface.java
This file contains code for the user to interact with the Warehouse System via a terminal.
It does so just as UserInterface.java but from the project tester class.
This and UserInterface.java should only differ by name and package declaration.
***********************************************************************/

package Project_1.Source_Code;

import java.util.Scanner;

public class WarehouseUserInterface
{
	public static void run(){
		//Warehouse warehouse;

		Scanner input = new Scanner(System.in);

		String inputStr = "notExit";
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			System.out.println("type exit to quit");
			inputStr = input.next();
		}

	}//end run
}
