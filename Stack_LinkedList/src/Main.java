import java.nio.file.StandardOpenOption;
import java.util.EmptyStackException;

class Node {
    public char info;
    public Node next;

    public Node(char x, Node p) {
        info = x;
        next = p;
    }

    public Node(char x) {
        this(x, null);
    }
}

class LinkedStack {
    protected Node head;
    private char pop;

    public LinkedStack() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(char x) {
        head = new Node(x, head);
    }

    char top() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        {
            return (head.info);
        }
    }

    public char pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        {
            char x = head.info;
            head = head.next;
            return x;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String text = "((((((";
        LinkedStack linkedStack = new LinkedStack();

        for (char hehe : text.toCharArray()) {
            if (hehe == '{' || hehe == '[' || hehe == '(') {
                linkedStack.push(hehe);
            } else if(hehe =='}' || hehe == ')' || hehe == ']'){
                if (!linkedStack.isEmpty() && (hehe == '}' && linkedStack.top() == '{' ||
                        hehe == ')' && linkedStack.top() == '(' ||
                        hehe == ']' && linkedStack.top() == '[')) {
                    linkedStack.pop();
                } else {
                    break;
                }
            }
        }
        if (linkedStack.isEmpty()) {
            System.out.println(true);
        } else
            System.out.println(false);
    }
}