package com.text_adventure.woodland_mansion.ui.menu;

import javafx.scene.layout.Pane;

public class MenuButton extends Pane {
	private String text;

	public MenuButton(String text) {
		this.text = text;
	}
	
	public String getMenuButtonText() {
		return text;
	}
}