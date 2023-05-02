package datastructures.linkedlist;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JosephCircle {
    private int size;
    private BoyNode headBoy;
    private BoyNode tailBoy;

    public JosephCircle(int count) {
        if (count < 1) {
            throw new RuntimeException("Joseph circle size must more than one");
        }
        this.size = count;
        this.initCircle();
    }

    private void initCircle() {
        this.tailBoy = this.headBoy = new BoyNode(1);
        headBoy.setNext(headBoy);
        for (int index = 1; index < size; index++) {
            BoyNode boyNode = new BoyNode(index + 1);
            this.tailBoy.setNext(boyNode);
            this.tailBoy = boyNode;
            this.tailBoy.setNext(this.headBoy);
        }
    }

    public int size() {
        return this.size;
    }

    public List<BoyNode> getBoys() {
        final List<BoyNode> leftBoys =
                Stream.iterate(this.headBoy, node -> node.getNext() != this.headBoy, BoyNode::getNext)
                        .collect(Collectors.toList());
        leftBoys.add(this.tailBoy);
        return leftBoys;
    }

    public BoyNode lastBoy(int step) {
        if (step < 2) {
            throw new RuntimeException("The step can not less than 2");
        }

        while (!this.headBoy.isLastBoy()) {
            for (int i = 0; i < step - 1; i++) {
                this.tailBoy = this.headBoy;
                this.headBoy = this.headBoy.getNext();
            }
            this.tailBoy.setNext(this.headBoy.getNext());
            this.headBoy = this.headBoy.getNext();
            this.size--;
        }
        return this.headBoy;
    }
}
