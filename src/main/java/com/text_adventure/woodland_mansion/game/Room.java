package com.text_adventure.woodland_mansion.game;

import javafx.scene.layout.Pane;

public class Room extends Pane {
	private int roomID;

	private String verticalPath = "up";
	private String verticalPathType = "ladder";

	private boolean hasDoorNorth = true;
	private boolean hasDoorEast = true;
	private boolean hasDoorSouth = true;
	private boolean hasDoorWest = true;
	private boolean hasHealthItem = true;
	private boolean hasBeginnerWeapon = true;
	private boolean hasStrongWeapon = true;

	@SuppressWarnings("unused")
	private int x;
	@SuppressWarnings("unused")
	private int y;
	@SuppressWarnings("unused")
	private int z;

	private boolean hasMonsters = false;

	public Room() {
		this.roomID = 1;
	}

	public Room(int x, int y, int z) {
		/*
		 * this.roomID = id; this.verticalPath = verticalPath; this.verticalPathType =
		 * verticalPathType; this.hasDoorNorth = doorNorth; this.hasDoorEast = doorEast;
		 * this.hasDoorSouth = doorSouth; this.hasDoorWest = doorWest;
		 */
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean hasMonster() {
		return this.hasMonsters;
	}

	public void spawnMonster() {
		this.hasMonsters = true;
	}

	public void killMonsters() {
		this.hasMonsters = false;
	}

	public String getCurrentFloor(Player player) {
		String floor;

		switch (player.getPositionXYZ(2)) {
			case 2:
				floor = "attic";
				break;
			case 1:
				floor = "ground floor";
				break;
			default:
				floor = "cellar";
				break;
		}

		return floor;
	}

	public void lookAround(Room playerlocation, Player player) {

		System.out.println("room ID: " + getRoomID() + "\nfloor: " + getCurrentFloor(player));

		boolean[] doorCheckArray = { hasDoorNorth, hasDoorEast, hasDoorSouth, hasDoorWest };
		int firstdirection = 0;

		System.out.print("You see doors that lead ");
		for (int i = 0; i < doorCheckArray.length; i++) {
			if (doorCheckArray[i] == true) {
				switch (i) {
					case 0:
						System.out.print("north");
						break;
					case 1:
						System.out.print("east");
						break;
					case 2:
						System.out.print("south");
						break;
					case 3:
						System.out.print("west");
				}
				firstdirection = i;
				break;
			}
		}

		for (int i = firstdirection + 1; i < doorCheckArray.length; i++) {

			if (doorCheckArray[i] == true) {
				System.out.print(", ");
				switch (i) {
					case 0:
						System.out.print("north");
						break;
					case 1:
						System.out.print("east");
						break;
					case 2:
						System.out.print("south");
						break;
					case 3:
						System.out.print("west");
				}
			}
		}

		System.out.println(".");

		if (!"none".equals(playerlocation.getVerticalPath()) && playerlocation.getVerticalPath() != null) {
			System.out.println("You also see a  " + playerlocation.getVerticalPathType() + " that leads "
					+ playerlocation.getVerticalPath() + ".");
		}

		if (playerlocation.isHasHealthItem() == true) {
			System.out.println(
					"\nWhile searching the room you found a first aid kit. \nYou store the kit in your Inventory");
			player.pickupHealthItem(playerlocation);
		}
	}

	public int getRoomID() {
		return this.roomID;
	}

	public void setRoomID(int a) {
		this.roomID = a;
	}

	public String getVerticalPath() {
		return this.verticalPath;
	}

	public void setVerticalPath(String vPath) {
		this.verticalPath = vPath;
	}

	public String getVerticalPathType() {
		return this.verticalPathType;
	}

	public void setVerticalPathType(String vPathType) {
		this.verticalPathType = vPathType;
	}

	public boolean isHasDoorNorth() {
		return this.hasDoorNorth;
	}

	public void setHasDoorNorth(boolean hDoorNorth) {
		this.hasDoorNorth = hDoorNorth;
	}

	public boolean isHasDoorEast() {
		return this.hasDoorEast;
	}

	public void setHasDoorEast(boolean hDoorEast) {
		this.hasDoorEast = hDoorEast;
	}

	public boolean isHasDoorSouth() {
		return this.hasDoorSouth;
	}

	public void setHasDoorSouth(boolean hDoorSouth) {
		this.hasDoorSouth = hDoorSouth;
	}

	public boolean isHasDoorWest() {
		return this.hasDoorWest;
	}

	public void setHasDoorWest(boolean hDoorWest) {
		this.hasDoorWest = hDoorWest;
	}

	public boolean isHasHealthItem() {
		return this.hasHealthItem;
	}

	public void setHasHealthItem(boolean healthItem) {
		this.hasHealthItem = healthItem;
	}

	public boolean isHasBeginnerWeapon() {
		return this.hasBeginnerWeapon;
	}

	public void setHasBeginnerWeapon(boolean beginnerWeapon) {
		this.hasBeginnerWeapon = beginnerWeapon;
	}

	public boolean isHasStrongWeapon() {
		return this.hasStrongWeapon;
	}

	public void setHasStrongWeapon(boolean strongWeapon) {
		this.hasStrongWeapon = strongWeapon;
	}
}
