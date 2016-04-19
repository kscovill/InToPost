import java.io.IOException;

import javax.swing.JOptionPane;

// Infix to Post fix with help from http://www.tutorialspoint.com/javaexamples/data_intopost.htm
public class InToPost {
   private Stack theStack;
   private char[] input;
   private String output = "";
   
   // Determines the length of the equation and sends the length to make a new Stack
   public InToPost(char[] in) {
      input = in;
      int stackSize = input.length;
      theStack = new Stack(stackSize);
   }
   
   // Does the checking to see where each character should go, Stack or output
   
   public String doTrans() {
      for (int j = 0; j < input.length; j++) {
         char ch = input[j];
         switch (ch) {
         // If + or - runs gotOper with the character and 1
            case '+': 
            case '-':
            gotOper(ch, 1); 
            break; 
            
            // if * or / run gotOper with the character and 2
            case '*': 
            case '/':
            gotOper(ch, 2); 
            break; 
            
            //If ( then push the character to the stack
            case '(': 
            theStack.push(ch);
            break;
            
            //if ) then run gotParen with the character
            case ')': 
            gotParen(ch); 
            break;
            //if number push to output
            default: 
            output = output + ch; 
            System.out.println("pushed to output: " + ch);
            break;
         }
      }
      while (!theStack.isEmpty()) {
    	  char q = theStack.pop();
         output = output + q;
         System.out.println("Pushed to output: "+ q);
      }
     // System.out.println(output);
      return output; 
   }
   
   // Code from source, modified to show iterations
   // Checks the precedence of PEMDAS
   public void gotOper(char opThis, int prec1) {
      while (!theStack.isEmpty()) {
         char opTop = theStack.pop();
         if (opTop == '(') {
            theStack.push(opTop);
            break;
         }
         else {
            int prec2;
            if (opTop == '+' || opTop == '-')
            prec2 = 1;
            else
            prec2 = 2;
            if (prec2 < prec1) { 
               theStack.push(opTop);
               break;
            }
		    else
            output = output + opTop;
            System.out.println("Pushed to output: " + opTop);
         }
      }
      theStack.push(opThis);
   }
   
   //Checks for where the Parenthesis are
   public void gotParen(char ch){ 
      while (!theStack.isEmpty()) {
         char chx = theStack.pop();
         if (chx == '(') 
         break; 
         else
         output = output + chx; 
         System.out.println("pushed to output: " + chx);
      }
   }
   
   //MAIN
   //Asks for the input, checks the input
   
   public static void main(String[] args) throws IOException {
	  boolean no = false;
	  String input = JOptionPane.showInputDialog(null, "Please enter an equation:");
      
	  //Loops through asking for input until the input satisfies that it is only characters such as +, -, /, *, and numbers
	   while(true){
	      System.out.println(input);
	      for(int i = 0; i<input.length();i++){
	    	  if(input.charAt(i)== ')' ||input.charAt(i)== '(' || input.charAt(i)== '/' || input.charAt(i)== '+' || input.charAt(i)== '-' || input.charAt(i)== '*'
	    			  || input.charAt(i)== '1' || input.charAt(i)== '2' || input.charAt(i)== '3' || input.charAt(i)== '4' || input.charAt(i)== '5' || input.charAt(i)== '5'
	    			  || input.charAt(i)== '6' || input.charAt(i)== '7' || input.charAt(i)== '8' || input.charAt(i)== '9' || input.charAt(i)== '0')
	    	  {
	    		  
	    	  }else{
	    		  no = true;
	    		  
	    	  }
	      }
	      if(no == false){
	    	  break;
	      }else{
	    	  System.out.println("Please enter a valid equation");
    		  input = JOptionPane.showInputDialog(null, "Please enter an equation:");
    		  no = false;
	      }
	  }
	   
	   // Once accepted, makes the characters in to an array and runs TheTrans
	   char[] into = input.toCharArray();
      System.out.println("Input is: " + input);
      String output;
      InToPost theTrans = new InToPost(into);
      output = theTrans.doTrans(); 
      
      //once TheTrans is finished it prints the final postfix version
      System.out.println("Postfix is " + output);
   }
   
   //Taken directly from http://www.tutorialspoint.com/javaexamples/data_intopost.htm
   class Stack {
      private int maxSize;
      private char[] stackArray;
      private int top;
      //Creates a stack with -1 at the top
      public Stack(int max) {
         maxSize = max;
         stackArray = new char[maxSize];
         top = -1;
      }
      
      //Pushes the current character to the stack
      public void push(char j) {
         stackArray[++top] = j;
         System.out.println("Pushed to Stack: "  + j);
      }
      //Pops the top character of the stack off
      public char pop() {
    	  char k;
    	  k = stackArray[top--]; 
    	  System.out.println("Popped from Stack: " + k);
         return k;
         
      }
      //Lets the user look at the top of the stack
      public char peek() {
         return stackArray[top];
      }
      // checks to see if the stack is empty
      public boolean isEmpty() {
         return (top == -1);
     }
   }
}