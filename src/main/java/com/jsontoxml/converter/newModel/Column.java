package com.jsontoxml.converter.newModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
public class Column{
	private String name;
	private String type;
@XmlAttribute(name = "name")
	public String getName(){
		return name;
	}
@XmlAttribute(name = "type")
	public String getType(){
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
}
