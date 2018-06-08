package code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        App app = new App();

        TextArea areaReport = new TextArea(app.collectData());
        areaReport.setEditable(false);
        areaReport.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (app.saveToFile(areaReport.getText())) {
                    app.showMessage(Alert.AlertType.INFORMATION, "Sucess", "File was saved successfuly.");
                } else {
                    app.showMessage(Alert.AlertType.ERROR, "Error", "File couldn't be saved.");
                }
            }
        });

        StackPane pane = new StackPane();
        pane.getChildren().add(areaReport);

        Scene scene = new Scene(pane);
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll("/css/RootStyle.css", "/css/TextAreaStyle.css");

        stage.setTitle("System Properties");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}