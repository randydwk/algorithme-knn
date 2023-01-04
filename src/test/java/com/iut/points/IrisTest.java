package com.iut.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.iut.model.Column;

public class IrisTest {
    Iris i1 = new Iris(5.1, 3.5, 1.4, 0.2, "setosa");
    Iris i2 = new Iris(4.9, 3.0, 1.4, 0.3, "setosa");
    Iris i3 = new Iris(4.7, 3.2, 1.3, 0.1, "setosa");

    Column c1 = new Column("sepalLength", null);
    Column c2 = new Column("petalWidth", null);

    @Test
    void testGetValue() {
        assertEquals(5.1, i1.getValue(c1));
        assertEquals(0.2, i1.getValue(c2));
        assertEquals(4.9, i2.getValue(c1));
        assertEquals(0.3, i2.getValue(c2));
        assertEquals(4.7, i3.getValue(c1));
        assertEquals(0.1, i3.getValue(c2));
    }

    @Test
    void toStringTest() {
        assertEquals("Iris [sepalLength=5.1, sepalWidth=3.5, petalLength=1.4, petalWidth=0.2, variety=setosa]",
                i1.toString());
        assertEquals("Iris [sepalLength=4.9, sepalWidth=3.0, petalLength=1.4, petalWidth=0.3, variety=setosa]",
                i2.toString());
        assertEquals("Iris [sepalLength=4.7, sepalWidth=3.2, petalLength=1.3, petalWidth=0.1, variety=setosa]",
                i3.toString());
    }
}
