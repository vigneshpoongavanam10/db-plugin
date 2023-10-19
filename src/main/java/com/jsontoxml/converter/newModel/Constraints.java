package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "constraints")
public class Constraints {
    private String nullable;
    private String primaryKey;

    @XmlAttribute(name = "nullable")
    public String getNullable() {
        return nullable;
    }

    @XmlAttribute(name = "primaryKey")
    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
}
