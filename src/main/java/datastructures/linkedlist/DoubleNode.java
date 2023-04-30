package datastructures.linkedlist;

import java.util.Optional;

public class DoubleNode {
    private final int no;
    private String name;
    private Optional<DoubleNode> next;
    private Optional<DoubleNode> pre;

    public DoubleNode(int no, String name) {
        this.no = no;
        this.name = name;
        this.next = Optional.empty();
        this.pre = Optional.empty();
    }

    public boolean isHead() {
        return next.isEmpty() && pre.isEmpty();
    }

    public boolean isTail() {
        return next.isEmpty() && pre.isPresent();
    }

    public boolean isMiddle() {
        return !isHead() && !isTail();
    }

    public boolean isSingle() {
        return pre.isEmpty() && next.isEmpty();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public Optional<DoubleNode> getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = Optional.ofNullable(next);
    }

    public Optional<DoubleNode> getPre() {
        return pre;
    }

    public void setPre(DoubleNode pre) {
        this.pre = Optional.ofNullable(pre);
    }
}
