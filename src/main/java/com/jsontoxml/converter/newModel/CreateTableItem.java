package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "createTable")
public class CreateTableItem {

    private List<ColumnItem> column;
    private String tableName;

    @XmlElement(name = "column")
    public List<ColumnItem> getColumn() {
        return column;
    }

    @XmlAttribute(name = "tableName")
    public String getTableName() {
        return tableName;
    }

    public void setColumn(List<ColumnItem> column) {
        this.column = column;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}