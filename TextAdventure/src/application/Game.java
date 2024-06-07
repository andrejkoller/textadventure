package application;

import java.util.HashMap;
import java.util.Map;

import MainMenuUI.MainMenu;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game extends Pane {
	Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 34);

	private static final int WIDTH = 1920;
	@SuppressWarnings("unused")
	private static final int HEIGHT = 1080;

	private MainMenu menu;

	private BorderPane gameLayout;

	private Room[][][] room = new Room[4][3][3];

	private TextArea output;
	private TextField input;
	private Text exitButton;

	private VBox game;
	private VBox gameBox;

	private Map<String, Command> commands = new HashMap<>();

	public Game() {
		game = createGame();

		gameLayout = new BorderPane();
		gameLayout.setCenter(game);
		gameLayout.setLayoutX((WIDTH - 1200) - 360);
		gameLayout.setLayoutY(50);

		getChildren().addAll(gameLayout);
	}

	public void setWorld() {
		int counter = 1;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < 3; z++) {
					room[x][y][z] = new Room(counter, "up", "ladder");

					if (z == 0) {
						System.out.println("Untergeschoss Raum X: " + x + " Y: " + y + " wurde befüllt. Raum ID: "
								+ room[x][y][z].getRoomID());
					} else if (z == 1) {
						System.out.println("Erdgeschoss Raum X: " + x + " Y: " + y + " wurde befüllt. Raum ID:  "
								+ room[x][y][z].getRoomID());
					} else {
						System.out.println("Dachboden Raum X: " + x + " Y: " + y + " wurde befüllt. Raum ID:  "
								+ room[x][y][z].getRoomID());
					}

					if (Math.random() < 0.2) {
						room[x][y][z].spawnEnemies();
					}

					counter++;
				}
			}
		}

		Player player = new Player("Andrej");
		Room playerlocation = room[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)];

		System.out.println(playerlocation.getRoomID());
	}

	public void printTextLine(String line) {
		output.appendText(line + "\n");
	}

	public void onUserInput(String line) {
		if (!commands.containsKey(line)) {
			printTextLine("Command not found");
			return;
		}

		commands.get(line).execute();
	}

	public void initGame() {
		printTextLine("You wake up in a dark, moist mansion... \n'What was my name?' you ask yourself:");
	}

	public VBox createGame() {
		Image levelImage = new Image("assets/images/mansion.png");
		ImageView levelImageView = new ImageView(levelImage);
		levelImageView.setFitHeight(550);
		levelImageView.setFitWidth(1200);

		output = new TextArea();
		output.setPrefHeight(350);
		output.setPrefWidth(600);
		output.setBackground(Background.fill(Color.WHITE));
		output.setStyle("-fx-control-inner-background:#000;");
		output.setFont(font);
		output.setCursor(Cursor.DEFAULT);
		output.setEditable(false);
		output.setMouseTransparent(true);
		output.setFocusTraversable(false);

		input = new TextField();
		input.setPrefHeight(50);
		input.setFont(font);
		input.clear();
		input.setStyle("-fx-control-inner-background: #000;" + "-fx-display-focuse: none;" + "-fx-border-color: #fff;");
		input.setOnAction(e -> {
			String inputText = input.getText();
			input.clear();
			onUserInput(inputText);
		});

		exitButton = new Text("Exit to Menu");
		exitButton.setStyle("-fx-cursor: hand;");
		exitButton.setFill(Color.WHITE);
		exitButton.setFont(font);
		exitButton.setTranslateY(25);
		exitButton.setOnMouseClicked(e -> {
			getChildren().clear();
			menu = new MainMenu();
			getChildren().add(menu);
		});

		gameBox = new VBox(levelImageView, output, input, exitButton);

		initGame();

		return gameBox;
	}
}
