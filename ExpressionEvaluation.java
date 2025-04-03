/*
 * Define the stack interface and use it to implement the method for evaluating an infix integer arithmetic
 *  expression. The method will take the infix expression as a string and tokenize it to extract the integers
 *  and operators. You can assume the expression has only integer constants, and the tokens are separated using 
 * spaces. The expression will support variables, arithmetic operators (+, -, *, /), relational operators (==, !=,
 *  &lt;, &gt;, &lt;=, &gt;=), and boolean operators (&amp;&amp;, ||, !), and parentheses. You can assume that
 *  arithmetic operators will not be used in unary form. Use the Java rules for precedence and associativity of
 *  operators.
 */
import java.util.Scanner;

/**
 * Class for evaluating infix expressions with operator precedence.
 */
public class ExpressionEvaluation {
    private Stack<Integer> numStack; // Stack to store numbers
    private Stack<String> operatorStack; // Stack to store operators

    public ExpressionEvaluation() {
        this.numStack = new Stack<>(15);
        this.operatorStack = new Stack<>(15);
    }

    /**
     * Evaluates a binary operation between two integers.
     * 
     * @param operator A operation to perform between two value
     * @param val1     first value
     * @param val2     second value
     * @return return a result of perform operation between two value
     */
    private int evaluate(String operator, int val2, int val1) {
        switch (operator) {
            case "+":
                return val1 + val2;
            case "-":
                return val1 - val2;
            case "*":
                return val1 * val2;
            case "/":
                return val1 / val2;
            case "%":
                return val1 % val2;
            case "==":
                return (val1 == val2) ? 1 : 0;
            case "!=":
                return (val1 != val2) ? 1 : 0;
            case "<":
                return (val1 < val2) ? 1 : 0;
            case ">":
                return (val1 > val2) ? 1 : 0;
            case "<=":
                return (val1 <= val2) ? 1 : 0;
            case ">=":
                return (val1 >= val2) ? 1 : 0;
            case "&&":
                return (val1 != 0 && val2 != 0) ? 1 : 0;
            case "||":
                return (val1 != 0 || val2 != 0) ? 1 : 0;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    /**
     * Determines precedence of operators.
     */
    private int precedence(String operator) {
        switch (operator) {
            case "||":
                return 1;
            case "&&":
                return 2;
            case "==":
            case "!=":
                return 3;
            case "<":
            case ">":
            case "<=":
            case ">=":
                return 4;
            case "+":
            case "-":
                return 5;
            case "*":
            case "/":
            case "%":
                return 6;
            default:
                return -1;
        }
    }

    /**
     * Processes an infix expression and evaluates it.
     * 
     * @param expression A input expression to evalute it.
     * @return Evalution result of exression .
     */
    public int evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+")) { // If token is a number
                numStack.push(Integer.parseInt(token));
            } 
            else if (token.equals("(")) { // If token is an opening parenthesis
                operatorStack.push(token);
            } 
            else if (token.equals(")")) { // If token is a closing parenthesis
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processOperator();
                }
                operatorStack.pop();
            }
            else { // If token is an operator
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
                    processOperator();
                }
                operatorStack.push(token);
            }
        }

        // Process remaining operators
        while (!operatorStack.isEmpty()) {
            processOperator();
        }

        return numStack.pop(); // Final result
    }

    /**
     * Pops two values and an operator from their stacks and evaluates the
     * operation.
     */
    private void processOperator() {
        String operator = operatorStack.pop();
        int val2 = numStack.pop();
        int val1 = numStack.pop();
        numStack.push(evaluate(operator, val2, val1));
    }

    public static void main(String[] args) {
        ExpressionEvaluation evaluator = new ExpressionEvaluation();
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        System.out.println("Expression 1: " + evaluator.evaluateExpression(expression)); 
    }
}
