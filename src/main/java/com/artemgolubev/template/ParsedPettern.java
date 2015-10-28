package com.artemgolubev.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParsedPettern {
	private static final String MULTIPLE_SYMBOLS = "*";

	private final String start;
	private final String end;
	private final boolean hasStars;
	private final List<String> parts;

	private ParsedPettern(String start, String end, boolean hasStars, List<String> parts) {
		this.start = start;
		this.end = end;
		this.hasStars = hasStars;
		this.parts = parts;
	}

	public static ParsedPettern parse(String pattern) {
		if (pattern == null) {
			throw new Shit();
		}

		if (!pattern.contains(MULTIPLE_SYMBOLS)) {
			return new ParsedPettern(pattern, "", false, new ArrayList<String>());
		}

		List<String> sections = new ArrayList<String>(Arrays.asList(pattern.split("\\" + MULTIPLE_SYMBOLS)));
		if (sections.isEmpty()) {
			sections.add("");
		}

		if (pattern.endsWith(MULTIPLE_SYMBOLS)) {
			sections.add("");
		}

		int len = sections.size();

		return new ParsedPettern(sections.get(0), sections.get(len - 1), true, sections.subList(1, len - 1));
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public boolean hasStars() {
		return hasStars;
	}

	public List<String> getParts() {
		return parts;
	}
}
