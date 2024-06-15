package com.text_adventure.woodland_mansion.ui;

import com.text_adventure.woodland_mansion.helper.Delay;

import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UserOutputArea extends Pane {
	private Font gameOutputAreaFont = Font.loadFont(getClass()
			.getResourceAsStream("/com/text_adventure/woodland_mansion/assets/fonts/PixelifySans-Regular.ttf"), 28);

	private TextArea output;

	private Delay delay = new Delay();

	public UserOutputArea() {
		output = new TextArea();
		output.setPrefHeight(350);
		output.setPrefWidth(1200);
		output.setBackground(Background.fill(Color.WHITE));
		output.setStyle("-fx-control-inner-background:#000;");
		output.setFont(gameOutputAreaFont);
		output.setCursor(Cursor.DEFAULT);
		output.setEditable(false);
		output.setMouseTransparent(true);
		output.setFocusTraversable(false);
		getChildren().add(output);
	}

	public void printTextLine(String line) {
		output.appendText(line + "\n");
	}
}
