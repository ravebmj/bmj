package org.bmj.userinsights.dto;

import java.io.Serializable;

public class TagEditorJson implements Serializable{
	private String id;
	private String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}