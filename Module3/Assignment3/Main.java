import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.Iterator;

public class Main {

  public static void main(String[] args) {
    ExpressionEvaluator eval = new ExpressionEvaluator();

    try {
      File mathFile = new File("test.math");
      Scanner in = new Scanner(mathFile);

      while (in.hasNextLine()) {
        String data = in.nextLine().replace(';', '\0');
        // System.out.print(data);
        System.out.println(eval.evaluate(data));
      }
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}

class Stack<T> implements Iterable<T> {
  // A Stack using the First in Last out Data Structure
  private class StackIterator implements Iterator<T> {
    // Creates an iterable class for easy forloop traversal
    Node next;

    public StackIterator(Stack<T> stack) {
      this.next = stack.topLevel;
    }

    public boolean hasNext() {
      // checks if the next node is null
      return (this.next != null);
    }

    public T next() {
      // Gets the next node in the stack
      T temp = this.next.get();
      this.next = this.next.getNext();
      return temp;
    }
  }

  public Iterator<T> iterator() {
    return new StackIterator(this);
  }

  private Node topLevel;
  private int size;

  // Constructors, Self explanitory.
  Stack() {
    this.topLevel = null;
    this.size = 0;
  }

  Stack(T data) {
    this.topLevel = new Node(data);
    this.size = 1;
  }

  Stack(T[] data) {
    for (T item : data) {
      this.push(item);
    }
  }

  class Node {
    // A node class, stores data and structures the stack
    private T data;
    private Node next;

    // Constructors
    Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }

    Node(T data) {
      this.data = data;
      this.next = null;
    }

    // Setters and Getters
    public void setNext(Node node) {
      this.next = node;
    }

    public Node getNext() {
      return this.next;
    }

    public T get() {
      return this.data;
    }

    public void set(T data) {
      this.data = data;
    }

    public String toString() {
      // Formats a nice string for the stack
      if (this.next == null) {
        return this.data.toString();
      }
      return String.format("%s -> %s", this.data.toString(), this.next);
    }

  }

  private Node walkToBottom() {
    // Walks to the first node in the stack.
    Node current = this.topLevel;

    while (current.getNext() != null) {
      current = current.getNext();
    }

    return current;
  }

  public void push(T data) {
    // Pushes a new piece of data to the top of the stack
    Node newNode = new Node(data);

    if (this.isEmpty()) {
      this.topLevel = newNode;
      this.size += 1;
      return;
    }

    newNode.setNext(this.topLevel);
    this.topLevel = newNode;
    this.size += 1;
  }

  public void push(Stack<T> stack) {
    // Pushes another stack to the top of the current stack
    Node bottom = stack.walkToBottom();

    bottom.setNext(this.topLevel);
    this.topLevel = stack.topLevel;
    this.size += stack.size();
  }

  public T pop() {
    // removes the top of the stack and returns the data stored in it.
    T tempData = this.topLevel.data;
    this.topLevel = this.topLevel.getNext();
    return tempData;
  }

  public T peek() {
    // shows the topLevel
    return this.topLevel.get();
  }

  public boolean isEmpty() {
    // checks if the stack is empty
    return this.topLevel == null;
  }

  public int size() {
    // returns the size of the stack
    return this.size;
  }

  public boolean has(T val) {
    // Checks to see if a value is in the stack
    for (T item : this) {
      if (item == val) {
        return true;
      }
    }
    return false;
  }

  public String toString() {
    if (this.isEmpty()) {
      return "Stack Is Empty.";
    }
    return this.topLevel.toString();
  }

}

class Operator {
  char op;

  public Operator() {
    this.op = '\0';
  }

  public Operator(char op) {
    this.op = op;
  }

  public double apply(double b, double a) {
    switch (this.op) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        if (b == 0) {
          throw new UnsupportedOperationException(
              "Cannot Divide By 0.");
        }
        return a / b;
      case '^':
        return Math.pow(a, b);
      default:
        throw new InvalidParameterException(String.format("Invalid Operator '%s'.", this.op));
    }
  }

  public boolean equals(char op) {
    return this.op == op;
  }

  public String toString() {
    return Character.toString(this.op);
  }
}

