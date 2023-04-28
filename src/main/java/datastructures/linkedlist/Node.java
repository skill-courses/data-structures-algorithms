package datastructures.linkedlist;

import java.util.Objects;
import java.util.Optional;

public class Node {
    private final int no;
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

    public Node copy() {
        return new Node(this.getNo(), this.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return no == node.no && name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, name);
    }
}
