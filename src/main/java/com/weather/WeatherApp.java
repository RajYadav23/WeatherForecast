package com.weather;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WeatherApp extends Application {

    @Override
    public void start(Stage stage) {

        // Input field
        TextField cityField = new TextField();
        cityField.setPromptText("Enter city name");
        cityField.setPrefWidth(220);

        Button getWeatherBtn = new Button("Get Weather");

        // Weather display box
        VBox currentWeatherBox = new VBox(12);
        currentWeatherBox.setPadding(new Insets(12));
        currentWeatherBox.setAlignment(Pos.CENTER_LEFT);
        currentWeatherBox.setStyle(
                "-fx-border-color: #cfd8dc;" +
                "-fx-border-radius: 8;" +
                "-fx-background-color: #f4faff;" +
                "-fx-background-radius: 8;"
        );

        VBox root = new VBox(15, cityField, getWeatherBtn, currentWeatherBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        getWeatherBtn.setOnAction(e -> {

            String city = cityField.getText().trim();
            if (city.isEmpty()) {
                showAlert("Please enter a city name.");
                return;
            }

            currentWeatherBox.getChildren().clear();

            WeatherData data = WeatherService.getCurrentWeather(city);

            if (data == null) {
                showAlert("Unable to fetch weather data.");
                return;
            }

            // Temperature
            HBox tempBox = infoRow("🌡 Temperature:", String.format("%.1f °C", data.getTemp()));

            // Humidity
            HBox humidityBox = infoRow("💧 Humidity:", data.getHumidity() + " %");

            // Wind
            HBox windBox = infoRow("💨 Wind:", String.format("%.1f m/s", data.getWind()));

            // Condition + icon
            ImageView icon = new ImageView(new Image(data.getIconUrl(), 50, 50, true, true));
            HBox conditionBox = infoRow("☁ Condition:", data.getCondition());
            conditionBox.getChildren().add(icon);

            currentWeatherBox.getChildren().addAll(
                    tempBox,
                    humidityBox,
                    windBox,
                    conditionBox
            );
        });

        Scene scene = new Scene(root, 420, 340);
        stage.setTitle("Weather Forecast App");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Helper method to create rows
    private HBox infoRow(String labelText, String valueText) {
        Text label = new Text(labelText);
        label.setFont(Font.font(16));

        Text value = new Text(valueText);
        value.setFont(Font.font(16));

        HBox box = new HBox(10, label, value);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
