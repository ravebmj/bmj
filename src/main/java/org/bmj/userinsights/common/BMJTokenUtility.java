package org.bmj.userinsights.common;


import org.apache.log4j.Logger;

public class BMJTokenUtility {

	private static final long serialVersionUID = -4319283587727971652L;

	public BMJTokenUtility() {
		MessageBundle mb = new MessageBundle();
	}
	private static final Logger log = Logger.getLogger(BMJTokenUtility.class);

	public static String getGeneratedToken(String txnId) {
		String token = InsightsConstants.TOKEN_PREFIX + txnId
				+ CommonUtils.getCurrentDateString();
		return token;
	}
	// generate "txn_dtls_ref" column value of table "TransactionDetails" which is based on primary key of "UserTransaction"
	public static String getTxnDtlRef(String txnId) {
		String token = InsightsConstants.TXN_DTL_REF_PREFIX +"_" +txnId+"_"
				+ CommonUtils.getCurrentDateString();
		return token;
	}
	public static String getGeneratedEncodedToken(String token,String passPharse) {
		try {
			return TokenEncriptOrDecriptUtility.encryptToken(token,passPharse);
		} catch (Exception e) {
			log.error("Error while generating the token");
			e.printStackTrace();
		}

		return token;
	}

	public static String getGeneratedDecodedToken(String token,String passPharse) {
		try {
			log.debug(token);
			return TokenEncriptOrDecriptUtility.decryptToken(token,passPharse);
		} catch (Exception e) {
			log.error("Error while generating the token");
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main (String arg[]){
		String passPhrase="73wIf4Ook0VHZatJBbalpM8vUn6s8wpq3Kta7x5ECXy";
		String a=getGeneratedEncodedToken("password",passPhrase);
		String b=getGeneratedDecodedToken(a,passPhrase);
		System.out.println(a +"  "+b);
	}



}
