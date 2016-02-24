package com.bandi.rest.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "message")
public class Message {
	String name;
	String text;
	String dontSend = "Hope not received";
	String iAmEmpty = "";
	String iAmNull = null;
	String anotherText;
	
	public Message() {}

	public Message(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	@XmlAttribute
	public String getName() {
		return name;
	}
	
	@XmlElement
	public String getText() {
		return text;
	}
	
}
