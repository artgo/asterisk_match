package com.artemgolubev.template;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import junit.framework.TestCase;

public class ParsedPetternTest extends TestCase {
	public void testAcceptFull() {
		ParsedPettern pattern = ParsedPettern.parse("a*b*c");
		assertThat(pattern.getStart()).isEqualTo("a");
		assertThat(pattern.getEnd()).isEqualTo("c");
		assertThat(pattern.hasStars()).isTrue();
		assertThat(pattern.getParts()).hasSize(1);
		assertThat(pattern.getParts().get(0)).isEqualTo("b");
	}

	public void testFirstEmpty() {
		ParsedPettern pattern = ParsedPettern.parse("*b*c");
		assertThat(pattern.getStart()).isEqualTo("");
		assertThat(pattern.getEnd()).isEqualTo("c");
		assertThat(pattern.hasStars()).isTrue();
		assertThat(pattern.getParts()).hasSize(1);
		assertThat(pattern.getParts().get(0)).isEqualTo("b");
	}

	public void testAcceptList() {
		ParsedPettern pattern = ParsedPettern.parse("a*b*");
		assertThat(pattern.getStart()).isEqualTo("a");
		assertThat(pattern.getEnd()).isEqualTo("");
		assertThat(pattern.hasStars()).isTrue();
		assertThat(pattern.getParts()).hasSize(1);
		assertThat(pattern.getParts().get(0)).isEqualTo("b");
	}

	public void testAcceptNull() {
		assertThatThrownBy(() -> ParsedPettern.parse(null)).isInstanceOf(Shit.class);
	}

	public void testAcceptEmpty() {
		ParsedPettern pattern = ParsedPettern.parse("");
		assertThat(pattern.getStart()).isEqualTo("");
		assertThat(pattern.getEnd()).isEqualTo("");
		assertThat(pattern.hasStars()).isFalse();
		assertThat(pattern.getParts()).isEmpty();
	}

	public void testAcceptNoStar() {
		ParsedPettern pattern = ParsedPettern.parse("a");
		assertThat(pattern.getStart()).isEqualTo("a");
		assertThat(pattern.getEnd()).isEqualTo("");
		assertThat(pattern.hasStars()).isFalse();
		assertThat(pattern.getParts()).isEmpty();
	}

	public void testAcceptStarBefore() {
		ParsedPettern pattern = ParsedPettern.parse("*a");
		assertThat(pattern.getStart()).isEqualTo("");
		assertThat(pattern.getEnd()).isEqualTo("a");
		assertThat(pattern.hasStars()).isTrue();
		assertThat(pattern.getParts()).isEmpty();
	}

	public void testAcceptStarAfter() {
		ParsedPettern pattern = ParsedPettern.parse("a*");
		assertThat(pattern.getStart()).isEqualTo("a");
		assertThat(pattern.getEnd()).isEqualTo("");
		assertThat(pattern.hasStars()).isTrue();
		assertThat(pattern.getParts()).isEmpty();
	}

	public void testAcceptJustStar() {
		ParsedPettern pattern = ParsedPettern.parse("*");
		assertThat(pattern.getStart()).isEqualTo("");
		assertThat(pattern.getEnd()).isEqualTo("");
		assertThat(pattern.hasStars()).isTrue();
		assertThat(pattern.getParts()).isEmpty();
	}
}
