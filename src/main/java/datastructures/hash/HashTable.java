package datastructures.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class HashTable {
    private final int count;
    private int size;
    private final List<LinkedList<Employee>> table;

    public HashTable(int count) {
        this.count = count;
        this.table = new ArrayList<>(size);
        IntStream.range(0, count).forEach(index -> table.add(new LinkedList<>()));
    }

    public void add(Employee employee) {
        table.get(getHashCode(employee.getId())).add(employee);
        size ++;
    }

    private int getHashCode(int id) {
        return id % this.count;
    }

    public int size() {
        return this.size;
    }

    public Optional<Employee> findById(int id) {
        final var employees = table.get(getHashCode(id));
        return employees.stream().filter(employee -> employee.getId() == id).findFirst();
    }
}
