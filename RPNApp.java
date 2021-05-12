package week10;
import java.util.*;

/**
 * RPNApp
 * This is a calculator that takes inputs in RPN - Reverse Polish Notation.
 * When a number is read, it is pushed onto the stack.
 * When an operator is read, it will operate on the top two numbers on the stack
 * and push the result back onto the stack.
 * For example, if you want to add 1 and 2, the instruction would be: "1 2 +"
 * If there are not enough operands an error will be raised.
 * Operators can be repeated until the stack is reduced to 1 item by adding an !
 * Accepted operators are + - * / and %. repeat them using +! -! *! /! %!
 * Brackets are also accepted. Instructions inside the brackets are repeated 
 * n times, where n is the number on top of the stack.
 * For example, "1 3 ( 2 * )" gives [8]
 *
 * The calculator accepts several special instructions
 * d - Duplicate the top number on the stack, eg; [1, 2] will become [1, 2, 2].
 * c - Copy. Take the top two numbers from the stack y and x and push x, y times
 *     eg; [1, 2, 4] will become [1, 2, 2, 2, 2].
 * r - Rotate the stack. Takes the number on top of the stack and moves it down 
 *     n - 1 spaces, where n is the number on top of the stack.
 *     eg; performing r on [1, 2, 3, 2] will become [1, 3, 2].
 * o - Outputs the top number on the stack to the console.
 *
 * @author Nicolas Concha
 * @author Ravin Pitawala
 * @author Danuda Jayawardena
 **/
public class RPNApp {

    private static Stack < Long > stack;

    /**
     * Main function, creates stack and reads input from System.in.
     * Starts reading from the stream and
     * @param args system arguments
     **/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //System.out.println("starting");

