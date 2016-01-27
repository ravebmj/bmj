package org.bmj.userinsights.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class implements different file utilities.  
 * File Utility  
 */
public class FileUtility {

	/**
	 * It saves mutipartfile object with detail mentioned in "fileNameWithPath".
	 * @param multipartFile
	 * @param uploadFolderPath
	 * @param fileNameWithPath
	 * @throws Exception
	 */
	public static  void saveFileToLocalDisk(MultipartFile multipartFile,
			String uploadFolderPath,String fileNameWithPath)
			throws Exception {

		File file = new File(uploadFolderPath);
		if (!file.exists()) {// Check folder name of "insightid" is exist.
			file.mkdirs();// If not then make it.
		}
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(
				fileNameWithPath));// Generate file at specified path location.
	}
	
	/**
	 * Move file from one location to other location.
	 * @param sourcePath
	 * @param destPath
	 * @param targetFolder 
	 */
	public static void moveFile(String sourcePath, String destPath, String targetFolder) throws Exception{
		InputStream inStream = null;
		OutputStream outStream = null;

			File sourcefile = new File(sourcePath);
			File targetfile = new File(destPath);

			File file = new File(targetFolder);
			if (!file.exists()) {// Check folder name of "insightid" is exist.
				file.mkdirs();// If not then make it.
			}
			
			
			inStream = new FileInputStream(sourcefile);
			outStream = new FileOutputStream(targetfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete the original file
			sourcefile.delete();


	}
	/**
	 * It deletes file mentioned at specified path.
	 * @param filePath
	 */
	public static void deleteFile(String filePath){
		File sourcefile = new File(filePath);
		// delete the original file
		sourcefile.delete();
	}
	/**
	 * It converts inputstream to array of bytes.
	 * @param stream
	 * @return
	 * @throws IOException
	 */
    public static byte[] readFully(InputStream stream) throws IOException
    {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1)
        {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }	
}
