package com.jsontoxml.converter.model;

public class ForeignKeyConstraintItem {

    private String baseColumnNames;
    private String referencedTableName;
    private String referencedColumnNames;
    private String baseTableName;

    private String constraintName;

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public String getBaseColumnNames() {
        return baseColumnNames;
    }

    public void setBaseColumnNames(String baseColumnNames) {
        this.baseColumnNames = baseColumnNames;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public String getReferencedColumnNames() {
        return referencedColumnNames;
    }

    public void setReferencedColumnNames(String referencedColumnNames) {
        this.referencedColumnNames = referencedColumnNames;
    }

    public String getBaseTableName() {
        return baseTableName;
    }

    public void setBaseTableName(String baseTableName) {
        this.baseTableName = baseTableName;
    }

    @Override
    public String toString() {
        return "ForeignKeyConstraintItem{" +
                "baseColumnNames='" + baseColumnNames + '\'' +
                ", referencedTableName='" + referencedTableName + '\'' +
                ", referencedColumnNames='" + referencedColumnNames + '\'' +
                ", baseTableName='" + baseTableName + '\'' +
                '}';
    }
}

