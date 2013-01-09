package com.example;

import com.google.common.io.CharStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class CSVEmployees implements Employees {

    private final Map<String, Employee> _data = new LinkedHashMap<>();

    public CSVEmployees(URL resourceURL) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try (InputStream in = resourceURL.openStream(); Reader reader = new InputStreamReader(in)) {
            for (String line : CharStreams.readLines(reader)) {
                try {
                    String[] fields = line.split(",");
                    _data.put(fields[0], new Employee.Builder()
                            .id(fields[0])
                            .name(fields[1])
                            .title(fields[2])
                            .salary(Integer.parseInt(fields[3]))
                            .hiredAt(df.parse(fields[4]))
                            .build());
                } catch (ParseException e) {
                    System.err.println("error parsing employee data: " + line);
                }
            }
        }
    }

    @Override
    public Iterable<Employee> all() {
        return _data.values();
    }

    @Override
    public Employee findbyId(String id) {
        return _data.get(id);
    }

}
