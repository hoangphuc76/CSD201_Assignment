import java.util.Scanner;

class Node {
    String data;
    Node prev;
    Node next;

    public Node(String data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private Node current;
    private Node undoTop;
    private Node redoTop;

    public TextEditor() {
        current = new Node("");
        undoTop = null;
        redoTop = null;
    }

    public void insertText(String text) {
        // Save current state to undo stack
        if (current != null) {
            undoTop = new Node(current.data);
            undoTop.next = current.next;
            if (current.next != null) {
                current.next.prev = undoTop;
            }
        }

        // Insert new text
        Node newNode = new Node(text);
        newNode.prev = current;
        newNode.next = current.next;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        current = newNode;

        // Clear redo stack
        redoTop = null;
    }

    public void undo() {
        if (undoTop != null) {
            // Save current state to redo stack
            redoTop = new Node(current.data);
            redoTop.next = current.next;
            if (current.next != null) {
                current.next.prev = redoTop;
            }

            // Restore previous state
            current = undoTop;
            undoTop = undoTop.prev;
            if (undoTop != null) {
                undoTop.next = current;
            }

            // Clear undo stack
            undoTop = null;
        }
    }

    public void redo() {
        if (redoTop != null) {
            // Save current state to undo stack
            undoTop = new Node(current.data);
            undoTop.next = current.next;
            if (current.next != null) {
                current.next.prev = undoTop;
            }

            // Restore redo state
            current = redoTop;
            redoTop = redoTop.next;
            if (redoTop != null) {
                redoTop.prev = current;
            }

            // Clear redo stack
            redoTop = null;
        }
    }

    public String getDocument() {
        Node temp = current;
        StringBuilder document = new StringBuilder();
        while (temp != null) {
            document.insert(0, temp.data);
            temp = temp.prev;
        }
        return document.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter text ('undo', 'redo', 'exit' also supported): ");
            String userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                break;
            } else if (userInput.equals("undo")) {
                textEditor.undo();
            } else if (userInput.equals("redo")) {
                textEditor.redo();
            } else {
                textEditor.insertText(userInput);
            }

            System.out.println("Document: " + textEditor.getDocument());
        }

        scanner.close();
    }
}
