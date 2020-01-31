/**
  * NAME:     Christenson, Mark
  * DATE:     20200120
  * FILE:     AverageList
  * COMMENTS: This program opens one of four files by using numbers in arguments
              #1 is standard use case with no negatives
              #2 is empty
              #3 has a negative in the middle
              #4 has a negative in the front
  */
package test;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AverageList
{
   public void avg(String[] args)
   {
      // declare variables
      String fileName = "";
      boolean error = false;
      int size = 4;
      int arr[] = new int[size];

      switch(args.length){
        case 0:
          System.out.print("ERROR: Too few arguments.  Choose first or second file\n");
          error = true;
          break;
        case 1:
          System.out.print("File #" + args[0] +" called\n");
          switch(Integer.parseInt(args[0])){
            case 1:
              fileName = "First.txt";
              break;
            case 2:
              fileName = "Second.txt";
              break;
            case 3:
              fileName = "Third.txt";
              break;
            case 4:
              fileName = "Fourth.txt";
              break;
            default:
              System.out.print("ERROR: Undefined option\n");
              error = true;
            }
          break;
        case 2:
          System.out.print("ERROR: Too many arguments.  Remove one and try again\n");
          error = true;
          break;
        default:
          System.out.print("ERROR: Default case called\n");
          error = true;
      }
      if(!error){
        try{
          System.out.print("Opening " + fileName + "\n");
          FileInputStream file = new FileInputStream(fileName);
          try{
            int a = file.read();
            int i = 0;
            while(a >= 0 && !error){
              if((char)a != ',' && (char)a != '-'){
                arr[i] = arr[i] * 10;
                arr[i] += a - (int)'0';
              }
              if((char)a == ','){
                i++;
              }
              if((char)a == '-'){
                System.out.println("ERROR: negative number found");
                error = true;
              }
              a = file.read();
            }

            if(i > 0){
              double avg = 0;
              if(!error){
                for(int j = 0; j < size; j++){
                  avg += arr[j];
                }
                avg/=size;
                System.out.print("Average = " + avg + "\n");
              }
            }
            if(i == 0){
              System.out.println("ERROR: empty file found");
              error = true;
            }
          } catch(Exception e2){System.out.print("Read error\n");}
        } catch (FileNotFoundException e){System.out.print("File not found\n");}
      }
      if(error){System.out.print("ERROR: could not compute the average\n");}
   } // close main
} // close AverageList
