package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addForeignKeyConstraint")
@XmlType(propOrder = {"baseTableName", "baseColumnNames", "constraintName", "referencedTableName", "referencedColumnNames"})
public class AddForeignKeyConstraintItem {
    private String referencedTableName;
    private String referencedColumnNames;
    private String baseColumnNames;
    private String constraintName;
    private String baseTableName;

    @XmlAttribute(name = "referencedTableName")
    public String getReferencedTableName() {
        return referencedTableName;
    }

    @XmlAttribute(name = "referencedColumnNames")
    public String getReferencedColumnNames() {
        return referencedColumnNames;
    }

    @XmlAttribute(name = "baseColumnNames")
    public String getBaseColumnNames() {
        return baseColumnNames;
    }

    @XmlAttribute(name = "constraintName")
    public String getConstraintName() {
        return constraintName;
    }

    @XmlAttribute(name = "baseTableName")
    public String getBaseTableName() {
        return baseTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public void setReferencedColumnNames(String referencedColumnNames) {
        this.referencedColumnNames = referencedColumnNames;
    }

    public void setBaseColumnNames(String baseColumnNames) {
        this.baseColumnNames = baseColumnNames;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public void setBaseTableName(String baseTableName) {
        this.baseTableName = baseTableName;
    }
}
