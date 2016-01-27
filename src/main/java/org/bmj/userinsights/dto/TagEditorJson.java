package org.bmj.userinsights.dto;

import java.io.Serializable;
/**
 * This DTO class use for autocomplete data for product,project,tag and insight title.
 * @author nilesh.kambli
 *
 */
public class TagEditorJson implements Serializable{
	
	private static final long serialVersionUID = -3171841883218159208L;
	private String id;
	private String text;
	
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	@Override 
	public boolean equals(Object other){ 
		if(this == other) 
			return true; 
		if(other == null || (this.getClass() != other.getClass())){ 
			return false; 
			} 
		TagEditorJson tagEditorJson = (TagEditorJson) other; 
		return (this.id == tagEditorJson.id) && (this.text != null && text.equals(tagEditorJson.text)); 
		} 
	@Override 
	public int hashCode(){ 
		int result = 0; 
		result = 31*result + (Integer.valueOf(id) !=null ? Integer.valueOf(id).hashCode() : 0);
		result = 31*result + (text !=null ? text.hashCode() : 0); 		
		return result; 
		}
	
	public int compareTo(TagEditorJson o) 
	{
		return Integer.valueOf(this.id) - Integer.valueOf(o.id); 
	} 



}
