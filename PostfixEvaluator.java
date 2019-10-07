package CalculatorApp;
import java.lang.Math;

public class PostfixEvaluator {

    static Float eval (String postfix){
        LinkedStack<Float> valueStack = new LinkedStack<>();


        while (postfix.length() > 0){
            int startOfNextChar = getNextCharacterAndRemove(postfix);
            String nextCharacter = postfix.substring(0, startOfNextChar);
            postfix = postfix.substring(startOfNextChar);
            if (isNumeric(nextCharacter)){
                valueStack.push(Float.parseFloat(nextCharacter));
            }
            else{
                switch(nextCharacter){
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case"^":
                        float operandTwo = valueStack.pop();
                        float operandOne = valueStack.pop();
                        float result = calculate(operandOne, operandTwo, nextCharacter);
                        valueStack.push(result);
                        break;
                    default: break;
                }
            }
        }
        return valueStack.peek();
    }

    private static float calculate(float operandOne, float operandTwo, String nextCharacter){
        switch (nextCharacter){
            case "+":
                return operandOne + operandTwo;
            case "-":
                return operandOne - operandTwo;
            case "*":
                return operandOne * operandTwo;
            case "/":
                return operandOne/operandTwo;
            case "^":
                return (float) Math.pow(operandOne,operandTwo);
        }
        return 0;
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
            Float d = Float.parseFloat(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
