package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
public class ColumnItem {
    private String name;
    private String type;
    private Constraints constraints;
    private String valueNumeric;
    private String value;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "constraints")
    public Constraints getConstraints() {
        return constraints;
    }

    @XmlAttribute(name = "valueNumeric")
    public String getValueNumeric() {
        return valueNumeric;
    }

    @XmlAttribute(name = "value")
    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public void setValueNumeric(String valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
