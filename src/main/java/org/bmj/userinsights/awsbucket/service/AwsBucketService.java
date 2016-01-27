package org.bmj.userinsights.awsbucket.service;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bmj.userinsights.awsbucket.dto.AWSFileDetail;
import org.bmj.userinsights.common.AWSBucketFileUtility;
import org.bmj.userinsights.common.FileUtility;
import org.bmj.userinsights.dto.AWSBucketConfig;
import org.bmj.userinsights.insight.Dao.IInsightDao;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

import com.amazonaws.services.s3.model.S3Object;
/**
 * Service class to copy , delete file and creation of zip file in AWS location.
 * @author nilesh.kambli
 *
 */
public class AwsBucketService implements IAwsBucketService{
	
	private static final  Logger log = Logger.getLogger(AwsBucketService.class);

	/**
	 * Get configuration details for AWS bucket connections.
	 */
	@Override
	public AWSBucketConfig getAWSBucketConfig() throws Exception {
		return  getDaoRef().getAWSBucketConfig();
	}
	/**
	 * Get file from AWS bucket.
	 * It returs only single file.
	 * @param insightId
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public AWSFileDetail getFileFromAWSBucket(String insightId,String fileId) throws Exception{
		AWSFileDetail awsFileDetail=new AWSFileDetail();
		AWSBucketConfig awsBucketConfig=getAWSBucketConfig();
		S3Object s3Object=AWSBucketFileUtility.getDownLoadFile(awsBucketConfig.getBucketName(), insightId+"/"+fileId, awsBucketConfig.getAccessKey(), awsBucketConfig.getSecretKey());
		awsFileDetail.setDataAsByteArray(FileUtility.readFully(s3Object.getObjectContent()));
		awsFileDetail.setAttachementType(s3Object.getObjectMetadata().getContentType());
		awsFileDetail.setDataAsStream(s3Object.getObjectContent());
		return awsFileDetail;
	}
	
	/**
	 * Delete file from AWS bucket.
	 * @param insightId
	 * @param fileId
	 * @param fileNameWithPath
	 * @throws Exception
	 */
	public void deleteFileFromBucket(String insightId,String fileId) throws Exception{
		AWSBucketConfig awsBucketConfig=getAWSBucketConfig();
		AWSBucketFileUtility.deleteFileFromBucket(awsBucketConfig.getBucketName(), insightId+"/"+fileId, awsBucketConfig.getAccessKey(), awsBucketConfig.getSecretKey());
	}	
	/**
	 * Upload file to AWS bucket.
	 * @param insightId
	 * @param fileId
	 * @param fileNameWithPath
	 * @throws Exception
	 */
	public void uploadFileToBucket(String insightId,String fileId,String fileNameWithPath) throws Exception{
		AWSBucketConfig awsBucketConfig=getAWSBucketConfig();
		AWSBucketFileUtility.upload(awsBucketConfig.getBucketName(), insightId+"/"+fileId,fileNameWithPath, awsBucketConfig.getAccessKey(), awsBucketConfig.getSecretKey());
		// Delete the uploaded file from temp folder.
		FileUtility.deleteFile(fileNameWithPath);
	}
	
	/**
	 * Create zip on AWS bucket
	 * @param insightId
	 * @param fileNameWithPath
	 */
	public void createAttachmentsZip(String insightId,InsightDto insightObj,HttpServletResponse response) throws Exception{
		log.info("starting of the createAttachmentsZip method in AWSBucketFileUtility");
		AWSBucketConfig awsBucketConfig=getAWSBucketConfig();
		AWSBucketFileUtility.writeToZipFile(awsBucketConfig.getBucketName(), insightId,  awsBucketConfig.getAccessKey(), awsBucketConfig.getSecretKey(),response,insightObj);
		log.info("ending of the createAttachmentsZip method in AWSBucketFileUtility");
	}
	
	/**
	 * get reference to User Insight Dao implementation.
	 * @return
	 * @throws Exception
	 */
	private IInsightDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IInsightDao)ctx.getBean("refInsightDaoImpl");
	}	
}
