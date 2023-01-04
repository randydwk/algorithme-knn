package com.iut.view;

import com.iut.algo.KnnAlgorithm;
import com.iut.algo.KnnRobust;
import com.iut.distance.IDistance;
import com.iut.distance.NormalizedEuclideanDistance;
import com.iut.distance.NormalizedManhattanDistance;
import com.iut.distance.RandomDistance;
import com.iut.model.*;
import com.iut.normalization.IValueNormalizer;
import com.iut.points.IPoint;
import com.iut.points.Iris;
import com.iut.points.Pokemon;
import com.iut.points.Titanic;
import com.iut.utils.AbstractSubject;
import com.iut.utils.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainPane extends Stage implements Observer {
    protected final Button IRIS_BUTTON = new Button("Load iris");
    protected final Button TITANIC_BUTTON = new Button("Load titanic");
    protected final Button POKEMON_BUTTON = new Button("Load pokemon");
    protected final Button ROBUST = new Button("Calculer la robustesse");
    protected final Button CLASSIFY = new Button("Classifier");
    protected final TextField K_TEXT = new TextField();
    protected final TextField POINT_INPUT = new TextField();
    protected final ChoiceBox<String> AXE_X_CHOICE = new ChoiceBox<>();
    protected final ChoiceBox<String> AXE_Y_CHOICE = new ChoiceBox<>();
    protected final ChoiceBox<IDistance> DISTANCE_CHOICE = new ChoiceBox<>();
    protected ScatterChart<Number, Number> chart;
    protected List<XYChart.Series<Number,Number>> series = new ArrayList<>();
    protected List<String> resultValues = new ArrayList<>();
    protected Model model;
    protected KnnAlgorithm algo;
    protected BorderPane pain = new BorderPane();
    protected VBox left;

    public MainPane() {
        Scene sc = this.createScene();
        this.createController();
        this.createWindow(sc);
    }

    public void attachModel(Model model) {
        this.model = model;
        model.attach(this);
        algo = new KnnAlgorithm(model);
    }

    public Scene createScene() {
        this.left = this.createLeft();
        this.left.setAlignment(Pos.TOP_CENTER);
        this.left.setPrefHeight(400.0);
        this.left.setPrefWidth(212.0);
        this.left.setPadding(new Insets(3.0));

        this.pain.setLeft(this.left);
        chart = new ScatterChart<>(new NumberAxis(0, 1, 0.1), new NumberAxis(0, 1, 0.1));
        this.pain.setCenter(chart);
        return new Scene(this.pain, 1000.0, 720.0);
    }

    private VBox createLeft() {
        VBox box = new VBox();
        box.setStyle("-fx-border-color:gray;-fx-border-width:1;");
        Label axes = new Label("Axes:");
        axes.setMaxWidth(Double.MAX_VALUE);
        VBox vbox1 = new VBox();
        vbox1.setStyle("-fx-border-color:#ccc;-fx-background-color:#eee;");
        vbox1.setPadding(new Insets(3.0));
        HBox axeX = new HBox();
        HBox axeY = new HBox();
        AXE_X_CHOICE.setPrefWidth(130.0);
        AXE_Y_CHOICE.setPrefWidth(130.0);
        Label axeXLabel = new Label("Axe X:");
        axeXLabel.setPrefWidth(60.0);
        axeX.getChildren().addAll(axeXLabel, AXE_X_CHOICE);
        Label axeYLabel = new Label("Axe Y:");
        axeYLabel.setPrefWidth(60.0);
        axeY.getChildren().addAll(axeYLabel, AXE_Y_CHOICE);
        vbox1.getChildren().add(axeX);
        vbox1.getChildren().add(axeY);
        Label attributes = new Label("Chargement:");
        attributes.setMaxWidth(Double.MAX_VALUE);

        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);

        POINT_INPUT.setPrefWidth(Double.MAX_VALUE);

        HBox kBox = new HBox();
        Label kLabel = new Label("K = ");
        kLabel.setMaxWidth(Double.MAX_VALUE);
        kBox.getChildren().addAll(kLabel, K_TEXT);

        HBox distanceBox = new HBox();
        Label distanceLabel = new Label("Distance:");
        kLabel.setMaxWidth(Double.MAX_VALUE);
        distanceBox.getChildren().addAll(distanceLabel, DISTANCE_CHOICE);
        DISTANCE_CHOICE.setPrefWidth(130.0);
        DISTANCE_CHOICE.getItems().addAll(new NormalizedManhattanDistance(), new NormalizedEuclideanDistance(), new RandomDistance());

        VBox bottom = new VBox(POINT_INPUT, kBox, distanceBox, ROBUST, CLASSIFY);
        bottom.setStyle("-fx-border-color:#ccc;-fx-background-color:#eee;");
        bottom.setPadding(new Insets(3.0));

        box.getChildren().addAll(axes, vbox1, attributes, IRIS_BUTTON, TITANIC_BUTTON, POKEMON_BUTTON, region, bottom);
        IRIS_BUTTON.setPrefWidth(250.0);
        TITANIC_BUTTON.setPrefWidth(250.0);
        POKEMON_BUTTON.setPrefWidth(250.0);
        ROBUST.setPrefWidth(250.0);
        CLASSIFY.setPrefWidth(250.0);
        return box;
    }

    public void createController() {
        IRIS_BUTTON.setOnMouseClicked((event) -> {
            IDataset dataset = new Dataset("Iris", Iris.class);
            irisNormalizeSetup(dataset);
            attachModel(new Model(dataset));
            model.loadFromFile("src/main/resources/iris.csv");
            fillColumns(model.getNormalizableColumns());

            XYChart.Series<Number,Number> setosa = new XYChart.Series<>();
            setosa.setName("Setosa");
            XYChart.Series<Number,Number> versicolor = new XYChart.Series<>();
            versicolor.setName("Versicolor");
            XYChart.Series<Number,Number> virginica = new XYChart.Series<>();
            virginica.setName("Virginica");
            series.clear();
            series.addAll(List.of(setosa, versicolor, virginica));
            resultValues.clear();
            resultValues.addAll(List.of(setosa.getName(), versicolor.getName(), virginica.getName()));
            loadChart(model.defaultXCol().getName(), model.defaultYCol().getName());
        });

        TITANIC_BUTTON.setOnMouseClicked((event) -> {
            IDataset dataset = new Dataset("Titanic", Titanic.class);
            titanicNormalizeSetup(dataset);
            attachModel(new Model(dataset));
            model.loadFromFile("src/main/resources/titanic.csv");
            System.out.println(model.getDataset().getNbLines());
            fillColumns(model.getNormalizableColumns());

            XYChart.Series<Number,Number> survivants = new XYChart.Series<>();
            survivants.setName("Survivants");
            XYChart.Series<Number,Number> morts = new XYChart.Series<>();
            morts.setName("Morts");
            series.clear();
            series.addAll(List.of(survivants, morts));
            resultValues.clear();
            resultValues.addAll(List.of("1", "0"));
            loadChart(model.defaultXCol().getName(), model.defaultYCol().getName());

        });

        POKEMON_BUTTON.setOnMouseClicked((event) -> {
            IDataset dataset = new Dataset("Pokemon", Pokemon.class);
            pokemonNormalizeSetup(dataset);
            attachModel(new Model(dataset));
            model.loadFromFile("src/main/resources/pokemon_train.csv");
            System.out.println(model.getDataset().getNbLines());
            fillColumns(model.getNormalizableColumns());

            XYChart.Series<Number,Number> legendaire = new XYChart.Series<>();
            legendaire.setName("Legendaire");
            XYChart.Series<Number,Number> commun = new XYChart.Series<>();
            commun.setName("Commun");
            series.clear();
            series.addAll(List.of(legendaire, commun));
            resultValues.clear();
            resultValues.addAll(List.of("true", "false"));
            loadChart(model.defaultXCol().getName(), model.defaultYCol().getName());
        });

        ROBUST.setOnMouseClicked((event) -> {
            int k;
            IDistance d;
            try {
                k = Integer.parseInt(K_TEXT.getText());
                d = DISTANCE_CHOICE.getValue();
                if (d == null) {
                    throw new IllegalStateException();
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Veuillez entrer un K correct").show();
                return;
            } catch (IllegalStateException e) {
                new Alert(Alert.AlertType.ERROR, "Choisissez une distance").show();
                return;
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(this);
            KnnRobust robust = new KnnRobust(model, file);
            String toDisplay = "Robustesse du modèle pour k = " + k + ":\n\t" + robust.robust(k, d).toString();
            new Alert(Alert.AlertType.INFORMATION, toDisplay).show();
        });

        CLASSIFY.setOnMouseClicked((event) -> new Knn());

        AXE_X_CHOICE.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String yLabel = AXE_Y_CHOICE.getValue();
            if (t1 != null && yLabel != null){
                loadChart(t1,yLabel);
            }
        });

        AXE_Y_CHOICE.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String xLabel = AXE_X_CHOICE.getValue();
            if (xLabel != null && t1 != null){
                loadChart(xLabel,t1);
            }
        });

        POINT_INPUT.setOnAction((event) -> {
            String text = POINT_INPUT.getText();
            model.createPoint(text);
        });
    }

    private void titanicNormalizeSetup(IDataset dataset) {
        dataset.fillColumns();
        dataset.getColumns().get(1).setNormalizer(IValueNormalizer.NormalizerTypes.ORDINAL_NORMALIZER.getNorm());
        dataset.getColumns().get(2).setNormalizer(IValueNormalizer.NormalizerTypes.BINARY_NORMALIZER.getNorm());
        dataset.getColumns().get(3).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(4).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(5).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(6).setNormalizer(IValueNormalizer.NormalizerTypes.ENUMERATIVE_NORMALIZER.getNorm());
    }

    private void irisNormalizeSetup(IDataset dataset) {
        dataset.fillColumns();
        dataset.getColumns().get(1).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(2).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(3).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(4).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
    }

    private void pokemonNormalizeSetup(IDataset dataset) {
        dataset.fillColumns();
        dataset.getColumns().get(2).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(3).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(4).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
        dataset.getColumns().get(5).setNormalizer(IValueNormalizer.NormalizerTypes.NUMBER_NORMALIZER.getNorm());
    }

    public void fillColumns(List<IColumn> columns) {
        AXE_X_CHOICE.getItems().clear();
        AXE_Y_CHOICE.getItems().clear();
        for (IColumn column : columns) {
            AXE_X_CHOICE.getItems().add(column.getName());
            AXE_Y_CHOICE.getItems().add(column.getName());
        }
    }

    public void loadChart(String xAxis, String yAxis) {
        System.out.println(series.toString());
        System.out.println(resultValues.toString());

        chart.getData().clear();
        for (XYChart.Series<Number,Number> serie : series) {
            serie.getData().clear();
            chart.getData().add(serie);
        }
        chart.getXAxis().setLabel(xAxis);
        chart.getYAxis().setLabel(yAxis);
        IDataset currentSet = model.getDataset();
        chart.setTitle(currentSet.getTitle());

        List<XYChart.Data<Number,Number>> datas = new ArrayList<>();

        for (IPoint point : currentSet) {
            XYChart.Data<Number, Number> data = new XYChart.Data<>(
                    point.getNormalizedValue(currentSet.getColumnByName(xAxis)),
                    point.getNormalizedValue(currentSet.getColumnByName(yAxis)),
                    point
            );
            datas.add(data);
            for (int i = 0; i < resultValues.size(); i++) {
                if (point.getValue(currentSet.getColumns().get(0)).toString().equals(resultValues.get(i))) {
                    series.get(i).getData().add(data);
                }
            }
        }

        // point visualisation! :)
        for (XYChart.Data<Number, Number> data : datas) {
            String toDisplay = data.getExtraValue().toString()
                    .replace("[",":\n\t")
                    .replace(", ", "\n\t")
                    .replace("]","");
            data.getNode().setOnMouseClicked((event) -> new Alert(Alert.AlertType.INFORMATION, toDisplay).show());
        }
    }

    public void createWindow(Scene sc) {
        this.setScene(sc);
        String title = "Classification Knn";
        this.setTitle(title);
        this.show();
    }

    public void update(AbstractSubject subj) {
        String xAxis = AXE_X_CHOICE.getValue() == null ? model.defaultXCol().toString() : AXE_X_CHOICE.getValue();
        String yAxis = AXE_Y_CHOICE.getValue() == null ? model.defaultYCol().toString() : AXE_Y_CHOICE.getValue();

        loadChart(xAxis, yAxis);
    }

    public void update(AbstractSubject subj, Object data) {
    }

    public class Knn extends Stage {
        protected final ListView<IPoint> VIEW = new ListView<>();
        protected final Button GO = new Button("Calculer la classe");

        public Knn() {
            Scene scene = createScene();
            controllers();
            createWindow(scene);
        }

        public Scene createScene() {
            VBox box = new VBox(VIEW, GO);
            VIEW.setPrefHeight(400);
            VIEW.setPrefWidth(100);
            GO.setPrefWidth(Double.MAX_VALUE);

            for (IPoint point : model.getDataset()) {
                VIEW.getItems().add(point);
            }

            return new Scene(box, 600,300);
        }

        public void controllers() {
            GO.setOnMouseClicked((event) -> {
                int k;
                IDistance d;
                try {
                    k = Integer.parseInt(K_TEXT.getText());
                    d = DISTANCE_CHOICE.getValue();
                    if (d == null) {
                        throw new IllegalStateException();
                    }

                } catch (NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Veuillez entrer un K correct").show();
                    return;
                } catch (IllegalStateException e) {
                    new Alert(Alert.AlertType.ERROR, "Choisissez une distance").show();
                    return;
                }
                IPoint pt = VIEW.getSelectionModel().getSelectedItems().get(0);
                ICategory cat = algo.calculateCategory(k, pt, d);

                String toDisplay = "Catégorie du point calculée par " + k + "-NN : \n\t" + cat.getTitle();
                new Alert(Alert.AlertType.INFORMATION, toDisplay).showAndWait();
                this.close();
            });
        }

        public void createWindow(Scene scene) {
            this.setScene(scene);
            String title = "Classe";
            this.setTitle(title);
            this.show();
        }
    }
}
