package com.example;

import java.util.Date;

public class PayrollService {

    public static void main(String[] args) {
        PayrollService ps = new PayrollService();
        ps.doPayrollWithoutBuilder();
        ps.doPayrollWithBuilder();
        ps.doPayrollWithBadData();
        ps.doPayrollWithMissingData();
    }

    private static void print(String title, Employee employee) {
        System.out.println("\n=== " + title + " ===");
        System.out.println(employee);
    }

    public void doPayrollWithoutBuilder() {
        Employee e = new Employee.Impl("1", "Fred Foobar", "Engineer", 100000, new Date());
        print("doPayrollWithoutBuilder", e);
    }

    public void doPayrollWithBuilder() {
        Employee e = new Employee.Builder()
                .name("Fred Foobar")
                .title("Engineer")
                .salary(100000)
                .build();
        print("doPayrollWithBuilder", e);
    }

    public void doPayrollWithBadData() {
        Employee e = new Employee.Builder()
                .id("123")
                .name("Fred Foobar")
                .title("Engineer")
                .salary(-1)
                .build();
        print("doPayrollWithBadData", e);
    }

    public void doPayrollWithMissingData() {
        Employee e = new Employee.Builder()
                .name("Fred Foobar")
                // oops, no title!
                .salary(100000)
                .hiredAt(new Date())
                .build();
        print("doPayrollWithMissingData", e);
    }

}
