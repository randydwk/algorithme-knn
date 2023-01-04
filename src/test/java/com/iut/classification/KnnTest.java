package com.iut.classification;

import com.iut.algo.KnnAlgorithm;
import com.iut.algo.KnnRobust;
import com.iut.distance.NormalizedManhattanDistance;
import com.iut.model.*;
import com.iut.normalization.NumberNormalizer;
import com.iut.points.IPoint;
import com.iut.points.Pokemon;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class KnnTest {

    @Test
    void robustesseTest() {
        IDataset set;

        set = new Dataset("pokemon", Pokemon.class);
        set.fillColumns();
        for (int i = 2; i <= 5; i++) {
            set.getColumns().get(i).setNormalizer(new NumberNormalizer());
        }
        IMVCModel model = new Model(set);
        KnnAlgorithm algo = new KnnAlgorithm(model);

        model.loadFromFile("src/main/resources/pokemon_train.csv");

        IPoint toClass = new Pokemon("Swablu", 5120, 255, 600000, 1.2, false);
        ICategory cat = algo.calculateCategory(0, toClass, new NormalizedManhattanDistance());
        assertNull(cat);
        cat = algo.calculateCategory(1, toClass, new NormalizedManhattanDistance());
        assertFalse(Boolean.parseBoolean(cat.toString()));
        cat = algo.calculateCategory(3, toClass, new NormalizedManhattanDistance());
        assertFalse(Boolean.parseBoolean(cat.toString()));

        KnnRobust rob = new KnnRobust(model, new File("src/main/resources/pokemon_test.csv"));
        Double robustesse = rob.robust(5, new NormalizedManhattanDistance());
        assertEquals(1.0, robustesse, 0.05);
    }
}
