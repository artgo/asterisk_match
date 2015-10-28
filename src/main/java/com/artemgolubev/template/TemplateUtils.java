package com.artemgolubev.template;

/**
 * Work with patters containing question mark
 *
 */
public class TemplateUtils {
	private static final char ANY_CHAR = '?';
	public static final int NOT_FOUND = -1;

	public static boolean startsFrom(String input, String startPattern) {
		if (isNullOrEmpty(startPattern)) {
			return true;
		}

		if (isNullOrEmpty(input) || input.length() < startPattern.length()) {
			return false;
		}

		for (int i = 0; i < startPattern.length(); i++) {
			char patternChar = startPattern.charAt(i);
			if ((patternChar != '?') && (patternChar != input.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static boolean endsWith(String input, String endPattern) {
		if (isNullOrEmpty(endPattern)) {
			return true;
		}

		if (isNullOrEmpty(input) || input.length() < endPattern.length()) {
			return false;
		}

		int patternLen = endPattern.length();
		int len = input.length();

		for (int i = 1; i <= endPattern.length(); i++) {
			char patternChar = endPattern.charAt(patternLen - i);
			if ((patternChar != ANY_CHAR) && (patternChar != input.charAt(len - i))) {
				return false;
			}
		}

		return true;
	}

	public static int indexOf(String input, String pattern, int from, int to) {
		if (isNullOrEmpty(pattern)) {
			return from;
		}

		if (isNullOrEmpty(input) || input.length() < pattern.length()) {
			return NOT_FOUND;
		}

		int len = pattern.length();

		for (int i = from; i < to; i++) {
			boolean foundNonMatch = false;
			for (int j = 0; j < len; j++) {
				char patternChar = pattern.charAt(j);
				if ((patternChar != ANY_CHAR) && (patternChar != input.charAt(i + j))) {
					foundNonMatch = true;
					break;
				}
			}
			if (!foundNonMatch) {
				return i;
			}
		}

		return NOT_FOUND;
	}

	public static boolean isNullOrEmpty(String startPattern) {
		return (startPattern == null) || "".equals(startPattern);
	}
}
