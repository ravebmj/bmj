package org.bmj.userinsights.common;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * This class is for encode and decoding Tokens 
 */
public class BMJTokenUtility {

	private static final long serialVersionUID = -4319283587727971652L;
	static ResourceBundle mb1= ResourceBundle.getBundle("userinsights_messages");
	
	private static final Logger log = Logger.getLogger(BMJTokenUtility.class);

	/**
	 * To generate encoded token
	 * @param token
	 * @param passPharse
	 * @return token
	 */
	public static String getGeneratedEncodedToken(String token,String passPharse) throws Exception{
		try {
			return TokenEncriptOrDecriptUtility.encryptToken(token,passPharse);
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,mb1.getString("generate_encode_token"));
			log.error("Error while generating the token");
			
		}

		return token;
	}

	/**
	 * To generate decoded token
	 * @param token
	 * @param passPharse
	 * @return token
	 */
	public static String getGeneratedDecodedToken(String token,String passPharse)throws Exception {
		try {
			log.debug(token);
			return TokenEncriptOrDecriptUtility.decryptToken(token,passPharse);
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,mb1.getString("generate_decode_token"));
			log.error("Error while generating the token");
			
		}

		return null;
	}
	
	public static String getGeneratedToken() {
		String token = InsightsConstants.TOKEN_PREFIX
				+ CommonUtils.getCurrentDateString();
		return token;
	}
	
	/**
	 * To generate encrypted password
	 */
	public static void main (String arg[]) throws Exception{
		//************* Initial declaration ***********************//
		//This passPhrase will be maintained in the constant file.
		String passPhrase="73wIf4Ook0VHZatJBbalpM8vUn6s8wpq3Kta7x5ECXy";
		//User password choice
		String password = "sadmin@123";
		//This key will be used to decode the password which is stored in the DB, but this value will not appear in the code or DB.
		String encodingKey = "BMJ_userinsight@2016#rAveDev2015";
		//************* Initial declaration end here ***********************//
		
		
		//This value will be stored in the DB to generate the key for password decryption. 
		String encodedPassPhrase=getGeneratedEncodedToken(encodingKey,passPhrase);
		//The code to get the key which will be used for decode and decrypt the password which is stored in the DB.
		String encodingKey_DB=getGeneratedDecodedToken(encodedPassPhrase,passPhrase);
		
		System.out.println("Please store me in to DB to decode&decrypt your password:- "+encodedPassPhrase);
		System.out.println("Hey I am your actual key to decode and decrypt your password:-  "+encodingKey_DB);
		
		
		
		//The code to generate the encrypted and encoded password. This value will be stored in to DB.
		String encodedPassword=getGeneratedEncodedToken(password,encodingKey_DB);
		//The code to generate the original password from the database encrypted and encoded password.
		String originalPassword=getGeneratedDecodedToken(encodedPassword,encodingKey_DB);
		
		System.out.println("Please store me in to DB, I am the user encrypted&encoded password:- "+encodedPassword);
		System.out.println("Hey I am your actual user password, please validate me with user input:-  "+originalPassword);
		
		/*String tokenGen = getGeneratedToken();
		System.out.println("tokenGen "+tokenGen);
		
		String encodedTokenGen = getGeneratedEncodedToken(encodingKey, tokenGen);
		System.out.println("encodedTokenGen "+encodedTokenGen);*/
	}



}
