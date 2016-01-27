package org.bmj.userinsights.dto;

import java.io.Serializable;

/**
 * Class which holds configuration to connect AWS bucket.
 * @author nilesh.kambli
 *
 */
public class AWSBucketConfig implements Serializable{

	private static final long serialVersionUID = 5300842306322663378L;
	String BucketName;
	String accessKey;
	String secretKey;
	/**
	 * @return the bucketName
	 */
	public String getBucketName() {
		return BucketName;
	}
	/**
	 * @param bucketName the bucketName to set
	 */
	public void setBucketName(String bucketName) {
		BucketName = bucketName;
	}
	/**
	 * @return the accessKey
	 */
	public String getAccessKey() {
		return accessKey;
	}
	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
