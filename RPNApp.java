import java.util.*;


/** 
  * @author Nicolas Concha
  * @author 
  * @author 
  **/
public class RPNApp {
  
  Scanner scan;
  Stack stack;
  
  /** Main function, creates stack and reads input from System.in.
    * Starting the interpreting cycle.
    * @param args system arguments
    **/
  public static void main(String[] args) {
    scan = new Scanner(System.in);
    stack = new Stack();
    
    readInput(scan);
  }
  
  
  /** Reads to the end of a scanner and interpreting data.
    * @param s scanner to read from
    **/
  public static void readInput(Scanner s) {
    while(s.hasNext()) {
      interpret(s); 
    }
    //saves memory by closing scanner when they are completed
    s.close();
  }
  
  
  /** Takes the next input from a scanner and does the appropriate operation.
    * @param in the scanner to read from and interpret
    */
  private static void interpret(Scanner s) {
    char in = s.next().charAt(0);
    
    switch(in) {
      case '+' :
      case '-' :
      case 'x' :
      case '/' :
        operate(in);
        break;
      case '(' :
        openBracket(s);
        break;
      case ')' :
        closeBracket();
        break;
      default :
        pushNum(in);
    }
  }
  
  /** Takes the last two numbers on the stack and
    * performs the specified operation.
    * @param in the operation to perform
    **/
  private static void operate(char in) {
    
  }
  
  /** Reads until it finds a close bracket and 
    * repeats the operations a number of times
    * equal to number on top of the stack.
    **/
  private static void openBracket(Scanner s) {
    //getting the symbols inside the brackets and creating a new scanner for it
    String inBrackets = extractBrackets(s);
    Scanner bracketStream;
    
    //number of times to repeat brackets
    n = stack.pop();
    for (int i = 0; i < n; i++) {
      //resets the bracket scanner so it can be repeated
      bracketStream = new Scanner(inBrackets);
      //completes the operations within the brackets
      readInput(bracketStream);
    }
  }
  
  /** Finds the instructions within a pair of brackets.
    * Searches until a closing bracket is found, preserves 
    * recursive brackets by keeping track of the number of unclosed brackets
    * @param s the string to read bracket data from
    * @return string of characters between the brackets
    **/
  private static String extractBrackets(Scanner s) {
    //string for storing the contents of the brackets
    StringBuilder inBrackets = new StringBuilder();
    //num of unclosed brackets
    int numBrackets = 1; 
    
    //finds the matching closing bracket
    while(s.hasNext() && numBrackets > 0) {
      char in = s.next().charAt(0);;
      switch(in) {
        case ')' :
          //marks a bracket as closed
          numBrackets--;
          //if this was the final closing bracket, don't include it in the return string
          if(numBrackets == 0) {
            break;
          }
        //if it's an opening bracket or any other symbol, include it in the string
        case '(' :
          numBrackets++;
        default:
          inBrackets.append(in);
      }
    }
    
    //if not all the brackets were closed, raise an error
    if (numBrackets > 0) {
      error("mismatched brackets");
    }
    
    return inBrackets.toString();
  }
  
  
  /** Raises error if there is a closing parenthesis
    * without an opening parenthesis preceding it at some point
    **/
  private static void closeBracket() {
    error("mismatched brackets");
  }
  
  /** Parses the input to an integer and pushes it to the stack.
    * @param in input char
    **/
  private static void pushNum(char in) {
    
  }
  
  /** Method for printing error messages easily. **/
  private static void error(String message) {
    
  } 
}
