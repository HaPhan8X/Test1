package com.tvo.puzzle.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/***
 * 
 * 
 * @author MinhTQ
 * 
 */
public class StringUtil {

	public static final String DELIMITER_SIGN = ";";

	public static byte[] hexToBytes(char[] hex) {
		try {
			int length = hex.length / 2;
			byte[] raw = new byte[length];
			for (int i = 0; i < length; i++) {
				int high = Character.digit(hex[i * 2], 16);
				int low = Character.digit(hex[i * 2 + 1], 16);
				int value = (high << 4) | low;
				if (value > 127)
					value -= 256;
				raw[i] = (byte) value;
			}
			return raw;
		} catch (Exception e) {
			return new byte[0];
		}
	}

	public static String lowerCaseFirstCharacter(String inString) {
		try {
			if (!isNull(inString)) {
				String firstC = inString.substring(0, 1);
				inString = inString.replaceFirst(firstC, firstC.toLowerCase());
			}
		} catch (Exception e) {
		}
		return inString;
	}

	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		try {
			for (int i = 0; i < b.length; i++) {
				result += Integer.toString((b[i] & 0xff) + 0x100, 16)
						.substring(1);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static boolean isNull(String str) {
		return (str == null || str.trim().length() <= 0);
	}

	public static List<String> separateString(String string) {
		List<String> result = null;
		try {
			if (!isNull(string)) {
				result = new ArrayList<String>();
				StringTokenizer token = new StringTokenizer(string,
						DELIMITER_SIGN);
				while (token.hasMoreElements()) {
					String value = (String) token.nextElement();
					result.add(value);
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static List<String> separateStringNotDuplicateValue(String string) {
		List<String> result = null;
		try {
			if (!isNull(string)) {
				result = new ArrayList<String>();
				StringTokenizer token = new StringTokenizer(string,
						DELIMITER_SIGN);
				while (token.hasMoreElements()) {
					String value = (String) token.nextElement();
					if (!result.contains(value)) {
						result.add(value);
					}

				}
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String buildLikeQuery(String param) {
		StringBuilder strBuilder = new StringBuilder();
		try {
			strBuilder.append("%");
			strBuilder.append(param);
			strBuilder.append("%");
		} catch (Exception e) {
		}
		return strBuilder.toString();
	}


	/**
	 * 
	 * Convert string to array
	 * 
	 * @author MinhTQ
	 * 
	 * @param String
	 *            input
	 * 
	 * @return String[]
	 */
	public static String[] stringToArray(String input) {
		if (!isNull(input)) {
			return input.split(" ");
		}
		return null;
	}

	public static String[] stringToArray(String input, String sign) {
		if (!isNull(input)) {
			return input.split(sign);
		}
		return null;
	}

	/**
	 * generate session id when user logging in
	 * 
	 * @author MinhTQ
	 * 
	 * @return sessionId
	 * 
	 */
	public static String generateSessionId() {
		try {
			Random r = new Random();
			String token = Long.toString(Math.abs(r.nextLong()), 36);
			return token;
		} catch (Exception e) {
			return "";
		}
	}


	public static boolean isPhoneNumber(String phone) {
		try {
			String PHONE_PATTERN = "[+]{0,1}\\d{9,15}"; // [+]{0,1}\\d{9,15}
			Pattern pattern = Pattern.compile(PHONE_PATTERN);
			Matcher matcher = null;
			if (phone.startsWith("+"))
				pattern.matcher(phone.substring(1));
			else
				pattern.matcher(phone);

			return matcher.matches();
		} catch (Exception e) {
			return false;
		}
	}

	public static Double roundDouble(Double r) {
		try {
			int decimalPlace = 4;
			BigDecimal bd = new BigDecimal(r);
			bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
			r = bd.doubleValue();
			return r;
		} catch (Exception e) {
			return new Double(0);
		}
	}

}
