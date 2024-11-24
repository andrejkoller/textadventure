package com.text_adventure.woodland_mansion.game;

public class Enemy {
	private int monsterId;
	private String name;
	private int health;
	private int damage;

	public Enemy(int id, String name, int health, int damage) {
		this.monsterId = id;
		this.name = name;
		this.health = health;
		this.damage = damage;
	}
	
	public int getId() {
		return monsterId;
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
}