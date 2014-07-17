/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crontranslator;

/**
 *
 * @author Jad
 */

import java.util.*;
public class CronTranslator 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       String cronTranslation = "";
        System.out.println("Use the following words/statements in your cron (case is not important):");
        System.out.println("");
        System.out.println("For all values: all");
        System.out.println("For any (no specific) value: any");
        System.out.println("You can also use single numbers.");
        System.out.println("For a range of values: from lower_bound_value to upper_bound_value");
        System.out.println("Use 1-7 for Sunday through to Saturday and 1-12 for January through to December.");
        System.out.println("Use 0-23 for hours and 0-59 for minutes.");
        System.out.println("");
        
       cronTranslation = tempName();
       System.out.println(cronTranslation);
       
    }
    
    public static String tempName()
    {
        Scanner input = new Scanner(System.in);
        StringBuilder fullTranslation = new StringBuilder("S \t M \t H \t DoM \t M \t DoW \t Y \n");
        InputValidator validator = new InputValidator();
        String currentFieldTranslation;
        String nextUserInput = "";
        
        /*Iterate seven times, once for each field in the cron string.
          output message for each field. */

        for (int i = 0; i < 7; i++) 
        {
            nextUserInput = "";
            switch(i)
            {
                case 0:
                    System.out.println("Enter desired second(s)");
                    break;
                case 1:
                    System.out.println("Enter desired minute(s)");
                    break;
                case 2:
                    System.out.println("Enter desired hour(s)");
                    break;
                case 3:
                    System.out.println("Enter desired day(s) of month");
                    break;
                case 4:
                    System.out.println("Enter desired month(s)");
                    break;
                case 5:
                    System.out.println("Enter desired day(s) of week");
                    break;
                case 6:
                    System.out.println("Enter desired year(s)");
                    break;    
                default:
                    break;
            }
            nextUserInput = input.nextLine();
            currentFieldTranslation = validator.checkInput(nextUserInput, i);
            
            /*If no translation could be returned then repeat loop, 
            otherwise append transltion to end of string.*/
            if(!(currentFieldTranslation.equals("n/a")))
            {
                fullTranslation.append(currentFieldTranslation).append("\t");
            }
            else
            {
                i--;
            }
        }
        return fullTranslation.toString();
    }
    
}
