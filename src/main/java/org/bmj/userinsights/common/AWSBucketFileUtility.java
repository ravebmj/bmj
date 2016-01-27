package org.bmj.userinsights.common;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bmj.userinsights.entity.InsightAttachment;
import org.bmj.userinsights.insight.dto.InsightDto;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
/**
 * Utility class to add, delete , creation of zip file on AWS bucket. 
 * @author nilesh.kambli
 *
 */
public class AWSBucketFileUtility {
	
	private static final  Logger log = Logger.getLogger(AWSBucketFileUtility.class);

	/**
	 * Upload file to AWS bucket.
	 * @param bucketName
	 * @param keyName
	 * @param uploadFileName
	 * @param accessKey
	 * @param secretKey
	 * @throws Exception
	 */
	public static void upload(String bucketName, String keyName,
			String uploadFileName, String accessKey, String secretKey)
			throws Exception {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = new AmazonS3Client(awsCreds);
		File file = new File(uploadFileName);
		s3client.putObject(new PutObjectRequest(bucketName, keyName, file));

	}
	/**
	 * Get file from AWS bucket.
	 * @param bucketName
	 * @param keyName
	 * @param accessKey
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static S3Object getDownLoadFile(String bucketName, String keyName,
			String accessKey, String secretKey) throws Exception {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3Client = new AmazonS3Client(awsCreds);
		S3Object s3object = null;

		s3object = s3Client
				.getObject(new GetObjectRequest(bucketName, keyName));

		return s3object;
	}
	
	/**
	 * Read the file from specified key and put in to zip on the fly and write to the browser
	 * @param bucketName
	 * @param keyName
	 * @param accessKey
	 * @param secretKey
	 * @param response
	 * @throws Exception 
	 */
	public static void writeToZipFile(String bucketName, String keyName,
			String accessKey, String secretKey, HttpServletResponse response,
			InsightDto insightObj) throws Exception {
		log.info("starting of the writeToZipFile method in AWSBucketFileUtility");
		ZipEntry zipentry = null;
		ServletOutputStream sos = null;
		ZipOutputStream zos = null;
		String path = null;
		Map<String, InsightAttachment> map = prepareInsightAttachmentMap(insightObj);
		log.debug("InsightAttachment map "+map.size());
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3Client = new AmazonS3Client(awsCreds);
		
		
		try {
			path = "InsightAttachments_".trim()+insightObj.getInsightDetailsDto().getId()+InsightsConstants.ZIP_FILE_EXT;
			log.debug("path  "+path);
			sos = response.getOutputStream();
			zos = new ZipOutputStream(sos);
			byte bytes[] = new byte[4096];
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=\""+path+"\"");
			
			log.debug("bucketName  "+bucketName);
			log.debug("keyName  "+keyName);
			ObjectListing images = s3Client.listObjects(bucketName, keyName
					+ "/".trim());
			List<S3ObjectSummary> list = images.getObjectSummaries();
			log.debug("list of ObjectSummaries size "+list.size());
			for (S3ObjectSummary image : list) {
				S3Object obj = s3Client.getObject(new GetObjectRequest(
						bucketName, image.getKey()));
				String name = obj.getKey().substring(
						obj.getKey().lastIndexOf("/") + 1,
						obj.getKey().length());

				zipentry = new ZipEntry(map.get(name).getFileName());
				zos.putNextEntry(zipentry);
				InputStream in = downloadFileAsStream(bucketName, obj.getKey(),
						s3Client);
				int bytesRead = -1;

				while ((bytesRead = in.read(bytes)) != -1) {
					zos.write(bytes, 0, bytesRead);
				}
				in.close();
			}
			
		} catch (Exception e)

		{
			e.printStackTrace();
		} finally {
			zos.flush();
			zos.closeEntry();
			zos.close();
			sos.close();
		}
		log.info("ending of the writeToZipFile method in AWSBucketFileUtility");
	}
	
	/**
	 * Prepare the map with uploaded fileId as key and InsightAttachement object as a value
	 * @param insightObj
	 * @return
	 * @throws Exception
	 */
	private static Map<String, InsightAttachment> prepareInsightAttachmentMap(
			InsightDto insightObj) throws Exception {
		Map<String, InsightAttachment> map = new HashMap<String, InsightAttachment>();
		if (insightObj != null && insightObj.getInsightDetailsDto() != null) {
			for (InsightAttachment insightAttachment : insightObj
					.getInsightDetailsDto().getAttachments()) {
				map.put(insightAttachment.getFileId(), insightAttachment);
			}
		}

		return map;
	}
		
	/**
	 * Return the input stream of each file under the key
	 * @param bucketName
	 * @param objectKey
	 * @param s3Client
	 * @return
	 */
	public static InputStream downloadFileAsStream(String bucketName,
			String objectKey, AmazonS3 s3Client) throws Exception {

		GetObjectRequest s3ObjectReq = new GetObjectRequest(bucketName,
				objectKey);
		S3Object downlodedObjectMD = s3Client.getObject(s3ObjectReq);
		return downlodedObjectMD.getObjectContent();

	}
	
	
	
	/**
	 * Delete file from AWS bucket.
	 * @param bucketName
	 * @param keyName
	 * @param accessKey
	 * @param secretKey
	 * @throws Exception
	 */
	public static void deleteFileFromBucket(String bucketName, String keyName,
			String accessKey, String secretKey) throws Exception {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3Client = new AmazonS3Client(awsCreds);

		s3Client.deleteObject(bucketName, keyName);

	}
}