        while (scan.hasNextLine()) {
            stack = new Stack < Long > ();
            Scanner s = new Scanner(scan.nextLine());
            try {
                readInput(s);
                System.out.println(stack.toString());
            } catch (EmptyStackException e) {
                System.out.println("Error: too few operands");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Reads to the end of a scanner and interprets inputs.
     * @param s scanner to read from
     * @throws Exception if any errors occurred while processing
     **/
    protected static void readInput(Scanner s) throws Exception {
        while (s.hasNext()) {
            interpret(s);
        }
        //saves memory by closing scanners when they are completed
        s.close();
    }

    /**
     * Takes the next input from a scanner and does the appropriate operation.
     * @param s the scanner to read from and interpret
     * @throws Exception if any errors occurred while processing the token
     */
    protected static void interpret(Scanner s) throws Exception {
        String in = s.next();
        //System.out.println("read input " + in);

        switch ( in ) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                operate( in );
                break;

            case "+!":
            case "-!":
            case "*!":
            case "/!":
            case "%!":
                repeat( in .substring(0, 1));
                break;

            case "c":
                copy();
                break;
            case "d":
                duplicate();
                break;
            case "o":
                output();
                break;
            case "r":
                rotate();
                break;

            case "(":
                openBracket(s);
                break;

            case ")":
                error("unmatched parentheses");
                break;

            default:
                pushNum( in );
        }
    }

    /**
     * Takes the last two numbers on the stack and
     * performs the specified operation.
     * @param in the operation to perform
     * @throws Exception Exception if any operations were illegal or the stack was empty
     **/
    protected static void operate(String in ) throws Exception {
        try {
            long b = stack.pop();
            long a = stack.pop();
            long result = 0;

            switch ( in ) {
                case "+":
                    result = a + b;
                    break;

                case "-":
                    result = a - b;

                    break;
                case "*":
                    result = a * b;

                    break;
                case "/":
                    if (b == 0) {
                        error("Division by zero");
                    }
                    result = a / b;

                    break;
                case "%":
                    if (b == 0) {
                        error("remainder by zero");
                    }
                    result = a % b;
                    break;
            }
            stack.push(result);
        } catch (EmptyStackException e) {
            error("too few operands");
        }
    }

    /**
     * repeats the operate function until the stack is reduced to 1 number
     * @param in the operator to repeat
     * @throws Exception if the stack was empty
     **/
    protected static void repeat(String in ) throws Exception {
        //System.out.println("repeating operator " + in);
        while (stack.size() > 1) {
            operate( in );
        }
    }

    /**
     * takes the last two numbers on the stack, y and x and 
     * pushes x, y times onto the stack
     * @return a boolean that is true if the function had no errors.
     * @throws Exception if there weren't enough numbers in the stack
     **/
    protected static void copy() throws Exception {
        //gets number of times to duplicate x
        long y = longToInt(stack.pop());
        //starting from 1 because x is still on the stack
        int i = 1;
        //makes y copies of x on the stack
        while (i < y) {
            duplicate();
            i++;
        }
    }

    /**
     * peeks at the number on top of the stack and pushes it, duplicating it
     **/
    protected static void duplicate() {
        long x = stack.peek();
        stack.push(x);
    }

    /**
     * outputs the last number on the stack to System.out.
     **/
    protected static void output() {
        System.out.print(stack.peek() + " ");
    }


    /**
     * converts a long to an int. 
     * @param l long to convert to int
     * @throws Exception if the number couldn't be converted
     **/
    protected static int longToInt(Long l) throws Exception {
        int n = 0;
        try {
            //gets number of places to move the top value by
            n = l.intValue();
        } catch (Exception e) {
            error("input " + l + " isn't integer");
        }
        return n;
    }

    /**
     * takes the number on top of the stack and moves it down k - 1 places
     * @throws Exception if there weren't enough numbers on the stack
     **/
    protected static void rotate() throws Exception {
        long t = stack.pop();
        int n = longToInt(t) - 1;
        //creates a temporary stack to store the values between the top and the 
        //new position
        Stack < Long > tempStack = new Stack < Long > ();

        //takes the top value from stack and sets it aside
        long top = stack.pop();
        //moves k-1 values from the stack onto the temporary stack
        for (int i = 0; i < n; i++) {
            tempStack.push(stack.pop());
        }

        //puts the value that was at the top into it's new position
        stack.push(top);
        //moves the other values back from
        for (int i = 0; i < n; i++) {
            stack.push(tempStack.pop());
        }
    }


    /** Reads until it finds a close bracket and
     * repeats the operations a number of times
     * equal to number on top of the stack.
     * @param s scaner to read brackets from
     * @throws Exception if the brackets aren't closed or an error occure while 
     *   processing the instructions inside the brackets
     **/
    protected static void openBracket(Scanner s) throws Exception {
        //System.out.println("Opening brackets");

        //getting the symbols inside the brackets and creating a new scanner for it
        String inBrackets = extractBrackets(s);
        Scanner bracketStream;

        //number of times to repeat brackets
        int n = longToInt(stack.pop());
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
     * @throws Exception if the brackets aren't closed
     **/
    protected static String extractBrackets(Scanner s) throws Exception {
        //string for storing the contents of the brackets
        StringBuilder inBrackets = new StringBuilder();
        //num of unclosed brackets
        int numBrackets = 1;

        //finds the matching closing bracket
        while (s.hasNext() && numBrackets > 0) {
            char in = s.next().charAt(0);;
            switch ( in ) {
                //if it's an opening bracket or any other symbol, 
                //include it in the string
                case '(':
                    numBrackets++;
                    inBrackets.append( in +" ");
                    break;

                case ')':
                    //marks a bracket as closed
                    numBrackets--;
                    //if this was the final closing bracket,
                    //don't include it in the return string
                    if (numBrackets > 0) {
                        inBrackets.append( in +" ");
                    }
                    break;
                    //adds all other characters to the inBrackets string
                default:
                    inBrackets.append( in +" ");
            }
        }


        //if not all the brackets were closed, raise an error
        if (numBrackets > 0) {
            error("unmatched parentheses");
            s.nextLine();
            return "";
        }

        return inBrackets.toString();
    }


    /**
     * Parses the input to an integer and pushes it to the stack.
     * @param in input char
     * @throws Exception if the input token isn't a number
     **/
    protected static void pushNum(String in ) throws Exception {
        try {
            stack.push(Long.parseLong( in ));
        } catch (NumberFormatException e) {
            error("bad token '" + in +"'");
        }
    }


    /**
     * Method for handling errors easily.
     * Prints a description of the error and closes the program
     * @param message a brief explanation of what went wrong
     * @throws Exception an exception with a formatted message
     **/
    public static void error(String message) throws Exception {
        throw new Exception("Error: " + message);
        //System.exit(1);
    }
}
