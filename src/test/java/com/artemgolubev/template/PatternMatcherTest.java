package com.artemgolubev.template;

import static org.assertj.core.api.Assertions.assertThat;
import junit.framework.TestCase;

public class PatternMatcherTest extends TestCase {
	public void testEmptyMatchesEmpty() {
		assertThat(PatternMatcher.matches("", "")).isTrue();
	}

	public void testNonEmptyDoesNotMatchEmpty() {
		assertThat(PatternMatcher.matches("a", "")).isFalse();
	}

	public void testHappyPath() {
		assertThat(PatternMatcher.matches("abcdefghijklmnop", "a?c*e?*?l*???")).isTrue();
	}

	public void testHappyPathDoubleStar() {
		assertThat(PatternMatcher.matches("abcdefghijklmnop", "a?c*e?**?l*???")).isTrue();
	}

	public void testHappyNotPathEnd() {
		assertThat(PatternMatcher.matches("abcdefghijklmnop", "a?c*e?*?l*??z")).isFalse();
	}

	public void testHappyNotPathMiddle() {
		assertThat(PatternMatcher.matches("abcdefghijklmnop", "a?c*z?*?*???")).isFalse();
	}

	public void testHappyNotPathStart() {
		assertThat(PatternMatcher.matches("abcdefghijklmnop", "b?c*f?*?l*???")).isFalse();
	}

	public void testDoubleStar() {
		assertThat(PatternMatcher.matches("abc", "a**c")).isTrue();
	}

	public void testEmptyMatch() {
		assertThat(PatternMatcher.matches("ac", "a*c")).isTrue();
	}

	public void testNoStar() {
		assertThat(PatternMatcher.matches("abc", "a?c")).isTrue();
	}
}
