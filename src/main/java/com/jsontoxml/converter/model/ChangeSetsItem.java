package com.jsontoxml.converter.model;


import java.util.List;


public class ChangeSetsItem {

    private int sno;
    private String coreTableName;
    private String action;
    private List<InsertValuesItem> insertValues;
    private List<ForeignKeyConstraintItem> forgeinKeyConstraint;
    private List<ColumnValuesItem> columnValues;

    private List<AddValues> addValues;

    public void setAddValues(List<AddValues> addValues) {
        this.addValues = addValues;
    }

    public List<UpdateValues> getUpdateValues() {
        return updateValues;
    }

    public void setUpdateValues(List<UpdateValues> updateValues) {
        this.updateValues = updateValues;
    }

    private List<UpdateValues> updateValues;

    public List<AddValues> getAddValues() {
        return addValues;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getCoreTableName() {
        return coreTableName;
    }

    public void setCoreTableName(String coreTableName) {
        this.coreTableName = coreTableName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<InsertValuesItem> getInsertValues() {
        return insertValues;
    }

    public void setInsertValues(List<InsertValuesItem> insertValues) {
        this.insertValues = insertValues;
    }

    public List<ForeignKeyConstraintItem> getForgeinKeyConstraint() {
        return forgeinKeyConstraint;
    }

    public void setForgeinKeyConstraint(List<ForeignKeyConstraintItem> forgeinKeyConstraint) {
        this.forgeinKeyConstraint = forgeinKeyConstraint;
    }

    public List<ColumnValuesItem> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValuesItem> columnValues) {
        this.columnValues = columnValues;
    }
}