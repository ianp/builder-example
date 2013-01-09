package com.example;

public interface Employees {

    Iterable<Employee> all();
    Employee findbyId(String id);

}
