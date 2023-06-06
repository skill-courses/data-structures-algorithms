package datastructures.hash;

import java.util.Objects;

public class Employee {
    private final int id;
    private final String name;
    private final int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
