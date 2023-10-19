package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "databaseChangeLog")
@XmlType(propOrder = {"xmlns", "xmlnsxsi", "schemaLocation", "logicalFilePath", "changeSet"})
public class ResponseAsList {

    private String xmlnsxsi;
    private String xmlns;
    private String schemaLocation;
    private String logicalFilePath;
    private List<ChangeSet> changeSet;

    @XmlElement(name = "changeSet")
    public List<ChangeSet> getChangeSet() {
        return changeSet;
    }

    public void setChangeSet(List<ChangeSet> changeSet) {
        this.changeSet = changeSet;
    }

    @XmlAttribute(name = "xmlns:xsi")
    public String getXmlnsxsi() {
        return xmlnsxsi;
    }

    public void setXmlnsxsi(String xmlnsxsi) {
        this.xmlnsxsi = xmlnsxsi;
    }

    @XmlAttribute(name = "xmlns")
    public String getXmlns() {
        return xmlns;
    }

    @XmlAttribute(name = "xsi:schemaLocation")
    public String getSchemaLocation() {
        return schemaLocation;
    }

    @XmlAttribute(name = "logicalFilePath")
    public String getLogicalFilePath() {
        return logicalFilePath;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    public void setLogicalFilePath(String logicalFilePath) {
        this.logicalFilePath = logicalFilePath;
    }


    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    @Override
    public String toString() {
        return "ResponseAsList{" +
                "xmlnsxsi='" + xmlnsxsi + '\'' +
                ", xmlns='" + xmlns + '\'' +
                ", schemaLocation='" + schemaLocation + '\'' +
                ", logicalFilePath='" + logicalFilePath + '\'' +
                ", changeSet=" + changeSet +
                '}';
    }
}
