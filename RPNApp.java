import java.util.*;

public RPNApp {
  
  Scanner scan;
  Stack stack;
  
  public static void main(String[] args) {
    scan = new Scanner(System.in);
    stack = new Stack();
    
    while(scan.hasNext()) {
      nextInput(); 
    }
    
  }
  
  private static void nextInput() {
    char in = scan.nextChar();
    
    switch(in) {
        
      case '+' :
      case '-' :
      case 'x' :
      case '/' :
        operate(in);
        break;
      case '(' :
        openBracket();
        break;
      case ')' :
        closeBracket();
        break;
      default :
        pushNum(in);
    }
  }
    
  private static void operate(char in) {
    
  }
    
  private static void openBracket(char in) {
    
  }
    
  private static void closeBracket(char in) {
    
  }
  
    
  private static void pushNum(char in) {
    
  }
  
  private static void error(String message) {
    
  }
  
}
