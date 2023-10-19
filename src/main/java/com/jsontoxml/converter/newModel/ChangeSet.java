package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "changeSet")
@XmlType(propOrder = {"id", "author", "createTable", "insert", "addColumn", "updateItems", "addForeignKeyConstraint"})
public class ChangeSet {
    private List<AddColumnItem> addColumn;
    private List<CreateTableItem> createTable;
    private String author;
    private List<InsertItem> insert;

    private List<UpdateItem> updateItems;

    @XmlElement(name = "update")
    public List<UpdateItem> getUpdateItems() {
        return updateItems;
    }

    public void setUpdateItems(List<UpdateItem> updateItems) {
        this.updateItems = updateItems;
    }

    private String id;
    private List<AddForeignKeyConstraintItem> addForeignKeyConstraint;

    @XmlElement(name = "addColumn")
    public List<AddColumnItem> getAddColumn() {
        return addColumn;
    }

    @XmlElement(name = "createTable")
    public List<CreateTableItem> getCreateTable() {
        return createTable;
    }

    @XmlAttribute(name = "author")
    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "insert")
    public List<InsertItem> getInsert() {
        return insert;
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    @XmlElement(name = "addForeignKeyConstraint")
    public List<AddForeignKeyConstraintItem> getAddForeignKeyConstraint() {
        return addForeignKeyConstraint;
    }

    public void setAddColumn(List<AddColumnItem> addColumn) {
        this.addColumn = addColumn;
    }

    public void setCreateTable(List<CreateTableItem> createTable) {
        this.createTable = createTable;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setInsert(List<InsertItem> insert) {
        this.insert = insert;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddForeignKeyConstraint(List<AddForeignKeyConstraintItem> addForeignKeyConstraint) {
        this.addForeignKeyConstraint = addForeignKeyConstraint;
    }

    @Override
    public String toString() {
        return "ChangeSet{" +
                "addColumn=" + addColumn +
                ", createTable=" + createTable +
                ", author='" + author + '\'' +
                ", insert=" + insert +
                ", updateItems=" + updateItems +
                ", id='" + id + '\'' +
                ", addForeignKeyConstraint=" + addForeignKeyConstraint +
                '}';
    }
}