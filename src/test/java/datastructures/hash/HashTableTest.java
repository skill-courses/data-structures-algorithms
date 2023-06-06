package datastructures.hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTableTest {

    @Test
    void should_add_employee_to_hash_table() {
        HashTable hashTable = new HashTable(10);

        Employee employee1 = new Employee(1, "tom", 22);
        Employee employee2 = new Employee(2, "jok", 20);
        hashTable.add(employee1);
        hashTable.add(employee2);

        assertEquals(2, hashTable.size());
    }

    @Test
    void should_find_employee_in_hash_table() {
        HashTable hashTable = new HashTable(10);

        Employee employee1 = new Employee(1, "tom", 22);
        Employee employee2 = new Employee(2, "jok", 20);
        Employee employee3 = new Employee(4, "zhangsan", 20);
        Employee employee4 = new Employee(20, "lisi", 20);
        Employee employee5 = new Employee(567, "wangwu", 20);
        hashTable.add(employee1);
        hashTable.add(employee2);
        hashTable.add(employee3);
        hashTable.add(employee4);
        hashTable.add(employee5);

        assertEquals(5, hashTable.size());

        final var employeeOptional = hashTable.findById(employee5.getId());
        assertTrue(employeeOptional.isPresent());
        assertEquals(employee5, employeeOptional.get());

        assertFalse(hashTable.findById(789).isPresent());
    }

}
