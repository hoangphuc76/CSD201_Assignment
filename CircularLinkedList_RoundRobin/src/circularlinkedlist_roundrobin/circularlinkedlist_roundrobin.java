package circularlinkedlist_roundrobin;

import java.util.Scanner;

class Node {
    public char name;
    public int bt;
    public int rt;
    public Node next;
}

class RoundRobin {
    public Node head = null;
    public int j = 65;

    public void insert(int n) {
        Node nn = new Node();
        nn.name = (char) j++;
        nn.bt = n;
        nn.rt = nn.bt;

        if (head == null) {
            head = nn;
            head.next = head;
        } else {
            Node temp = head;
            while (temp.next != head)
                temp = temp.next;
            nn.next = temp.next;
            temp.next = nn;
        }
    }

    public void quantum(int t) {
        Node temp = head;
        int c = 0;
        
        System.out.println("Turnaround Time of each processes: ");
        while (head != null) {
            temp.rt = temp.rt - t;
            c = c + t;
            
            if (temp.rt <= 0) {
                c = c + temp.rt;
                System.out.println(temp.name + " " + c);
                del(temp.name);

                if (temp.next == temp) {
                    break;
                }
            }

            temp = temp.next;
        }
    }

    public void del(char x) {
        Node p = null;
        Node temp = head;

        if (head.name == x) {
            while (temp.next != head)
                temp = temp.next;
            p = head;
            temp.next = head.next;
            head = head.next;
            p = null;
        } else {        
            while (temp.name != x) {
                p = temp;
                temp = temp.next;
            }
            p.next = temp.next;
            temp = null;
        }
    }
}

public class circularlinkedlist_roundrobin {
    public static void main(String[] args) {
        RoundRobin robin = new RoundRobin();
        Scanner scanner = new Scanner(System.in);

        int y, t, n;
        System.out.print("Enter number of process: ");
        y = scanner.nextInt();
        System.out.print("Enter the quantum: ");
        t = scanner.nextInt();
        System.out.print("Enter the burst time of each processes: ");
        for (int i = 0; i < y; i++) {
            n = scanner.nextInt();
            robin.insert(n);
        }

        robin.quantum(t);

    }
}
