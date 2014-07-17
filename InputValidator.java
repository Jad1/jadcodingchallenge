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
import java.util.regex.*;
public class InputValidator
{
 
    
    //Check what the input is and perform relevant action.
    public String checkInput(String input, int field)
    {
        String nextPart = "";
        if(input.equalsIgnoreCase("all"))
        {
            return "* \t";
        }
        
        //"any" is only allowed for one of "day of month" or "day of week" fields.
        else if(input.equalsIgnoreCase("any") && ((field == 3) || (field == 5)))
        {
            return "? \t";
        }
        
        //Check if inputted string represents a number.
        else if (isInteger(input))
        {
            return input;
        }
        
        //If input is a range then check that it is correct for the relevant field.
        else
        {
            switch(field)
            {
                case 0:
                case 1:
                    nextPart = translateRange(input, "^from [1-5]?[0-9] to [1-5]?[0-9]$");
                    return nextPart;
                case 2:
                    nextPart = translateRange(input, "^from ([1]?[0-9]|[2]?[0-3]) to ([1]?[0-9]|[2]?[0-3])$");
                    return nextPart;
                case 3:
                    break;
                case 4:
                    nextPart = translateRange(input, "^from ([1-9]|[1]?[0-2]) to ([1-9]|[1]?[0-2])$");
                    return nextPart;
                case 5:
                    nextPart = translateRange(input, "^from [1-7] to [1-7]$");
                    return nextPart;
                case 6:
                    nextPart = translateRange(input, "^from (19[7-9][0-9]|20[0-9]{2}) to (19[7-9][0-9]|20[0-9]{2})$");
                    return nextPart;
                default:
                    break;
            }
        }
        
        return "n/a";
    }
    
    
    //Check if a string represents an integer by checking its ASCII value.
    public boolean isInteger(String input)
    {
        char currentChar = ' ';
        for (int i = 0; i < input.length(); i++)
        {
            currentChar = input.charAt(i);
            if((int)currentChar < 48 || (int)currentChar > 57)
            {
                return false;
            }
        }
        return true;
    }
    
    //Check correctness of range of values.
    public String translateRange(String input, String regex)
    {
        Pattern rangePattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        int lowerBound = 0, upperBound = 0, temp = 0;
        
        //Check if seconds input matches the correct expression for ranges.
        Matcher secondsRangeMatcher = rangePattern.matcher(input);
        if(secondsRangeMatcher.matches())
        {
           //Remove words from input and replace with '-' to denote range.
           input = input.replaceAll("[A-Za-z]", "").trim();
           lowerBound = Integer.valueOf(input.substring(0, (input.indexOf(" "))));
           upperBound = Integer.valueOf(input.substring(input.indexOf(" ") + 2));
           
           //If values are wrong way round assume user made a mistake and swap them.
           if(lowerBound > upperBound)
           {
               temp = lowerBound;
               lowerBound = upperBound;
               upperBound = temp;
           }
           
           //If values are equal assume user wants to enter a single value.
           else if(lowerBound == upperBound)
           {
               return String.valueOf(lowerBound);
           }
           
           return lowerBound + "-" + upperBound + " ";
        }
        else
        {
            return "n/a";
        }
    }
}
