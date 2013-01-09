package com.example.test;

import com.example.CSVEmployees;
import com.example.Employees;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Iterables.size;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class EmployeesTests {

    private Employees _employees;

    @Before
    public void setUp() throws Exception {
        _employees = new CSVEmployees(getClass().getResource("employees.csv"));
    }

    @Test
    public void testThatAllEmployeesAreLoaded() {
        assertThat(size(_employees.all()), is(4));
        assertThat(_employees.findbyId("1"), is(notNullValue()));
        assertThat(_employees.findbyId("9"), is(nullValue()));
    }

}
