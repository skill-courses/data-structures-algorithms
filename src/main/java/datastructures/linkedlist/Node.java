package datastructures.linkedlist;

import java.util.Optional;

public class Node {
    private int no;
    private String name;
    private Optional<Node> next = Optional.empty();

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public Optional<Node> getNext() {
        return next;
    }

    public void setNext(Optional<Node> next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public int getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
    }
}
