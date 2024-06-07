package application;

import java.util.Scanner;

import javafx.scene.layout.Pane;

public class Weapon extends Pane {
	Scanner sc = new Scanner(System.in);
	private String name;
	private String category;
	private int damage;

	public Weapon(String name, String category, int damage) {
		this.name = name;
		this.category = category;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
