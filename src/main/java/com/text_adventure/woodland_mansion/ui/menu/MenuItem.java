package com.text_adventure.woodland_mansion.ui.menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuItem extends Pane {
	private Font font = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);
	private Text mainMenuText;

	public MenuItem(MenuButton button) {
		mainMenuText = new Text(button.getMenuButtonText());
		mainMenuText.setFont(font);
		mainMenuText.setFill(Color.WHITE);
		mainMenuText.setTextAlignment(TextAlignment.CENTER);
		mainMenuText.setOnMouseClicked(button.getOnMouseClicked());
		getChildren().add(mainMenuText);
	}
}
