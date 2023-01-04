package com.iut.classification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iut.model.Dataset;
import com.iut.model.Model;
import com.iut.normalization.BinaryNormalizer;
import com.iut.normalization.EnumerativeNormalizer;
import com.iut.normalization.NumberNormalizer;
import com.iut.normalization.OrdinalNormalizer;
import com.iut.points.Titanic;

public class TestNormalizer {
	/**
	 *
	 */
	private static final double DELTA = 0.05;
	Dataset set = new Dataset("titanic", Titanic.class);
	Model model = new Model(set);

	@BeforeEach
	public void before() {
		model.loadFromFile(new File("src/main/resources/titanic.csv"));
		set.getColumn(1).setNormalizer(new OrdinalNormalizer());
		set.getColumn(2).setNormalizer(new BinaryNormalizer());
		set.getColumn(3).setNormalizer(new NumberNormalizer());
		set.getColumn(4).setNormalizer(new NumberNormalizer());
		set.getColumn(5).setNormalizer(new NumberNormalizer());
		set.getColumn(6).setNormalizer(new EnumerativeNormalizer());
	}

	@Test
	public void testNormalizeNumberNormalizer() {
		// Age
		assertEquals(0.27, set.getColumn(3).getNormalizedValue(set.getPoints().get(0)), DELTA); // 22 22-0.42/80-0.42 0.27
		// SibSp
		assertEquals(0.12, set.getColumn(4).getNormalizedValue(set.getPoints().get(0)), DELTA); // 1-0/8-0
		// Fare
		assertEquals(0.077, set.getColumn(5).getNormalizedValue(set.getPoints().get(0)), 0.1); // 7.25-0/93.5-0
	}

	@Test
	public void testNormalizeOrdinalNormalizer() {
		// classe
		assertEquals(0, set.getColumn(1).getNormalizedValue(set.getPoints().get(0)));
		assertEquals(0.5, set.getColumn(1).getNormalizedValue(set.getPoints().get(9)));
		assertEquals(1, set.getColumn(1).getNormalizedValue(set.getPoints().get(1)));
	}

	@Test
	public void testNormalizeBinaryNormalizer() {
		// sex
		assertEquals(0, set.getColumn(2).getNormalizedValue(set.getPoints().get(0)));
		assertEquals(1, set.getColumn(2).getNormalizedValue(set.getPoints().get(1)));

	}

	@Test
	public void testNormalizeEnumerativeNormalizer() {
		// embarked
		assertEquals(0, set.getColumn(6).getNormalizedValue(set.getPoints().get(0)));
		assertEquals(0.5, set.getColumn(6).getNormalizedValue(set.getPoints().get(1)));
		assertEquals(1, set.getColumn(6).getNormalizedValue(set.getPoints().get(5)));
	}

	@Test
	public void testDenormalizeNumberNormalizer() {
		// age
		assertEquals(22.0,
				set.getColumn(3).getDenormalizedValue(set.getColumn(3).getNormalizedValue(set.getPoints().get(0))));
		// SibSp
		assertEquals(1.0,
				set.getColumn(4).getDenormalizedValue(set.getColumn(4).getNormalizedValue(set.getPoints().get(0))));
		// Fare
		assertEquals(7.25,
				set.getColumn(5).getDenormalizedValue(set.getColumn(5).getNormalizedValue(set.getPoints().get(0))));
	}

	@Test
	public void testDenormalizeOrdinalNormalizer() {
		// classe
		assertEquals(3, set.getColumn(1).getDenormalizedValue(0));
		assertEquals(2, set.getColumn(1).getDenormalizedValue(0.5));
		assertEquals(1, set.getColumn(1).getDenormalizedValue(1));
	}

	@Test
	public void testDenormalizeBinaryNormalizer() {
		// sex
		assertEquals("male", set.getColumn(2).getDenormalizedValue(0));
		assertEquals("female", set.getColumn(2).getDenormalizedValue(1));

	}

	@Test
	public void testDenormalizeEnumerativeNormalizer() {
		// embarked
		assertEquals('S', set.getColumn(6).getDenormalizedValue(0));
		assertEquals('C', set.getColumn(6).getDenormalizedValue(0.5));
		assertEquals('Q', set.getColumn(6).getDenormalizedValue(1));

	}
}
