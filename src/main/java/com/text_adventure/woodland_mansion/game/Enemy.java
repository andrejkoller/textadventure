package com.text_adventure.woodland_mansion.game;

public class Enemy {
	private final int id;
	private String name;
	private int health;
	private int damage;

	public Enemy(int id, String name, int health, int damage) {
		this.id = id;
		this.name = name;
		this.health = health;
		this.damage = damage;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}