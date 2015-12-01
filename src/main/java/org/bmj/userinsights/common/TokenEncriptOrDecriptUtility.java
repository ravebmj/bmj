package org.bmj.userinsights.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import org.apache.commons.codec.binary.Base64;

public class TokenEncriptOrDecriptUtility {
	
	
	/**
	 * This method is decrypting the token
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String decryptToken (String token,String passPharse) throws Exception
	{
		ResourceBundle messagesProperties= MessageBundle.getResourceBundle();
        byte[] encryptedToken = Base64.decodeBase64(token);

		SecretKeySpec secretKeySpec = tokenKeySpec(messagesProperties,passPharse);
                
        Cipher cipher = Cipher.getInstance(messagesProperties.getString(InsightsConstants.SECRET_KEY));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decryptedtoken = cipher.doFinal(encryptedToken);
        
        return new String(decryptedtoken);
	}
	
	/**
	 * This method returns the secret key specifications.
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	private static SecretKeySpec tokenKeySpec(ResourceBundle configProperties,String passPharse)throws UnsupportedEncodingException, NoSuchAlgorithmException,Exception
	{
		byte[] key = (passPharse).getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16); // use only first 128 bit
		
		return new SecretKeySpec(key, configProperties.getString(InsightsConstants.SECRET_KEY));
	}
	
	/**
	 * Method to generate the encrypted token.
	 * @param mscNo
	 * @return
	 * @throws Exception
	 */
	public static String encryptToken (String token,String passPharse) throws Exception
	{
		ResourceBundle messagesProperties= MessageBundle.getResourceBundle();
		SecretKeySpec secretKeySpec = tokenKeySpec(messagesProperties,passPharse);

        Cipher cipher = Cipher.getInstance(messagesProperties.getString(InsightsConstants.SECRET_KEY));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedToken = cipher.doFinal((token).getBytes());
        
		return new String(Base64.encodeBase64URLSafe(encryptedToken));
	}



}
