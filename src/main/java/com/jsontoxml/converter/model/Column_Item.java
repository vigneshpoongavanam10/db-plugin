package com.jsontoxml.converter.model;

public class Column_Item {
    private String name;
    private String type;
    private Constraint constraint;
    private String valueNumeric;
    private String value;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Constraint getConstraints() {
        return constraint;
    }

    public String getValueNumeric() {
        return valueNumeric;
    }

    public String getValue() {
        return value;
    }
}
