package datastructures.linkedlist;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoubleLinkedList {

    private int size;
    private Optional<DoubleNode> head;
    private Optional<DoubleNode> tail;

    public DoubleLinkedList() {
        this.tail = this.head = Optional.empty();
    }

    public int size() {
        return size;
    }

    public void add(DoubleNode node) {
        final Optional<DoubleNode> nodeOptional = Optional.of(node);
        this.tail.ifPresentOrElse((DoubleNode tailNode) -> {
            tailNode.setNext(node);
            node.setPre(tailNode);
        }, () -> this.head = nodeOptional);
        this.tail = nodeOptional;
        size++;
    }

    public void addByOrderNo(DoubleNode node) {
        this.findByPredicate(item -> item.getNo() > node.getNo())
                .ifPresentOrElse((DoubleNode it) -> {
                    if (it.isHead()) {
                        this.head = Optional.of(node);
                    } else {
                        it.getPre().get().setNext(node);
                        node.setPre(it.getPre().get());
                    }
                    it.setPre(node);
                    node.setNext(it);
                    size++;
                }, () -> this.add(node));
    }

    public Optional<DoubleNode> findByPredicate(Predicate<DoubleNode> predicate) {
        return Stream.iterate(this.head, Optional::isPresent, node -> node.get().getNext())
                .map(Optional::get)
                .filter(predicate)
                .findFirst();
    }

    public List<DoubleNode> toList() {
        return Stream.iterate(this.head, Optional::isPresent, node -> node.get().getNext())
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void update(DoubleNode node) {
        this.findByPredicate(item -> item.getNo() == node.getNo())
                .ifPresent(it -> it.setName(node.getName()));
    }

    public void delete(DoubleNode node) {
        this.findByPredicate(item -> item.getNo() == node.getNo())
                .ifPresent((DoubleNode it) -> {
                    size--;
                    if (it.isSingle()) {
                        this.head = Optional.empty();
                        this.tail = Optional.empty();
                        return;
                    }

                    if (it.isHead()) {
                        it.getNext().get().setPre(null);
                        this.head = it.getNext();
                        return;
                    }

                    if (it.isTail()) {
                        it.getPre().get().setNext(null);
                        this.tail = it.getPre();
                        return;
                    }

                    if (it.isMiddle()) {
                        it.getPre().get().setNext(it.getNext().get());
                        it.getNext().get().setPre(it.getPre().get());
                    }
                });
    }
}
