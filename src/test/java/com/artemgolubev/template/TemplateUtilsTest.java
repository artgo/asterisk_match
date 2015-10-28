package com.artemgolubev.template;

import static org.assertj.core.api.Assertions.assertThat;
import junit.framework.TestCase;

public class TemplateUtilsTest extends TestCase {
	public void testStartFromMatchesFullStr() {
		assertThat(TemplateUtils.startsFrom("abc", "a?c")).isTrue();
	}

	public void testStartFromMatchesPartialStr() {
		assertThat(TemplateUtils.startsFrom("abczzz", "a?c")).isTrue();
	}

	public void testStartFromMatchesDoesntStart() {
		assertThat(TemplateUtils.startsFrom("zabc", "a?c")).isFalse();
	}

	public void testEndsWithMatchesFullStr() {
		assertThat(TemplateUtils.endsWith("abc", "a?c")).isTrue();
	}

	public void testEndsWithMatchesPartialStr() {
		assertThat(TemplateUtils.endsWith("zzzabc", "a?c")).isTrue();
	}

	public void testEndsWithDoesntEnd() {
		assertThat(TemplateUtils.endsWith("abcz", "a?c")).isFalse();
	}

	public void testIndexExist() {
		assertThat(TemplateUtils.indexOf("zabcz", "a?c", 0, 5)).isEqualTo(1);
	}

	public void testIndexNotExist() {
		assertThat(TemplateUtils.indexOf("zabdz", "a?c", 0, 5)).isEqualTo(-1);
	}

	public void testIndexNotExistEnd() {
		assertThat(TemplateUtils.indexOf("zzabc", "a?c", 0, 5)).isEqualTo(2);
	}

	public void testIndexNotExistStart() {
		assertThat(TemplateUtils.indexOf("abczz", "a?c", 0, 5)).isEqualTo(0);
	}
}
