package com.iut.classification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iut.model.Column;
import com.iut.model.Dataset;
import com.iut.model.IColumn;
import com.iut.model.IMVCModel;
import com.iut.model.Model;
import com.iut.points.IPoint;
import com.iut.points.Iris;
import com.iut.points.Pokemon;
import com.iut.points.Titanic;

class TestDataset {

    private static final Double DELTA = 0.05;

    Dataset setPokemon = new Dataset("pokemon", Pokemon.class);
    Dataset setIris = new Dataset("iris", Iris.class);
    Dataset setTitanic = new Dataset("titanic", Titanic.class);

    IMVCModel modelPokemon = new Model(setPokemon);
    IMVCModel modelIris= new Model(setIris);
    IMVCModel modelTitanic = new Model(setTitanic);

    @BeforeEach
    void init() {
        modelPokemon.loadFromFile(new File("src/main/resources/pokemon_train.csv"));
        modelIris.loadFromFile(new File("src/main/resources/iris.csv"));
        modelTitanic.loadFromFile(new File("src/main/resources/titanic.csv"));
    }

    @Test
    void testNbLignes() {
        assertEquals(507, setPokemon.getNbLines());
        assertEquals(150, setIris.getNbLines());
        assertEquals(891, setTitanic.getNbLines());
    }

    @Test
    void testMinValues() {
        assertEquals(1280, setPokemon.getMinValue(setPokemon.getColumn(2)).doubleValue(), DELTA);
        assertEquals(4.3, setIris.getMinValue(setIris.getColumn(1)).doubleValue(), DELTA);
        assertEquals(0, setTitanic.getMinValue(setTitanic.getColumn(4)).doubleValue(), DELTA);

    }

    @Test
    void testMaxValues() {
        assertEquals(30720, setPokemon.getMaxValue(setPokemon.getColumn(2)).doubleValue(), DELTA);
        assertEquals(7.9, setIris.getMaxValue(setIris.getColumn(1)).doubleValue(), DELTA);
        assertEquals(8, setTitanic.getMaxValue(setTitanic.getColumn(4)).doubleValue(), DELTA);
    }

    @Test
    void testGetTitle() {
        assertEquals("titanic", setTitanic.getTitle());
        assertNotEquals("Titanic", setTitanic.getTitle());
        assertNotEquals("iris", setTitanic.getTitle());
    }

    @Test
    void testAddAllLine() {
        List<IPoint> points = List.of(new Iris(), new Iris(), new Iris());
        int irisSize = setIris.getNbLines();
        setIris.addAllLine(points);
        assertEquals(irisSize + 3, setIris.getNbLines());
    }

    @Test
    void testAddLine() {
        IPoint iris = new Iris();
        int irisSize = setIris.getNbLines();
        setIris.addLine(iris);
        assertEquals(irisSize + 1, setIris.getNbLines());
    }

    @Test
    void testGetColumnByName() {
        Column irisColumn = new Column("sepallength", setIris);
        Column titanicColumn = new Column("age", setTitanic);
        Column pkmnColumn = new Column("speed", setPokemon);

        assertEquals(irisColumn, setIris.getColumnByName("sepallength"));
        assertEquals(titanicColumn, setTitanic.getColumnByName("age"));
        assertEquals(pkmnColumn, setPokemon.getColumnByName("speed"));
    }

    @Test
    void testGetColumns() {
        // columns that should be in setIris
        Column c1 = new Column("sepallength", setIris);
        Column c2 = new Column("sepalwidth", setIris);
        Column c3 = new Column("petallength", setIris);
        Column c4 = new Column("petalwidth", setIris);
        Column c5 = new Column("variety", setIris);

        // actual columns in setIris
        List<IColumn> columns = setIris.getColumns();
        
        assertTrue(columns.containsAll(List.of(c1, c2, c3, c4, c5)));
    }

    

    @Test
    void testGetPoints() {
        Iterator<IPoint> it = setIris.iterator();
        List<IPoint> points = setIris.getPoints();
        int counter = 0;
        while(it.hasNext()) {
            assertEquals(it.next(), points.get(counter++));
        }
    }

    @Test
    void testIterator() {
        Iterator<IPoint> it = setPokemon.iterator();
        int counter = 0;
        while (it.hasNext()) {
            counter++;
            it.next();
        }
        assertEquals(setPokemon.getNbLines(), counter);
    }

    @Test
    void testSetLines() {
        List<IPoint> passagers = List.of(new Titanic(), new Titanic(), new Titanic(), new Titanic());
        int beforeSetSize = setTitanic.getNbLines();
        setTitanic.setLines(passagers);
        assertNotEquals(beforeSetSize, setTitanic.getNbLines());
        assertEquals(4, setTitanic.getNbLines());
        Iterator<IPoint> it = setTitanic.iterator();

        while (it.hasNext()) {
            assertEquals(new Titanic(), it.next());
        }
    }
}
