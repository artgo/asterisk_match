package com.artemgolubev.template;

import static org.assertj.core.api.Assertions.assertThat;
import junit.framework.TestCase;

public class PatternMatcherTest extends TestCase {
	public void testEmptyMatchesEmpty() {
		assertThat(PatternMatcher.matches("", "")).isTrue();
	}

	public void testEmptyMatchesStar() {
		assertThat(PatternMatcher.matches("", "*")).isTrue();
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

	public void testOverlapDoesNotMatch() {
		assertThat(PatternMatcher.matches("abc", "a*b*b*c")).isFalse();
	}

	public void testOverlapDoesNotMatchIfLengthDoesNotMatch() {
		assertThat(PatternMatcher.matches("abc", "a*?*?*c")).isFalse();
	}

	public void testOverlapDoesNotMatchForFirstPart() {
		assertThat(PatternMatcher.matches("aebfc", "a*?e*?*c")).isFalse();
	}

	public void testSwitchDoesNotMatch() {
		assertThat(PatternMatcher.matches("aebfc", "a*f*b*c")).isFalse();
	}

	public void testDoubleMatch() {
		assertThat(PatternMatcher.matches("abbc", "a*b*b*c")).isTrue();
	}

	public void testDoubleOverlapDoesNotMatch() {
		assertThat(PatternMatcher.matches("abdbdc", "a*b?*?b*c")).isFalse();
	}

	public void testDoubleNonOverlapMatch() {
		assertThat(PatternMatcher.matches("abdbdc", "a*b?*b?*c")).isTrue();
	}

	public void testOverlapDoesNotMatchMiddle() {
		assertThat(PatternMatcher.matches("abdc", "a*b*b*c")).isFalse();
	}

	public void testOverlapDoesNotMatchMiddlePre() {
		assertThat(PatternMatcher.matches("aebdc", "a*b*b*c")).isFalse();
	}

	public void testOverlapDoesNotMatchOverlappingPatterns() {
		assertThat(PatternMatcher.matches("aebdc", "a*b?*?d*c")).isFalse();
	}

	public void testOverlapMatchesFollowingPatterns() {
		assertThat(PatternMatcher.matches("aebdc", "a*b*d*c")).isTrue();
	}

	public void testOverlapDoesNotMatchLong() {
		assertThat(PatternMatcher.matches("abcdefghijklmnop", "a?c*e*e*???")).isFalse();
	}

	public void testDoubleStar() {
		assertThat(PatternMatcher.matches("abc", "a**c")).isTrue();
	}

	public void testEmptyMatch() {
		assertThat(PatternMatcher.matches("ac", "a*c")).isTrue();
	}

	public void testEndingStarMatch() {
		assertThat(PatternMatcher.matches("ac", "a*")).isTrue();
	}

	public void testEmptyMatchStart() {
		assertThat(PatternMatcher.matches("a", "*a")).isTrue();
	}

	public void testStartingStarMatching() {
		assertThat(PatternMatcher.matches("ac", "*c")).isTrue();
	}

	public void testEmptyMatchEnd() {
		assertThat(PatternMatcher.matches("a", "a*")).isTrue();
	}

	public void testNoStar() {
		assertThat(PatternMatcher.matches("abc", "a?c")).isTrue();
	}
}
