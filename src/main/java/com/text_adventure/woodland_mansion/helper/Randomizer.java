package com.text_adventure.woodland_mansion.helper;

import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public class Randomizer extends Pane {
	public Randomizer() {
	}
	
	public <T> T getRandomItem(ObservableList<T> list) {
		Random random = new Random();
		int listSize = list.size();
		int randomIndex = random.nextInt(listSize);
		return list.get(randomIndex);
	}
}
