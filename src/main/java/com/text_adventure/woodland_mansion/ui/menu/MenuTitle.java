package com.text_adventure.woodland_mansion.ui.menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuTitle extends Pane {
	private final Font font = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);
	private final Text mainTitle;

	public MenuTitle(String name) {
		String spread = "";

		for (char c : name.toCharArray()) {
			spread += c + " ";
		}

		this.mainTitle = new Text(spread);
		mainTitle.setFont(this.font);
		mainTitle.setFill(Color.WHITE);
		mainTitle.setTextAlignment(TextAlignment.CENTER);
		getChildren().addAll(this.mainTitle);
	}
}