class Lexer {
  public Stack<Operator> operatorStack = new Stack<Operator>();
  public Stack<Double> valueStack = new Stack<Double>();
  private char[] expr;
  private char currentChar;
  private int pos = 0;

  public Lexer() {
    this.expr = new char[] {};
    this.currentChar = '\0';
  }

  public Lexer(char[] expr) {
    this.expr = expr;
    if (this.expr.length > 0)
      this.currentChar = expr[0];
  }

  public void set(char[] expr) {
    this.expr = expr;
    if (this.expr.length > 0)
      this.currentChar = this.expr[0];
  }

  private void advance() {
    this.pos++;
    if (this.pos < this.expr.length)
      this.currentChar = this.expr[this.pos];
  }

  public void eval() {
    this.pos = 0;

    while (this.pos < this.expr.length) {
      if (this.currentChar == '\n' || this.currentChar == '\r' || this.currentChar == ';' || this.expr.length == 0) {
        break;
      }

      if (this.currentChar == ' ' || this.currentChar == '\0') {

        this.advance();

      } else if (Character.isDigit(this.currentChar)) {

        this.makeNumber();

      } else if (this.currentChar == '(') {

        this.operatorStack.push(new Operator(this.currentChar));
        this.advance();

      } else if (this.currentChar == ')') {
        while (!this.operatorStack.peek().equals('(')) {
          Operator op = this.operatorStack.pop();
          this.valueStack.push(op.apply(this.valueStack.pop(), this.valueStack.pop()));
        }
        this.operatorStack.pop();
        this.advance();

      } else if (this.currentChar == '+' || this.currentChar == '-' || this.currentChar == '*'
          || this.currentChar == '/' || this.currentChar == '^')

      {

        Operator thisOp = new Operator(this.currentChar);
        while (!this.operatorStack.isEmpty()
            && this.hasPrecedence(thisOp, this.operatorStack.peek())) {
          Operator op = this.operatorStack.pop();
          this.valueStack.push(op.apply(this.valueStack.pop(), this.valueStack.pop()));
        }
        this.operatorStack.push(thisOp);
        this.advance();

      } else {

        throw new InvalidParameterException(String.format("Invalid Token '%c'", this.currentChar));

      }

    }

    while (!operatorStack.isEmpty())

    {

      Operator op = this.operatorStack.pop();
      this.valueStack.push(op.apply(this.valueStack.pop(), this.valueStack.pop()));

    }
  }

  private boolean hasPrecedence(Operator op1, Operator op2) {
    if (op2.equals('(') || op2.equals(')'))
      return false;

    if (((op1.equals('*') || op1.equals('/')) &&
        (op2.equals('+') || op2.equals('-'))) ||
        (op1.equals('^') &&
            (op2.equals('+') || op2.equals('-') || op2.equals('*') || op2.equals('/'))))
      return false;

    else
      return true;
  }

  private void makeNumber() {
    StringBuilder numStr = new StringBuilder();
    int decimalCount = 0;

    while (this.pos < this.expr.length &&
        (Character.isDigit(this.currentChar) || this.currentChar == '.')) {
      if (this.currentChar == '.') {
        if (decimalCount == 1) {
          break;
        }
        decimalCount++;
        numStr.append('.');
      } else {
        numStr.append(this.currentChar);
      }
      this.advance();
    }

    this.valueStack.push(Double.parseDouble(numStr.toString()));
  }
}

class ExpressionEvaluator {
  private Lexer lexer = new Lexer();
  private char[] expr;

  public ExpressionEvaluator() {
    this.expr = new char[] {};
  }

  public ExpressionEvaluator(String expr) {
    this.expr = expr.toCharArray();
  }

  public Object evaluate(String expr) {
    if (expr.length() != 0) {
      System.out.printf("Evaluating %s\n", expr);
      lexer.set(expr.toCharArray());
      lexer.eval();

      return lexer.valueStack.pop();
    }
    return "";
  }

  public Object evaluate(char[] expr) {
    if (expr.length != 0) {
      System.out.printf("Evaluating %s\n", expr.toString());
      lexer.set(expr);
      lexer.eval();

      return lexer.valueStack.pop();
    }
    return "";
  }

  public Object evaluate() {
    return this.evaluate(this.expr);
  }
}
