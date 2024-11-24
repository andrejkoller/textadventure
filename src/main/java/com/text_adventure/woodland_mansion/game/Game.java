package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.helper.Randomizer;
import com.text_adventure.woodland_mansion.ui.UserInputField;
import com.text_adventure.woodland_mansion.ui.UserOutputArea;
import com.text_adventure.woodland_mansion.ui.menu.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game extends Pane {
	private Font exitButtonFont = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 34);

	private Image levelImage;
	private ImageView levelImageView;

	private UserOutputArea gameUserOutput;
	private UserInputField gameUserInput;

	private HBox wrapGameBox = new HBox();
	private VBox gameBox = new VBox();

	private Room[][][] mansion = new Room[10][10][10];
	private Player player;
	private Delay delay = new Delay();

	private int currentX = 0;
	private int currentY = 0;
	private int currentZ = 0;

	private ObservableList<Enemy> enemyData = FXCollections.observableArrayList();

	public Game(BorderPane root, Scene scene) {
		player = new Player("Andrej");
		Enemy ghoul = new Enemy(1, "Ghoul", 20, 3);
		enemyData.add(ghoul);

		Enemy skeever = new Enemy(2, "Skeever", 5, 1);
		enemyData.add(skeever);

		Enemy rottenHand = new Enemy(3, "Rotten Hand", 1, 1);
		enemyData.add(rottenHand);

		if (player.getHealth() <= 0) {
			root.getChildren().clear();
			GameOver gameOver = new GameOver(root, scene);
			root.getChildren().addAll(gameOver);
		}

		wrapGameBox.setAlignment(Pos.CENTER);

		createGame(wrapGameBox, root, scene);

		root.setCenter(wrapGameBox);
	}

	public void initGame(UserOutputArea output, UserInputField input, BorderPane root, Scene scene, Enemy enemy) {
		delay.setDelay(1000, () -> {
			output.printTextLine("You wake up in a dark, moist mansion...");
		});

		delay.setDelay(3000, () -> {
			output.printTextLine("'Where am I?' you ask yourself");
		});

		delay.setDelay(5000, () -> {
			output.printTextLine("'and... what is my name?'");
			output.printTextLine("");
		});

		delay.setDelay(7000, () -> {
			output.printTextLine("Ah I can remember! My name is " + player.getName() + "!");
			output.printTextLine("");
		});

		delay.setDelay(9000, () -> {
			output.printTextLine("I have to find a way out of this uncanny place.");
			output.printTextLine("");
		});

		delay.setDelay(11000, () -> {
			output.printTextLine("Exits: North, East, South, West");
		});

		initCommands(root, scene, input, output, enemy);
		initRooms();
	}

	public void initCommands(BorderPane root, Scene scene, UserInputField input, UserOutputArea output, Enemy enemy) {
		input.commands.put("help", new Command("help", "Print all commands", () -> runHelp(input, output)));

		input.commands.put("status", new Command("status", "Player status", () -> runPlayerStats(output)));
		input.commands.put("location", new Command("location", "Player location", () -> runPlayerLocation(output)));

		input.commands.put("suicide", new Command("suicide", "Kill yourself", () -> runGameOver(root, scene)));

		input.commands.put("go north", new Command("go north", "direction", () -> runGo(0, 1, 0, output, enemy)));
		input.commands.put("go east", new Command("go east", "direction", () -> runGo(1, 0, 0, output, enemy)));
		input.commands.put("go south", new Command("go south", "direction", () -> runGo(0, -1, 0, output, enemy)));
		input.commands.put("go west", new Command("go west", "direction", () -> runGo(-1, 0, 0, output, enemy)));
		input.commands.put("go up", new Command("go up", "direction", () -> runGo(0, 0, 1, output, enemy)));
		input.commands.put("go down", new Command("go down", "direction", () -> runGo(0, 0, -1, output, enemy)));
		input.commands.put("stay here", new Command("stay here", "", () -> runGo(0, 0, 0, output, enemy)));

		input.commands.put("attack", new Command("attack", "kill monsters", () -> runAttack(output)));
	}

	public void initRooms() {
		for (int z = 0; z < 10; z++) {
			for (int y = 0; y < 10; y++) {
				for (int x = 0; x < 10; x++) {
					mansion[x][y][z] = new Room(x, y, z);

					if (Math.random() < 0.2) {
						mansion[x][y][z].spawnMonster();
					}
				}
			}
		}
	}

	public Room getCurrentRoom() {
		return mansion[currentX][currentY][currentZ];
	}

	public void createGame(Pane parentBox, BorderPane root, Scene scene) {
		levelImage = new Image(getClass().getResource("/images/mansion.png").toExternalForm());
		levelImageView = new ImageView(levelImage);
		levelImageView.setFitHeight(550);
		levelImageView.setFitWidth(1200);

		gameUserOutput = new UserOutputArea();
		gameUserInput = new UserInputField(gameUserOutput);

		var exitButton = new Text("Exit to Menu");
		exitButton.setFill(Color.WHITE);
		exitButton.setFont(exitButtonFont);
		exitButton.setTranslateY(25);
		exitButton.setOnMouseClicked(e -> {
			getChildren().clear();
			Menu menu = new Menu(root, scene);
			root.getChildren().addAll(menu);
		});

		gameBox.setAlignment(Pos.CENTER_LEFT);
		gameBox.getChildren().addAll(levelImageView, gameUserOutput, gameUserInput, exitButton);

		initGame(gameUserOutput, gameUserInput, root, scene, null);

		parentBox.getChildren().add(gameBox);
		;
	}

	public void runHelp(UserInputField input, UserOutputArea output) {
		input.commands.forEach((name, cmd) -> {
			output.printTextLine(name + "\t" + cmd.getDescription());
		});
	}

	public void runPlayerStats(UserOutputArea output) {
		output.printTextLine("");
		player.printPlayerName(output);
		player.printPlayerHealth(output);
		player.printPlayerInventory(output);
	}

	public void runPlayerLocation(UserOutputArea output) {
		output.printTextLine("Your are in room: " + currentX + "," + currentY + "," + currentZ);
	}

	public void runGameOver(BorderPane root, Scene scene) {
		root.getChildren().clear();
		GameOver gameOver = new GameOver(root, scene);
		root.getChildren().add(gameOver);
	}

	public void runMove(UserOutputArea output, UserInputField input) {
		player.move(output, input, getCurrentRoom());
	}

	public void runGo(int dx, int dy, int dz, UserOutputArea output, Enemy enemy) {
		if (getCurrentRoom().hasMonster()) {
			Randomizer rando = new Randomizer();
			rando.getRandomItem(enemyData);

			if (rando.getRandomItem(enemyData).getName() == "Ghoul") {
				output.printTextLine("A " + enemyData.get(0).getName() + " is in the room. Kill it!");
			}

			if (rando.getRandomItem(enemyData).getName() == "Skeever") {
				output.printTextLine("A " + enemyData.get(1).getName() + " is in the room. Kill it!");
			}

			if (rando.getRandomItem(enemyData).getName() == "Rotten Hand") {
				output.printTextLine("A " + enemyData.get(2).getName() + " is in the room. Kill it!");
			}

			return;
		}

		currentX += dx;
		currentY += dy;
		currentZ += dz;

		output.printTextLine("You are now in room: " + currentX + "," + currentY + "," + currentZ);
	}

	public void runAttack(UserOutputArea output) {
		getCurrentRoom().killMonsters();
		output.printTextLine("You killed the monsters in this room.");
	}
}