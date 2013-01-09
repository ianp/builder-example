package com.example.test;

import com.example.Employee;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class EmployeeTests {

    private static final String NAME = "Fred Foobar";
    private static final String TITLE = "Engineer";
    private static final int SALARY = 100000;

    @Test
    public void testThatNullIdsAreAllowed() {
        Employee e = new Employee.Builder()
                .name(NAME)
                .title(TITLE)
                .salary(SALARY)
                .hiredAt(new Date())
                .build();
        assertThat(e.getId(), is(notNullValue()));
    }

    @Test
    public void testThatNullHiredAtsAreAllowed() {
        Employee e = new Employee.Builder().id("1").name(NAME).title(TITLE).salary(SALARY).build();
        assertThat(e.getHiredAt(), is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatNegativeSalariesAreRejected() {
        new Employee.Builder().name(NAME).title(TITLE).salary(-50).build();
    }

    @Test
    public void testThatEmployeeIsBuiltCorrectly() {
        Employee e = new Employee.Builder().name(NAME).title(TITLE).salary(SALARY).build();
        assertThat(e.getName(), is(NAME));
        assertThat(e.getTitle(), is(TITLE));
        assertThat(e.getSalary(), is(SALARY));
    }

}
