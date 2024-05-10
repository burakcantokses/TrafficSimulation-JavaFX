import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;

public class MainMenu extends Application {

    private TrafficSimulatorApplication trafficSimulatorApplication;
    public static int level = 1;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Traffic Simulator");

        trafficSimulatorApplication = new TrafficSimulatorApplication();

        // Arka plan görüntüsü
        ImageView background = new ImageView(new Image("file:bg.png"));

        // Butonları içeren VBox
        Button levelSelectionButton = new Button("Level Seçimi");
        levelSelectionButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        levelSelectionButton.setOnMouseEntered(e -> levelSelectionButton.setStyle("-fx-font-size: 20px; -fx-background-color: yellow; -fx-text-fill: black;"));
        levelSelectionButton.setOnMouseExited(e -> levelSelectionButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;"));
        levelSelectionButton.setOnAction(e -> {
            showLevelSelectionPopup(primaryStage);
        });

        Button startGameButton = new Button("Oyuna Başla");
        startGameButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        startGameButton.setOnMouseEntered(e -> startGameButton.setStyle("-fx-font-size: 20px; -fx-background-color: yellow; -fx-text-fill: black;"));
        startGameButton.setOnMouseExited(e -> startGameButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;"));
        startGameButton.setOnAction(e -> {
            try {
                trafficSimulatorApplication.start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        VBox buttonBox = new VBox(20, levelSelectionButton, startGameButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Butonları ve arka planı içeren StackPane
        StackPane stackPane = new StackPane(background, buttonBox);
        Scene scene = new Scene(stackPane, 625, 471);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLevelSelectionPopup(Stage primaryStage) {
        Popup popup = new Popup();
        VBox popupContent = new VBox(10);
        popupContent.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");

        // Level seçenekleri
        for (int i = 1; i <= 5; i++) {
            int level = i;
            Button levelButton = new Button("Level " + level);
            levelButton.setOnAction(e -> {
                // Seçilen seviyeye göre bir şeyler yapabilirsiniz
                MainMenu.level = level;
                System.out.println("Seçilen seviye: " + level);
                popup.hide();
            });
            popupContent.getChildren().add(levelButton);
        }

        Button customLevel = new Button("Özel Level");
        customLevel.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Özel Seviye Dosyası Seçin");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                // Özel seviye seçildiğinde level'i -1 olarak ayarla
                level = -1;
                System.out.println("Seçilen dosya: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Seçim yapılmadı.");
            }
            popup.hide();
        });
        popupContent.getChildren().add(customLevel);

        popup.getContent().add(popupContent);
        popup.setAutoHide(true);

        popup.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}