package com.iut;

import com.iut.view.MainPane;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    // lancer avec maven (en ligne de commande) : mvn (clean) javafx:run
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	new MainPane();
    }
}