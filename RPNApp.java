import java.util.*;


/** 
 * RPN calculator
 * @author Nicolas Concha
 * @author Ravin Pitawala
 * @author Danuda Jayawardena
 **/
public class RPNApp {
  
  private static Scanner scan;
  private static Stack<Integer> stack;
  
  /** 
   * Main function, creates stack and reads input from System.in.
   * Starting the interpreting cycle.
   * @param args system arguments
   **/
  public static void main(String[] args) {
    scan = new Scanner(System.in);
    stack = new Stack<Integer>();
    
    readInput(scan);
  }
  
  
  /** 
   * Reads to the end of a scanner and interprets inputs.
   * @param s scanner to read from
   **/
  protected static void readInput(Scanner s) {
    while(s.hasNext()) {
      interpret(s); 
    }
    //saves memory by closing scanners when they are completed
    s.close();
  }
  
  
  /** 
   * Takes the next input from a scanner and does the appropriate operation.
   * @param s the scanner to read from and interpret
   */
  protected static void interpret(Scanner s) {
    String in = s.next();
    
    switch(in) {
      case "+" :
      case "-" :
      case "*" :
      case "/" :
      case "%" :
        operate(in);
        break;
        
      case "+!" :
      case "-!" :
      case "*!" :
      case "/!" :
      case "%!" :
        repeat(in.substring(0, 0));
        break;
        
      case "c" :
        copy();
        break;
      case "d" :
        duplicate();
        break;
      case "o" :
        output();
        break;
      case "r" :
        rotate();
        break;
        
      case "(" :
        openBracket(s);
        break;
      case ")" :
        closeBracket();
        break;
        
      default :
        pushNum(in);
    }
  }
  
  /** 
   * Takes the last two numbers on the stack and
   * performs the specified operation.
   * @param in the operation to perform
   **/
  protected static void operate(String in) {
    
  }
  
  /** 
   * repeats the operate function until the stack is reduced to 1 number
   * @param in the operator to repeat
   **/
  protected static void repeat(String in) {
    system.out.println(in);
    
    if (stack.empty()) {
      error("too few operands");
    }
    while (stack.size() > 1) {
      operate(in);
    }
  }
  
  /** 
   * takes the last two numbers on the stack, y and x and pushes x, y times onto the stack
   **/
  protected static void copy() {
    //gets number of times to duplicate x
    int n = stack.pop();
    for (int i = 0; i < n; i ++) {
      duplicate();
    }
  }
  
  /** 
   * peeks at the number on top of the stack and pushes it again, duplicating it
   **/
  protected static void duplicate() {
    int top = stack.peek();
    stack.push(top);
  }
  
  /** 
   * outputs the last number on the stack to System.out
   **/
  protected static void output() {
    
  }
  
  /** 
   * takes the number on top of the stack and moves it to the bottom
   **/
  protected static void rotate() {
    
  }
  
  /** Reads until it finds a close bracket and 
   * repeats the operations a number of times
   * equal to number on top of the stack.
   **/
  protected static void openBracket(Scanner s) {
    //getting the symbols inside the brackets and creating a new scanner for it
    String inBrackets = extractBrackets(s);
    Scanner bracketStream;
    
    //number of times to repeat brackets
    int n = stack.pop();
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
  protected static String extractBrackets(Scanner s) {
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
          inBrackets.append(in + " ");
      }
    }
    
    //if not all the brackets were closed, raise an error
    if (numBrackets > 0) {
      error("mismatched brackets");
    }
    
    return inBrackets.toString();
  }
  
  
  /** 
   * Raises error if there is a closing parenthesis
   * without an opening parenthesis preceding it at some point
   **/
  protected static void closeBracket() {
    error("mismatched brackets");
  }
  
  /** 
   * Parses the input to an integer and pushes it to the stack.
   * @param in input char
   **/
  protected static void pushNum(String in) {
    //try
    //stack.push(5);
  }
  
  
  /** Method for printing error messages easily. 
   * @param message breif explanation of what went wrong
   **/
  public static void error(String message) {
    System.out.println("Error: " + message);
    System.exit(0);
  } 
}
