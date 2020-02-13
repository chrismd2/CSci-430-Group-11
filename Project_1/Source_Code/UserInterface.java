/**********************************************************************
UserInterface.java
This file contains code for the user to interact with the Warehouse System via a terminal.
***********************************************************************/

//package Project_1.Source_Code;
package Source_Code;

import java.util.Scanner;

public class UserInterface
{
	public static void main(String[] args){
		//Warehouse warehouse;

		Scanner input = new Scanner(System.in);

		String inputStr = "notExit";
		while(!inputStr.equals("exit") && !inputStr.equals("e")){
			System.out.println("type exit to quit");
			inputStr = input.next();
		}

	}//end run
}
