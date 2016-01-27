package org.bmj.userinsights.awsbucket.dto;

import java.io.InputStream;

/**
 * This class represent File object from AWS bucket.
 * It has attachmenttype and file content as array of byte.
 * @author nilesh.kambli
 *
 */
public class AWSFileDetail {
	
	byte[] dataAsByteArray = null;
	String attachementType="";// file attachment type.
	InputStream dataAsStream;// File object in term of array of bytes.

	/**
	 * @return the attachementType
	 */
	public String getAttachementType() {
		return attachementType;
	}
	/**
	 * @param attachementType the attachementType to set
	 */
	public void setAttachementType(String attachementType) {
		this.attachementType = attachementType;
	}
	/**
	 * @return the dataAsByteArray
	 */
	public byte[] getDataAsByteArray() {
		return dataAsByteArray;
	}
	/**
	 * @param dataAsByteArray the dataAsByteArray to set
	 */
	public void setDataAsByteArray(byte[] dataAsByteArray) {
		this.dataAsByteArray = dataAsByteArray;
	}
	/**
	 * @return the dataAsStream
	 */
	public InputStream getDataAsStream() {
		return dataAsStream;
	}
	/**
	 * @param dataAsStream the dataAsStream to set
	 */
	public void setDataAsStream(InputStream dataAsStream) {
		this.dataAsStream = dataAsStream;
	}

}
