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
    private Node tail;
    private Node head;

    public TextEditor() {
        this.tail = null;
        this.head = null;
    }

    public void insert(String text) {
        Node newNode = new Node(text);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.printf("Current Text: %s\n", traverse());

    }

    public void undo() {
        if (tail != null && tail.prev != null) {
            tail = tail.prev;
        }
        System.out.printf("After Undo: %s\n", traverse());
    }

    public void redo() {
        if (tail != null && tail.next != null) {
            tail = tail.next;
        }
        System.out.printf("After Redo: %s\n", traverse());

    }

    public String traverse() {
        Node temp = head;
        StringBuilder str = new StringBuilder();
        while (temp != null) {
            str.append(temp.data);
            if (temp == tail) {
                break;
            }
            temp = temp.next;
        }
        return str.toString();
    }
}
