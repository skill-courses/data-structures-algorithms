package datastructures.linkedlist;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DoubleLinkedListTest {

    @Test
    void should_create_null_double_linked_list() {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        int size  = doubleLinkedList.size();

        assertEquals(0, size);
    }

    @Test
    void should_add_elements_to_double_linked_List() {
        final DoubleNode node1 = new DoubleNode(1, "宋江");
        final DoubleNode node2 = new DoubleNode(2, "卢俊义");
        final DoubleNode node3 = new DoubleNode(3, "林冲");

        final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);

        assertEquals(3, doubleLinkedList.size());
        List<DoubleNode> nodes = doubleLinkedList.toList();
        assertEquals(List.of(node1, node2, node3), nodes);
    }

    @Test
    void should_add_elements_by_order() {
        final DoubleNode node1 = new DoubleNode(1, "宋江");
        final DoubleNode node2 = new DoubleNode(2, "卢俊义");
        final DoubleNode node3 = new DoubleNode(3, "林冲");
        final DoubleNode node4 = new DoubleNode(4, "武松");

        final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrderNo(node2);
        doubleLinkedList.addByOrderNo(node1);
        doubleLinkedList.addByOrderNo(node4);
        doubleLinkedList.addByOrderNo(node3);

        assertEquals(4, doubleLinkedList.size());
        List<DoubleNode> nodes = doubleLinkedList.toList();
        assertEquals(List.of(node1, node2, node3, node4), nodes);
    }

    @Test
    void should_find_element() {
        final DoubleNode node1 = new DoubleNode(1, "宋江");
        final DoubleNode node2 = new DoubleNode(2, "卢俊义");
        final DoubleNode node3 = new DoubleNode(3, "林冲");
        final DoubleNode node4 = new DoubleNode(4, "武松");

        final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        final Optional<DoubleNode> nodeOptional = doubleLinkedList.findByPredicate(node -> node.getNo() > 0);
        assertTrue(nodeOptional.isPresent());
        assertEquals(1, nodeOptional.get().getNo());

        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);

        final Optional<DoubleNode> byPredicate = doubleLinkedList.findByPredicate(node -> node.getNo() > 2);
        assertTrue(byPredicate.isPresent());
        assertEquals(3, byPredicate.get().getNo());

        final Optional<DoubleNode> notNode = doubleLinkedList.findByPredicate(node -> node.getNo() > 4);
        assertFalse(notNode.isPresent());
    }

    @Test
    void should_update_element() {
        final DoubleNode node1 = new DoubleNode(1, "宋江");
        final DoubleNode node2 = new DoubleNode(2, "卢俊义");
        final DoubleNode node3 = new DoubleNode(3, "林冲");

        final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);

        final DoubleNode newNode = new DoubleNode(3, "武松");
        doubleLinkedList.update(newNode);

        final Optional<DoubleNode> optionalNode = doubleLinkedList.findByPredicate(node -> node.getNo() == 3);
        assertTrue(optionalNode.isPresent());
        assertEquals(newNode.getName(), optionalNode.get().getName());
    }

    @Test
    void should_delete_node() {
        final DoubleNode node1 = new DoubleNode(1, "宋江");
        final DoubleNode node2 = new DoubleNode(2, "卢俊义");
        final DoubleNode node3 = new DoubleNode(3, "林冲");

        final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);

        doubleLinkedList.delete(node2);
        assertEquals(2, doubleLinkedList.size());
        assertEquals(List.of(node1, node3), doubleLinkedList.toList());

        doubleLinkedList.delete(node3);
        assertEquals(1, doubleLinkedList.size());
        assertEquals(List.of(node1), doubleLinkedList.toList());

        doubleLinkedList.delete(node1);
        assertEquals(0, doubleLinkedList.size());
    }
}
