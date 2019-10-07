package CalculatorApp;
import java.util.Stack;

public class InfixToPostfix {

  private static int precedence(char c){
    switch (c){
      case '(':
        return 1;
      case '+':
      case '-':
        return 2;
      case '*':
      case '/':
        return 3;

      case '^':
        return 4;
      default:
        return -1;
    }
  }

  static String convert(String infix) {
      LinkedStack<Character> operatorStack = new LinkedStack();
      StringBuilder postfix = new StringBuilder();

      String nextCharacter;
      while (infix.length() > 0) {
          int startOfNextChar = getNextCharacterAndRemove(infix);
          nextCharacter = infix.substring(0, startOfNextChar);
          infix = infix.substring(startOfNextChar);

          if (isNumeric(nextCharacter)) {
              postfix.append(nextCharacter + " ");
          } else {
              switch (nextCharacter) {
                  case "^": case "(":
                      operatorStack.push(nextCharacter.charAt(0));
                      break;
                  case "+":
                  case "-":
                  case "*":
                  case "/":
                      while (!operatorStack.isEmpty() && precedence(nextCharacter.charAt(0)) <= precedence(operatorStack.peek())) {
                          postfix.append(operatorStack.peek()+" ");
                          operatorStack.pop();
                      }
                      operatorStack.push(nextCharacter.charAt(0));
                      break;
                  case ")":
                      Character topOperator = operatorStack.pop();
                      while (topOperator != '(') {
                          postfix.append(topOperator + " ");
                          topOperator = operatorStack.pop();
                      }
                      break;
                  default:
                      break;
              }
          }
      }

      while (!operatorStack.isEmpty()) {
          Character topOperator = operatorStack.pop();
          postfix.append(topOperator + " ");
      }

      return postfix.toString();
  }

  private static int getNextCharacterAndRemove(String input){
    int i = 1;
    if (Character.isDigit(input.charAt(0))){
      while (i < input.length() && Character.isDigit(input.charAt(i)))
        i++;
    }

    return i;
  }

  private static boolean isNumeric(String strNum) {
    try {
        int d = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
        return false;
    }
    return true;
  }

  public static boolean checkBalance(String infix){
      StackInterface<Character> openDelimiterStack = new LinkedStack<>();
      int charCount = infix.length();
      boolean isBalanced = true;
      int i = 0;
      char nextCharacter = ' ';

      while (isBalanced && (i < charCount)){
          nextCharacter = infix.charAt(i);
          switch (nextCharacter){
              case '(': case '[': case '{':
                  openDelimiterStack.push(nextCharacter);
                  break;
              case ')': case ']': case '}':
                  if (openDelimiterStack.isEmpty())
                      isBalanced = false;
                  else{
                      char openDelimiter = openDelimiterStack.pop();
                      isBalanced = isPaired(openDelimiter, nextCharacter);
                  }
                  break;
              default: break;
          }
          i++;
      }
      if (!openDelimiterStack.isEmpty())
          isBalanced = false;
      return isBalanced;
  }

  private static boolean isPaired(char open, char close){
      return (open == '(' && close == ')') ||
              (open == '[' && close ==']') ||
              (open == '{' && close == '}');
  }
}
