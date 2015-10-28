package com.artemgolubev.template;

public class PatternMatcher {
	public static boolean matches(String input, String pattern) {
		if (TemplateUtils.isNullOrEmpty(pattern)) {
			return TemplateUtils.isNullOrEmpty(input);
		}

		ParsedPettern pp = ParsedPettern.parse(pattern);

		if (!TemplateUtils.startsFrom(input, pp.getStart())) {
			return false;
		}

		if (!pp.hasStars()) {
			return input.length() == pp.getStart().length();
		}

		if (!TemplateUtils.endsWith(input, pp.getEnd())) {
			return false;
		}

		int from = pp.getStart().length();
		int to = input.length() - pp.getEnd().length();

		for(String part : pp.getParts()) {
			int index = TemplateUtils.indexOf(input, part, from, to);
			if (index == TemplateUtils.NOT_FOUND) {
				return false;
			}
			from = index + part.length();
		}

		return true;
	}
}
