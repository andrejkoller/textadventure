package com.text_adventure.woodland_mansion.ui;

import java.util.HashMap;
import java.util.Map;

import com.text_adventure.woodland_mansion.game.Command;

import javafx.scene.ImageCursor;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class UserInputField extends Pane {
	private final Font gameInputFieldFont = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 28);
	private final Image inputCursor = new Image(getClass().getResource("/icons/cursor_pen.png").toExternalForm());
	private TextField input;
	private String inputText;

	public Command command;
	public Map<String, Command> commands = new HashMap<>();

	public UserInputField(UserOutputArea output) {
		this.input = new TextField();
		input.setPrefHeight(50);
		input.setPrefWidth(1200);
		input.setFont(this.gameInputFieldFont);
		input.clear();
		input.setStyle("-fx-control-inner-background: #000;" + "-fx-display-focuse: none;" + "-fx-border-color: #fff;");
		input.setOnAction(e -> {
			this.inputText = input.getText();
			input.clear();
			onUserInput(this.inputText, output);
		});

		input.setCursor(new ImageCursor(this.inputCursor));
		getChildren().add(this.input);
	}

	public void onUserInput(String line, UserOutputArea output) {
		if (!commands.containsKey(line)) {
			output.printTextLine("");
			output.printTextLine("Command not found!");
			output.printTextLine("Type 'help' to show a list of commands.");
			return;
		}

		commands.get(line).execute();
	}
}
