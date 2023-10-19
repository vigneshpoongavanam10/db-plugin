package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "update")
public class UpdateItem {
    private String tableName;
    private List<ColumnItem> column;

    @XmlAttribute(name = "tableName")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @XmlElement(name = "column")
    public List<ColumnItem> getColumn() {
        return column;
    }

    public void setColumn(List<ColumnItem> column) {
        this.column = column;
    }
}
