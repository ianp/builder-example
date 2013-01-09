package com.example;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.nullToEmpty;

public interface Employee {

    public Date getHiredAt();
    public String getId();
    public String getName();
    public int getSalary();
    public String getTitle();

    public static class Impl implements Employee {

        private final String _id;
        private final String _name;
        private final String _title;
        private final int _salary;
        private final Date _hiredAt;

        protected Impl(String id, String name, String title, int salary, Date hiredAt) {
            _id = id;
            _name = name;
            _title = title;
            _salary = salary;
            _hiredAt = hiredAt;
        }

        public Date getHiredAt() {
            return _hiredAt;
        }

        public String getId() {
            return _id;
        }

        public String getName() {
            return _name;
        }

        public int getSalary() {
            return _salary;
        }

        public String getTitle() {
            return _title;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).omitNullValues()
                    .add("id", _id)
                    .add("name", _name)
                    .add("title", _title)
                    .add("salary", _salary)
                    .add("hiredAt", _hiredAt)
                    .toString();
        }

    }

    public static class Builder {

        private static final AtomicInteger _ids = new AtomicInteger();

        private static String checkString(String value, String name) {
            value = nullToEmpty(value).trim();
            checkArgument(!value.isEmpty(), "%s cannot be null or empty", name);
            return value;
        }

        private String _id;
        private String _name;
        private String _title;
        private Integer _salary;
        private Date _hiredAt;

        public final Builder hiredAt(Date hiredAt) {
            _hiredAt = hiredAt;
            return this;
        }

        public final Builder id(String id) {
            _id = id;
            return this;
        }

        public final Builder name(String name) {
            _name = name;
            return this;
        }

        public final Builder salary(int salary) {
            checkArgument(salary >= 0, "salary cannot be negative");
            _salary = salary;
            return this;
        }

        public final Builder title(String title) {
            _title = title;
            return this;
        }

        public Employee build() {
            return new Impl(
                    _id != null ? _id : String.format("emp:%06d", _ids.incrementAndGet()),
                    checkString(_name, "name"),
                    checkString(_title, "title"),
                    checkNotNull(_salary, "salary"),
                    _hiredAt != null ? _hiredAt : new Date());
        }

    }

}
