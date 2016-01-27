package org.bmj.userinsights.awsbucket.service;

import javax.servlet.http.HttpServletResponse;

import org.bmj.userinsights.awsbucket.dto.AWSFileDetail;
import org.bmj.userinsights.dto.AWSBucketConfig;
import org.bmj.userinsights.insight.dto.InsightDto;

public interface IAwsBucketService {
	public AWSBucketConfig getAWSBucketConfig() throws Exception;
	public AWSFileDetail getFileFromAWSBucket(String insightId,String fileId) throws Exception;
	public void uploadFileToBucket(String insightId,String fileId,String fileNameWithPath) throws Exception;
	public void deleteFileFromBucket(String insightId,String fileId) throws Exception;
	public void createAttachmentsZip(String insightId,InsightDto insightObj,HttpServletResponse response) throws Exception;
}
