package application;

public class Room {
	private int roomID = 1;
	private String verticalPath = "up";
	private String verticalPathType = "ladder";
	private boolean hasEnemies = false;

	public Room(int a, String b, String c) {
		this.roomID = a;
		this.verticalPath = b;
		this.verticalPathType = c;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int a) {
		this.roomID = a;
	}

	public String getVerticalPath() {
		return verticalPath;
	}

	public void setVerticalPath(String vertical_Path) {
		verticalPath = vertical_Path;
	}

	public String getVerticalPathType() {
		return verticalPathType;
	}

	public void setVerticalPathType(String verticalpath_type) {
		this.verticalPathType = verticalpath_type;
	}

	public boolean hasEnemies() {
		return hasEnemies;
	}

	public void spawnEnemies() {
		hasEnemies = true;
	}

	public void killEnemies() {
		hasEnemies = false;
	}
	
	public void printRoomInfo() {
		System.out.println("Room infos:");
		System.out.println(getRoomID());
		if (getVerticalPath() == "none") {
			System.out.println("Theres a " + verticalPathType + " that leads " + verticalPath + "\n");
		}
	}
}
