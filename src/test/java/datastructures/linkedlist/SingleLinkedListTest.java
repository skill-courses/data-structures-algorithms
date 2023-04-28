package datastructures.linkedlist;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingleLinkedListTest {

    @Test
    void should_init_single_liked_list() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        assertEquals(0, singleLinkedList.size());
    }

    @Test
    void should_add_node_to_single_linked_list() {
        Node node = new Node(1, "宋江");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node);

        assertEquals(1, singleLinkedList.size());
        assertEquals(Arrays.asList(node), singleLinkedList.toList());
    }

    @Test
    void should_add_nodes_to_single_linked_list() {
        Node node = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);

        assertEquals(3, singleLinkedList.size());
        final List<Node> expected = Arrays.asList(node, node2, node3);
        assertEquals(expected, singleLinkedList.toList());
    }


    @Test
    void should_add_by_order_no() {
        Node node = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "武松");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByNoOrder(node);
        singleLinkedList.addByNoOrder(node4);
        singleLinkedList.addByNoOrder(node3);
        singleLinkedList.addByNoOrder(node5);
        singleLinkedList.addByNoOrder(node2);

        assertEquals(5, singleLinkedList.size());
        final List<Node> expected = Arrays.asList(node, node2, node3, node4, node5);
        assertEquals(expected, singleLinkedList.toList());
    }

    @Test
    void should_find_node_by_no() {
        Node node = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);

        assertEquals(3, singleLinkedList.size());
        Optional<Node> nodeOptional = singleLinkedList.findByNo(2);
        assertTrue(nodeOptional.isPresent());
        assertEquals(node2.getName(), nodeOptional.get().getName());
    }

    @Test
    void should_not_find_node_by_not_existed_no() {
        Node node = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);

        assertEquals(3, singleLinkedList.size());
        Optional<Node> nodeOptional = singleLinkedList.findByNo(4);
        assertFalse(nodeOptional.isPresent());
    }

    @Test
    void should_not_find_node_when_linked_list_is_empty() {
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        assertEquals(0, singleLinkedList.size());
        Optional<Node> nodeOptional = singleLinkedList.findByNo(4);
        assertFalse(nodeOptional.isPresent());
    }

    @Test
    void should_update_node() {
        Node node = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);

        Node newNode = new Node(2, "晁盖");
        singleLinkedList.updateNode(newNode);

        final Optional<Node> byNo = singleLinkedList.findByNo(newNode.getNo());
        assertTrue(byNo.isPresent());
        assertEquals(node2.getName(), byNo.get().getName());

        Node newNode1 = new Node(4, "鲁智深");
        singleLinkedList.updateNode(newNode1);

        final Optional<Node> byNo1 = singleLinkedList.findByNo(newNode1.getNo());
        assertFalse(byNo1.isPresent());
    }

    @Test
    void should_delete_node_by_no() {
        Node node = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(node);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);

        singleLinkedList.delete(node.getNo());
        final Optional<Node> byNo = singleLinkedList.findByNo(node.getNo());
        assertFalse(byNo.isPresent());
        assertEquals(2, singleLinkedList.size());
        assertEquals(Arrays.asList(node2, node3), singleLinkedList.toList());

        singleLinkedList.delete(node3.getNo());
        final Optional<Node> byNo1 = singleLinkedList.findByNo(node3.getNo());
        assertFalse(byNo1.isPresent());
        assertEquals(1, singleLinkedList.size());
        assertEquals(Arrays.asList(node2), singleLinkedList.toList());

        singleLinkedList.delete(node2.getNo());
        assertEquals(0, singleLinkedList.size());
    }

}
