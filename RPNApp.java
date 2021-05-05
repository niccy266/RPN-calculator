import java.util.*;

public RPNApp {
  
  Scanner scan;
  Stack stack;
  
  public static void main(String[] args) {
    scan = new Scanner(System.in);
    stack = new Stack();
    
    
    readInput(scan)
  }
  
  /** Reads to the end of a scanner and interpreting data.
    * @param s scanner to read from
    **/
  public static void readInput(Scanner s) {
    while(s.hasNext()) {
      interpret(s); 
    }
    //saves memory by closing scanner when they are completed
    s.close()
  }
  
  /** Takes the next input from a scanner and does the appropriate operation.
    * @param in the scanner to read from and interpret
    */
  private static void interpret(Scanner s) {
    char in = s.nextChar();
    
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
    for (int i = 0; i < ) {
      bracketStream = new Scanner(inBrackets);
      
    }
  }
  
  private static String extractBrackets(Scanner s) {
    //string for storing the contents of the brackets
    StringBuilder inBrackets = new StringBuilder;
    //num of unclosed brackets
    int numBrackets = 1; 
    
    while(s.hasNext() && numBrackets > 0) {
      char in = s.nextChar();
      switch(in) {
        case ')' :
          numBrackets--;
          if(numBrackets == 0) {
            break;
          }
        case '(' :
          numBrackets++;
        default:
          inBrackets.append(in);
      }
    }
    
    return inBrackets.toString();
  }
  
  private static void closeBracket() {
    
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
