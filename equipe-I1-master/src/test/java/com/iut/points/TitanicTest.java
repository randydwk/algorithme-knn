package com.iut.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.iut.model.Column;

public class TitanicTest {
    Titanic t1 = new Titanic(1, 3, "male", 24, 0, 25.6, 'S');
    Titanic t2 = new Titanic(1, 1, "female", 56, 6, 69.4, 'S');
    Titanic t3 = new Titanic(0, 2, "male", 47.3, 3, 47.6, 'C');

    Column c1 = new Column("sex", null);
    Column c2 = new Column("age", null);

    @Test
    void testGetValue() {
        assertEquals("male", t1.getValue(c1));
        assertEquals("female", t2.getValue(c1));
        assertEquals("male", t3.getValue(c1));
        assertEquals(24.0, t1.getValue(c2));
        assertEquals(56.0, t2.getValue(c2));
        assertEquals(47.3, t3.getValue(c2));
    }

    @Test
    void testToString() {
        assertEquals("Titanic [survived=1, pClass=3, sex=male, age=24.0, sibSp=0, fare=25.6, embarked=S]",
                t1.toString());
        assertEquals("Titanic [survived=1, pClass=1, sex=female, age=56.0, sibSp=6, fare=69.4, embarked=S]",
                t2.toString());
        assertEquals("Titanic [survived=0, pClass=2, sex=male, age=47.3, sibSp=3, fare=47.6, embarked=C]",
                t3.toString());
    }
}
