import java.util.*;


/** 
 * RPNApp
 * This class is a calculator that takes inputs in RPN - Reverse Polish Notation.
 * When a number is read, it is pushed onto the stack.
 * When an operator is read, it will operate on the top two numbers on the stack
 * and push the result back onto the stack.
 * For example, if you want to add 1 and 2, the RPN instruction would be: "1 2 +"
 * If there are not enough operands an error will be raised.
 * Operators can be repeated until the stack is reduced to 1 result by adding an !
 * Accepted operators are + - * / and %. repeat them using +! -! *! /! %!
 * Brackets are also accepted. When brackets are encountered, the instructions
 * inside the brackets are repeates n times, where n is the number on top of the stack
 * For example, "1 3 ( 2 * )" gives [8]
 * 
 * The calculator accepts several special instructions
 * d - Duplicate the top number on the stack, eg; [1, 2] will become [1, 2, 2].
 * c - Copy. Take the top two numbers from the stack y and x and push x, y times,
 *     eg; [1, 2, 4] will become [1, 2, 2, 2, 2].
 * r - Rotate the stack. Takes the number on top of the stack and moves it to the bottom,
 *     eg; [1, 2, 3] will become [3, 1, 2].
 * o - Outputs the top number on the stack to the console.
 * 
 * @author Nicolas Concha
 * @author Ravin Pitawala
 * @author Danuda Jayawardena
 **/
public class RPNApp {
  
  private static Stack<Integer> stack;
  
  /** 
   * Main function, creates stack and reads input from System.in.
   * Starts reading from the stream and 
   * @param args system arguments
   **/
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    stack = new Stack<Integer>();
    
    readInput(scan);
    System.out.println(stack.toString());
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
      case "h" :
        help();
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
    int y = stack.pop();
    //duplicates the number y times, starting with one copy still on the stack
    for (int i = 1; i < y; i ++) {
      duplicate();
    }
  }
  
  /** 
   * peeks at the number on top of the stack and pushes it again, duplicating it
   **/
  protected static void duplicate() {
    int x = stack.peek();
    stack.push(x);
  }
  
  /** 
   * outputs the last number on the stack to System.out
   **/
  protected static void output() {
    
  }
  
  /** 
   * takes the number on top of the stack and moves it down k - 1 places
   **/
  protected static void rotate() {
    //creates a temporary stack to hold stack's values
    Stack<Integer> tempStack = new Stack<Integer>;
    //takes the top value from stack and sets it aside
    int top = stack.pop();
    //empties the stack onto the temporary stack
    while (!stack.empty()) {
      tempStack.push(stack.pull());
    }
    //puts the value that was at the top at the bottom
    stack.push(top);
    //moves the other values back from 
    while (!tempStack.empty()) {
      stack.push(tempStack.pull());
    }
  }
  
  /** 
   * takes the number on top of the stack and moves it to the bottom
   **/
  protected static void help() {
    System.out.println("This app is a calculator that takes inputs in RPN - Reverse Polish Notation.\n"
                      + "When a number is read, it is pushed onto the stack.\n"
                      + "When an operator is read, it will operate on the top two numbers on the stack\n"
                      + "and push the result back onto the stack.\n"
                      + "For example, if you want to add 1 and 2, the RPN instruction would be: \"1 2 +\"\n"
                      + "If there are not enough operands an error will be raised.\n"
                      + "Operators can be repeated until the stack is reduced to 1 result by adding an !\n"
                      + "Accepted operators are + - * / and %. repeat them using +! -! *! /! %!\n"
                      + "Brackets are also accepted. When brackets are encountered, the instructions\n"
                      + "inside the brackets are repeates n times, where n is the number on top of the stack\n"
                      + "For example, \"1 3 ( 2 * )\" gives [8]\n"
                      + "\n"
                      + "The calculator accepts several special instructions\n"
                      + "d - Duplicate the top number on the stack, eg; [1, 2] will become [1, 2, 2].\n"
                      + "c - Copy. Take the top two numbers from the stack y and x and push x, y times,\n"
                      + "    eg; [1, 2, 4] will become [1, 2, 2, 2, 2].\n"
                      + "r - Rotate the stack. Takes the number on top of the stack and moves it to the bottom,\n"
                      + "    eg; [1, 2, 3] will become [3, 1, 2].\n"
                      + "o - Outputs the top number on the stack to the console.\n");
    System.exit(0);
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
      error("unmatched parentheses");
    }
    
    return inBrackets.toString();
  }
  
  
  /** 
   * Raises error if there is a closing parenthesis without a matching opening parenthesis.
   **/
  protected static void closeBracket() {
    error("unmatched parentheses");
  }
  
  /** 
   * Parses the input to an integer and pushes it to the stack.
   * @param in input char
   **/
  protected static void pushNum(String in) {
    //try
    //stack.push(5);
  }
  
  
  /** 
   * Method for handling errors easily.
   * Prints a description of the error and closes the program
   * @param message a brief explanation of what went wrong
   **/
  public static void error(String message) {
    System.out.println("Error: " + message);
    System.exit(1);
  } 
}
