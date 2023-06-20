# RPNApp

This is a calculator that takes inputs in RPN - Reverse Polish Notation.

When an operator is read, it will operate on the top two numbers on the stack and push the result back onto the stack. For example, if you want to add 1 and 2, the instruction would be: "1 2 +". If there are not enough operands an error will be raised.


Accepted operators are + - * / and %. Operators can be repeated until the stack is reduced to 1 item by adding an ! like so: +! -! *! /! %!

Brackets are also accepted. Instructions inside the brackets are repeated n times, where n is the number on top of the stack.
For example, "1 3 ( 2 * )" gives [8]. The three is pulled from the stack, then the 1 is multiplied by 2 for three repreats, giving 8. 

The calculator accepts several special instructions

  d - Duplicate the top number on the stack, eg; [1, 2] will become [1, 2, 2].
  
  c - Copy. Take the top two numbers from the stack y and x and push x, y times
      eg; [1, 2, 4] will become [1, 2, 2, 2, 2].
  
  r - Rotate the stack. Takes the number on top of the stack and moves it down
      n - 1 spaces, where n is the number on top of the stack.
      eg; performing r on [1, 2, 3, 2] will become [1, 3, 2].
  
  o - Outputs the top number on the stack to the console.
