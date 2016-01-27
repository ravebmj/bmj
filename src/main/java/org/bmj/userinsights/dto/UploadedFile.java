package org.bmj.userinsights.dto;



public class UploadedFile {

  private Integer id;
  private String name;
  private String location;
  private Long size;
  private String type;
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the location
 */
public String getLocation() {
	return location;
}
/**
 * @param location the location to set
 */
public void setLocation(String location) {
	this.location = location;
}
/**
 * @return the size
 */
public Long getSize() {
	return size;
}
/**
 * @param size the size to set
 */
public void setSize(Long size) {
	this.size = size;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}




}
