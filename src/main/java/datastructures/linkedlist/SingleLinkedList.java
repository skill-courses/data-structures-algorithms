package datastructures.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class SingleLinkedList {

    private Optional<Node> head;
    private Optional<Node> tail;
    private int size;

    public SingleLinkedList() {
        this.head = Optional.empty();
        this.tail = Optional.empty();
    }

    public void add(Node node) {
        tail.ifPresentOrElse(tailNode -> tailNode.setNext(Optional.ofNullable(node)),
                () -> this.head = Optional.ofNullable(node));
        this.tail = Optional.ofNullable(node);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public List<Node> toList() {
        ArrayList<Node> nodes = new ArrayList<>();
        Optional<Node> temp = this.head;
        while (temp.isPresent()) {
            nodes.add(temp.get().copy());
            temp = temp.get().getNext();
        }
        return nodes;
    }

    private ArrayDeque<Node> toStack() {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Optional<Node> temp = this.head;
        while (temp.isPresent()) {
            stack.push(temp.get().copy());
            temp = temp.get().getNext();
        }
        return stack;
    }

    public void addByNoOrder(Node node) {
        final Optional<Node> nodeOptional = Optional.ofNullable(node);
        final Predicate<Node> findMoreThanNode =
                temp -> temp.getNext().isPresent() && temp.getNext().get().getNo() > node.getNo();
        this.findByPredicate(findMoreThanNode).ifPresentOrElse((Node tempNode) -> {
                    node.setNext(tempNode.getNext());
                    tempNode.setNext(nodeOptional);
                },
                () -> this.tail.ifPresentOrElse((Node tailNode) -> {
                            tailNode.setNext(nodeOptional);
                            this.tail = nodeOptional;
                        },
                        () -> this.tail = this.head = nodeOptional
                )
        );
        size++;
    }

    public Optional<Node> findByNo(int no) {
        final Predicate<Node> findPreNode = node -> node.getNo() == no;
        return findByPredicate(findPreNode);
    }

    public void updateNode(Node newNode) {
        this.findByNo(newNode.getNo()).ifPresent(node -> node.setName(newNode.getName()));
    }

    public void delete(int no) {
        this.head.ifPresent((Node headNode) -> {
            if (headNode.getNo() == no) {
                this.head = headNode.getNext();
                size--;
            }
        });

        this.findPreNodeByNo(no).ifPresent((Node node) -> {
            node.getNext().ifPresentOrElse(temp -> node.setNext(temp.getNext()),
                    () -> {
                        node.setNext(Optional.empty());
                        this.tail = Optional.of(node);
                    });
            size--;
        });
    }

    private Optional<Node> findPreNodeByNo(int no) {
        final Predicate<Node> findPreNode = node -> node.getNext().isPresent() && node.getNext().get().getNo() == no;
        return findByPredicate(findPreNode);
    }

    private Optional<Node> findByPredicate(Predicate<Node> predicate) {
        if (size == 0) {
            return Optional.empty();
        }

        Optional<Node> temp = this.head;
        while (temp.isPresent()) {
            if (predicate.test(temp.get())) {
                return temp;
            }
            temp = temp.get().getNext();
        }
        return Optional.empty();
    }

    public Optional<Node> findByIndexFromTail(int index) {
        if (index > this.size) {
            return Optional.empty();
        }

        final Node node = this.toList().get(size - index);
        return Optional.of(node);
    }

    public SingleLinkedList reverse() {
        SingleLinkedList reversedSingleLinkedList = new SingleLinkedList();
        this.toStack().forEach(reversedSingleLinkedList::add);
        return reversedSingleLinkedList;
    }

    public SingleLinkedList mergeByOrderNo(SingleLinkedList singleLinkedList) {
        singleLinkedList.toList().forEach(this::addByNoOrder);
        return this;
    }
}
