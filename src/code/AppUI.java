package code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppUI extends Application {
    private App app;

    private Scene scene;
    private Stage stage;

    private TextArea areaReport;
    private StackPane pane;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initComponents();

        areaReport.setText(app.collectData());
        areaReport.setEditable(false);
        areaReport.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (app.saveToFile(areaReport.getText())) {
                    app.showMessage(Alert.AlertType.INFORMATION, "Success", "File was saved successfully.");
                } else {
                    app.showMessage(Alert.AlertType.ERROR, "Error", "File could not be saved.");
                }
            }
        });

        pane.getChildren().add(areaReport);
        pane.getStyleClass().add("pane");

        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().addAll("/css/PaneStyle.css", "/css/TextAreaStyle.css");

        stage.setTitle("System Properties");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void initComponents() {
        app = new App();
        areaReport = new TextArea();
        pane = new StackPane();
        scene = new Scene(pane);
    }
}