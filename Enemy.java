package com.text_adventure.woodland_mansion.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Enemy {
	private String name;
	private int health;
	private int damage;

	private ObservableList<Enemy> enemies = FXCollections.observableArrayList(new Enemy("Ghoul", 60, 5),
			new Enemy("Skeleton", 20, 3), new Enemy("Skeever", 1, 1), new Enemy("Green Slime", 4, 10),
			new Enemy("Rotten Hand", 0, 0), new Enemy("Werewolf", 100, 40), new Enemy("Gargoyl", 150, 20));

	public Enemy(String name, int health, int damage) {
		this.name = name;
		this.health = health;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public ObservableList<Enemy> getEnemies() {
		return enemies;
	}
}