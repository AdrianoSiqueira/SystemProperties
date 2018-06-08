package code;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

class App {

    String collectData() {
        StringBuilder separator = new StringBuilder("=-= =-= =-= =-= =-= =-= =-= =-= =-= =-= \n");
        StringBuilder report = new StringBuilder();

        report.append(separator)
                .append("System.getProperties() \n")
                .append(separator);

        for (String s : System.getProperties().stringPropertyNames()) {
            report.append(s)
                    .append(": ")
                    .append(System.getProperty(s))
                    .append("\n");
        }

        report.append(separator)
                .append("\n")
                .append(separator)
                .append("System.getenv() \n")
                .append(separator);

        for (String s : System.getenv().keySet()) {
            report.append(s)
                    .append(": ")
                    .append(System.getenv(s))
                    .append("\n");
        }

        report.append(separator)
                .append("\n");

        try {
            InetAddress address = InetAddress.getLocalHost();
            report.append(separator)
                    .append("InetAddress.getLocalHost() \n")
                    .append(separator)
                    .append("IP: ")
                    .append(address.getHostAddress())
                    .append("\n")
                    .append("Host: ")
                    .append(address.getHostName())
                    .append("\n")
                    .append(separator)
                    .append("\n");
        } catch (UnknownHostException ignored) {
        }


        report.append(separator)
                .append("Runtime.getRuntime() \n")
                .append(separator)
                .append("CPUs: ")
                .append(Runtime.getRuntime().availableProcessors())
                .append("\n")
                .append(separator)
                .append("\n");

        return report.toString();
    }

    boolean saveToFile(String s) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Report");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.setInitialFileName(System.getProperty("user.name") + " report.txt");
        File file = chooser.showSaveDialog(null);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(s);
                writer.close();
                return true;
            } catch (IOException ignored) {
            }
        }

        return false;
    }

    void showMessage(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Save to File");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}