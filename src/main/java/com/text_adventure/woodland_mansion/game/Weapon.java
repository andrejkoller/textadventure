package com.text_adventure.woodland_mansion.game;

import javafx.scene.layout.Pane;

public class Weapon extends Pane {

	private String type;
	private int damage;

	public Weapon(String type, int damage) {
		this.type = type;
		this.damage = damage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void printWeaponStats() {

	}
}
