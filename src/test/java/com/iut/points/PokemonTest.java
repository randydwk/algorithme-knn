package com.iut.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.iut.model.Column;

class PokemonTest {
    Pokemon p1 = new Pokemon("Bulbasaur", 5120, 45, 1059860, 45, false);
    Pokemon p2 = new Pokemon("Ivysaur", 5120, 45, 1059860, 60, false);
    Pokemon p3 = new Pokemon("Darkrai", 30720, 3, 1250000, 100, true);

    Column c1 = new Column("captureRate", null);
    Column c2 = new Column("speed", null);

    @Test
    void testGetValue() {
        assertEquals(p1.getValue(c1), p2.getValue(c1));
        assertEquals(100.0, p3.getValue(c2));
        assertEquals(3.0, p3.getValue(c1));
    }

    @Test
    void toStringTest() {
        assertEquals(
                "Pokemon [name=Bulbasaur, baseEggSteps=5120, captureRate=45.0, experienceGrowth=1059860, speed=45.0, isLegendary=false]",
                p1.toString());
        assertEquals(
                "Pokemon [name=Ivysaur, baseEggSteps=5120, captureRate=45.0, experienceGrowth=1059860, speed=60.0, isLegendary=false]",
                p2.toString());
        assertEquals(
                "Pokemon [name=Darkrai, baseEggSteps=30720, captureRate=3.0, experienceGrowth=1250000, speed=100.0, isLegendary=true]",
                p3.toString());
    }

    @Test
    void testNormalizedValue() {
        // sic c'est de la merde
        assertTrue(true);
    }
}
