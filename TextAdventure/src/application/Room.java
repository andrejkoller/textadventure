
public class Room {

	// Attribute

	private String verticalPath = "up"; // Optionen: up, down
	private String verticalPathType = "ladder"; // Optionen: staircase, ladder
	private int roomID;
	private boolean hasDoorNorth = true;
	private boolean hasDoorEast = true;
	private boolean hasDoorSouth = true;
	private boolean hasDoorWest = true;
	private boolean hasHealthItem = true;
	private boolean hasBeginnerWeapon = true;
	private boolean hasStrongWeapon = true;

	// Konstruktormethode ID

	public Room(int i) {
		this.roomID = i;
	}

	// Konstruktormethode um Raum mit Eigenschaften zu erstellen

	public Room(int id, String verticalPath, String verticalPathType, boolean doorNorth, boolean doorEast, boolean doorSouth, boolean doorWest) {
		this.roomID = id;
		this.verticalPath = verticalPath;
		this.verticalPathType = verticalPathType;
		this.hasDoorNorth = doorNorth;
		this.hasDoorEast = doorEast;
		this.hasDoorSouth = doorSouth;
		this.hasDoorWest = doorWest;
	}

	// Methoden um Infos zu drucken

	public String currentFloor(Player player) {
		String floor;
		
		if(player.getPositionXYZ(2) == 2) {
			floor = "attic";
		}else if(player.getPositionXYZ(2) == 1) {
			floor = "ground floor";
		}else {
			floor = "cellar";
		}
		
		return floor;
	}
	
	public void lookAround(Room playerlocation, Player player) {

		System.out.println("room ID: " + getRoomID() + "\nfloor: " + currentFloor(player));

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

		if (playerlocation.getVerticalPath() != "none" && playerlocation.getVerticalPath() != null) {
			System.out.println("You also see a  " + playerlocation.getVerticalPathType() + " that leads "
					+ playerlocation.getVerticalPath() + ".");
		}

		if (playerlocation.isHasHealthItem() == true) {
			System.out.println(
					"\nWhile searching the room you found a first aid kit. \nYou store the kit in your Inventory");
			player.pickupHealthItem(playerlocation);
		}

	}

	// Getter und Setter Methoden

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

	public boolean isHasDoorNorth() {
		return hasDoorNorth;
	}

	public void setHasDoorNorth(boolean hasDoorNorth) {
		this.hasDoorNorth = hasDoorNorth;
	}

	public boolean isHasDoorEast() {
		return hasDoorEast;
	}

	public void setHasDoorEast(boolean hasDoorEast) {
		this.hasDoorEast = hasDoorEast;
	}

	public boolean isHasDoorSouth() {
		return hasDoorSouth;
	}

	public void setHasDoorSouth(boolean hasDoorSouth) {
		this.hasDoorSouth = hasDoorSouth;
	}

	public boolean isHasDoorWest() {
		return hasDoorWest;
	}

	public void setHasDoorWest(boolean hasDoorWest) {
		this.hasDoorWest = hasDoorWest;
	}

	public boolean isHasHealthItem() {
		return hasHealthItem;
	}

	public void setHasHealthItem(boolean hasHealthItem) {
		this.hasHealthItem = hasHealthItem;
	}

	public boolean isHasBeginnerWeapon() {
		return hasBeginnerWeapon;
	}

	public void setHasBeginnerWeapon(boolean hasBeginnerWeapon) {
		this.hasBeginnerWeapon = hasBeginnerWeapon;
	}

	public boolean isHasStrongWeapon() {
		return hasStrongWeapon;
	}

	public void setHasStrongWeapon(boolean hasStrongWeapon) {
		this.hasStrongWeapon = hasStrongWeapon;
	}

}