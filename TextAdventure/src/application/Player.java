import java.util.Scanner;

public class Player {
	// X Koordinate (Horizontal) max 3
	// Y Koordinate (Horizontal) max 2
	// Z Koordinate (Vertikal) max 2 ---> 0 = Keller ; 1 = Erdgeschoss; 2 = Dachboden

	private int[] positionXYZ = { 0, 0, 0 }; // max Parameters 3,2,2 //Spawn Parameters Cords 3,2,0
	private int health = 1;
	private String name = "";
	private int healthItemsInInventory = 0;
	private String equippedWeapon = "None";

	// Konstruktor
	Player(String name) {
		this.setName(name);
	}

	// Funktionen die nicht Getter oder Setter sind
	public void printPlayerHealth() {
		System.out.println("Lifepoints: " + getHealth());
	}

	public void printPlayerInventory() {
		System.out.println("Your Inventory:");

		if (healthItemsInInventory > 0) {
			System.out.println("First Aid Kit: " + healthItemsInInventory + "x");
		} else {
			System.out.println("empty\n");
		}

	}

	public void move(Scanner sc, Room playerlocation) {
		boolean loop = true;
		while (loop == true) {
			System.out.println("\nwhich way do you choose? \npossible options: \nnorth, east, south, west, up, down or cancel");
			String direction = sc.nextLine();

			switch (direction) {

			case "north":
				if (getPositionXYZ(1) > 0 && playerlocation.isHasDoorNorth() == true) {
					setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1) - 1, getPositionXYZ(2));
					loop = false;
				} else {
					System.out.println("theres no door you're able to go through!!!");
				}
				break;

			case "east":
				if (getPositionXYZ(0) < 3 && playerlocation.isHasDoorEast() == true) {
					setPositionXYZ(getPositionXYZ(0) + 1, getPositionXYZ(1), getPositionXYZ(2));
					loop = false;
				} else {
					System.out.println("theres no door you're able to go through!!!");
				}
				break;

			case "south":
				if (getPositionXYZ(1) < 2 && playerlocation.isHasDoorEast() == true) {
					setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1) + 1, getPositionXYZ(2));
					loop = false;
				} else {
					System.out.println("theres no door you're able to go through!!!");
				}
				break;

			case "west":
				if (getPositionXYZ(0) > 0 && playerlocation.isHasDoorWest() == true) {
					setPositionXYZ(getPositionXYZ(0) - 1, getPositionXYZ(1), getPositionXYZ(2));
					loop = false;
				} else {
					System.out.println("theres no door you're able to go through!!!");
				}
				break;

			case "up":
				if (playerlocation.getVerticalPath() == "up" && playerlocation.getVerticalPathType() == "staircase"
						|| playerlocation.getVerticalPath() == "up"&& playerlocation.getVerticalPathType() == "ladder") {
					setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1), getPositionXYZ(2) + 1);
					loop = false;
				} else {
					System.out.println("Theres no way up. Guess I'll try something else...");
				}
				break;

			case "down":
				if (playerlocation.getVerticalPath() == "down" && playerlocation.getVerticalPathType() == "ladder"
						|| playerlocation.getVerticalPath() == "down" && playerlocation.getVerticalPathType() == "ladder") {
					setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1), getPositionXYZ(2) - 1);
					loop = false;
				} else {
					System.out.println("Theres no way down. Guess I'll try something else...");
				}
			case "cancel":
				loop = false;
				break;

			default:
				System.out.println("Invalid");
				break;
			}
		}

	}

	public void attack() {

	}

	public void pickupHealthItem(Room playerlocation) {
		if (playerlocation.isHasHealthItem() == true) {
			healthItemsInInventory += 1;
			playerlocation.setHasHealthItem(false);
		}
	}

	public void useHealthItem() {
		if (health + 50 >= 100) {
			health = 100;
		} else {
			health = health + 50;
		}
		System.out.println("You feel better.\nhealth: " + health);
		healthItemsInInventory -= 1;
	}

	// Getter und Setter Methoden

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPositionXYZ(int i) {
		return positionXYZ[i];
	}

	public void setPositionXYZ(int x, int y, int z) {
		this.positionXYZ[0] = x;
		this.positionXYZ[1] = y;
		this.positionXYZ[2] = z;
	}

	public int getHealthItemsInInventory() {
		return healthItemsInInventory;
	}

	public void setHealthItemsInInventory(int healthItemsInInventory) {
		this.healthItemsInInventory = healthItemsInInventory;
	}

	public String getEquippedWeapon() {
		return equippedWeapon;
	}

	public void setEquippedWeapon(String equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}

}