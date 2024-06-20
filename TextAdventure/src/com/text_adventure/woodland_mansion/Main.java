package com.text_adventure.woodland_mansion;

import com.text_adventure.woodland_mansion.ui.UserCursor;
import com.text_adventure.woodland_mansion.ui.menu.Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Image windowIcon = new Image("/com/text_adventure/woodland_mansion/assets/icons/mansion-icon.png");

	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;

	private Stage stage;
	private Scene scene;
	private BorderPane root;

	UserCursor userCursor;

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		stage = primaryStage;

		root = new BorderPane();
		root.setStyle("-fx-background-color: #000;");

		scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		userCursor = new UserCursor();
		userCursor.setCursor(scene);

		Menu menu = new Menu(root, scene);

		primaryStage.getIcons().add(windowIcon);
		primaryStage.setTitle("Woodland Mansion");
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		root.getChildren().add(menu);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
