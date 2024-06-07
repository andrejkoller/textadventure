package application;

import MainMenuUI.MainMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public  class Main extends Application {
	private Stage stage;
	private Scene scene;
	private Group root;

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		Image icon = new Image("/assets/images/mansion-icon.png");

		stage = primaryStage; 
		root = new Group();
		scene = new Scene(root, 1920, 1080, Color.BLACK);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		MainMenu mainMenu = new MainMenu();

		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Woodland Mansion");
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint(""); 

		root.getChildren().addAll(mainMenu);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
