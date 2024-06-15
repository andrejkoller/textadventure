package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.ui.UserInputField;
import com.text_adventure.woodland_mansion.ui.UserOutputArea;
import com.text_adventure.woodland_mansion.ui.menu.Menu;

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
			.getResourceAsStream("/com/text_adventure/woodland_mansion/assets/fonts/PixelifySans-Regular.ttf"), 34);

	private Image levelImage;
	private ImageView levelImageView;

	private Image skullIconImage;
	private ImageView skullIconImageView;

	private UserOutputArea gameUserOutput;
	private UserInputField gameUserInput;

	private HBox wrapGameBox = new HBox();
	private VBox gameBox = new VBox();
	private HBox wrapButtonBox = new HBox();

	private Room[][][] mansion = new Room[4][3][3];
	private Room room = new Room();
	private Player player;
	private Delay delay = new Delay();

	private int currentX = 0;
	private int currentY = 0;
	private int currentZ = 0;

	public Game(BorderPane root, Scene scene) {
		player = new Player("Andrej", 100);

		wrapGameBox.setAlignment(Pos.CENTER);

		createGame(wrapGameBox, root, scene);

		root.setCenter(wrapGameBox);
	}

	public void initGame(UserOutputArea output, UserInputField input, BorderPane root, Scene scene) {
		delay.setDelay(1000, () -> {
			output.printTextLine("You wake up in a dark, moist mansion...");
		});

		delay.setDelay(3000, () -> {
			output.printTextLine("'Where am I?' you ask yourself");
			output.printTextLine("");
		});

		delay.setDelay(5000, () -> {
			output.printTextLine("'and... what is my name?'");
		});

		delay.setDelay(7000, () -> {
			output.printTextLine("Ah I can remember! My name is " + player.getName() + ".");
			output.printTextLine("");
		});

		delay.setDelay(9000, () -> {
			output.printTextLine(
					"You check yourself in which condition you are: " + player.getHealth() + " Lifepoints.");
			output.printTextLine("");
		});

		delay.setDelay(11000, () -> {
			output.printTextLine("Exits: North - go north, East - go east, South - go south, West - go west");
		});

		initCommands(input, output);
		initRooms();
	}

	public void initCommands(UserInputField input, UserOutputArea output) {
		input.commands.put("help", new Command("help", "Print all commands", () -> runHelp(input, output)));

		input.commands.put("status", new Command("status", "Player status", () -> runPlayerStats(output)));
		input.commands.put("location", new Command("location", "Player location", () -> runPlayerLocation(output)));

		input.commands.put("go north", new Command("go north", "direction", () -> runGo(0, 1, 0, output)));
		input.commands.put("go east", new Command("go east", "direction", () -> runGo(1, 0, 0, output)));
		input.commands.put("go south", new Command("go south", "direction", () -> runGo(0, -1, 0, output)));
		input.commands.put("go west", new Command("go west", "direction", () -> runGo(-1, 0, 0, output)));
		input.commands.put("go up", new Command("go up", "direction", () -> runGo(0, 0, 1, output)));
		input.commands.put("go down", new Command("go down", "direction", () -> runGo(0, 0, -1, output)));
		input.commands.put("stay here", new Command("stay here", "", () -> runGo(0, 0, 0, output)));

		input.commands.put("attack", new Command("attack", "kill monsters", () -> runAttack(output)));
	}

	public void initRooms() {
		for (int z = 0; z < 3; z++) {
			for (int y = 0; y < 3; y++) {
				for (int x = 0; x < 4; x++) {
					mansion[x][y][z] = new Room(x, y, z);

					if (Math.random() < 0.2) {
						mansion[x][y][z].spawnMonsters();
					}
				}
			}
		}
	}

	public Room getCurrentRoom() {
		return mansion[currentX][currentY][currentZ];
	}

	public void createGame(Pane parentBox, BorderPane root, Scene scene) {
		levelImage = new Image("/com/text_adventure/woodland_mansion/assets/images/mansion.png");
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

		skullIconImage = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_dead.png");
		skullIconImageView = new ImageView(skullIconImage);
		skullIconImageView.setFitHeight(50);
		skullIconImageView.setFitWidth(50);
		skullIconImageView.setTranslateY(25);
		skullIconImageView.setOnMouseClicked(e -> {
			root.getChildren().clear();
			GameOver gameOver = new GameOver(root, scene);
			root.getChildren().addAll(gameOver);
		});

		wrapButtonBox.setSpacing(940);
		wrapButtonBox.getChildren().addAll(exitButton, skullIconImageView);

		gameBox.setAlignment(Pos.CENTER_LEFT);
		gameBox.getChildren().addAll(levelImageView, gameUserOutput, gameUserInput, wrapButtonBox);

		initGame(gameUserOutput, gameUserInput, root, scene);

		parentBox.getChildren().add(gameBox);
		;
	}

	public void runHelp(UserInputField input, UserOutputArea output) {
		input.commands.forEach((name, cmd) -> {
			output.printTextLine(name + "\t" + cmd.getDescription());
		});
	}

	public void runPlayerStats(UserOutputArea output) {
		player.printPlayerName(output);
		player.printPlayerHealth(output);
		player.printPlayerInventory(output);
	}

	public void runPlayerLocation(UserOutputArea output) {
		room.getCurrentFloor(player);
	}

	public void runMove(UserOutputArea output, UserInputField input) {
		player.move(output, input, getCurrentRoom());
	}

	public void runGo(int dx, int dy, int dz, UserOutputArea output) {
		if (getCurrentRoom().hasMonsters()) {
			output.printTextLine("This room has monsters! Clear it before you can proceed.");
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
