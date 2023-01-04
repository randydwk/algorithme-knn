package com.iut.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iut.normalization.BooleanNormalizer;
import com.iut.normalization.IValueNormalizer;

class ColumnTest {
    Column c1, c2;
    Dataset set;
    IValueNormalizer normalizer;
    
    @BeforeEach
    void init() {
        c1 = new Column("survived", set);
        c2 = new Column("age", set);
        c1.setNormalizer(new BooleanNormalizer());
    }

    @Test
    void getNameTest() {
        assertEquals("survived", c1.getName());
        assertEquals("age", c2.getName());
    }

    @Test
    void getDatasetTest() {
        assertEquals(set, c1.getDataset());
        assertEquals(set, c2.getDataset());
    }

    @Test
    void isNormalizableTest() {
        assertTrue(c1.isNormalizable());
        assertFalse(c2.isNormalizable());
    }

    @Test
    void testGetDenormalizedValue() {
        
    }

    @Test
    void testGetNormalizedValue() {
        
    }

    @Test
    void testSetNormalizer() {
        
    }
}
