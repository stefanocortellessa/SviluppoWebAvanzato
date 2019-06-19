package it.univaq.disim.swa.visitaq.business.impl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import com.google.common.hash.Hashing;

public class Utility {

	protected static SecureRandom random = new SecureRandom();

	// Method to generate a User Token
	public static String generateToken() {

		long longToken = Math.abs(random.nextLong());

		return Long.toString(longToken, 200);
	}

	// Method to encode the password
	public String hashPwd(String originalString) {

		String hashed = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();

		return hashed;
	}
}
