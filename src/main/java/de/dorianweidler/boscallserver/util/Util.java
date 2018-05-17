package de.dorianweidler.boscallserver.util;

import java.security.SecureRandom;

public class Util {

	private static final char[] ALLOWED_CHARACTERS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
			'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z' };

	public static String generateRandomString(int length) {
		StringBuilder keyBuilder = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for(int i = 0; i < length; i++) {
			keyBuilder.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)]);
		}
		return keyBuilder.toString();
	}

}
