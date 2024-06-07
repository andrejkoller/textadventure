package application;

public class Player {
	private int[] positionXYZ = { 3, 2, 0 };
	private int health = 100;
	private String name = "";

	Player(String name) {
		this.setName(name);
	}

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

	public void printPlayerstats() {
		System.out.println("Playername: " + getName());
		System.out.println("Lifepoints: " + getHealth());
	}
}
