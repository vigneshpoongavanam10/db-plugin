package com.jsontoxml.converter.model;


public class ColumnValuesItem {
    private boolean nn;
    private String dataType;
    private String column;
    private boolean pk;
    private boolean uq;
    private String value;

    public boolean isNn() {
        return nn;
    }

    public String getDataType() {
        return dataType;
    }

    public String getColumn() {
        return column;
    }

    public boolean isPk() {
        return pk;
    }

    public boolean isUq() {
        return uq;
    }

    public String getValue() {
        return value;
    }
}
