
public class Room {

	//X Koordinate (Horizontal) max 4
	//Y Koordinate (Horizontal) max 4
	//Z Koordinate (Vertikal) max 2 ---> 0 = Keller ; 1 = Erdgeschoss; 2 = Dachboden
	//--------------------------------------------------------------------------------------	
	//Attribute
	private String verticalPath = "up";
	private String verticalPathType = "ladder";
	private int roomID = 1; 
	
	//--------------------------------------------------------------------------------------
	
	//Konstruktormethode blank 
	public Room() {
		
	}
	
	//Konstruktormethode um Raum mit Eigenschaften zu erstellen
	public Room(int a, String b, String c) {
		this.roomID = a;
		this.verticalPath = b;
		this.verticalPathType = c;
	}
	
	//--------------------------------------------------------------------------------------
	
	//Methoden um Infos zu drucken
	public void printRaumInfos() {
		System.out.println("Room infos:");
		System.out.println(getRoomID());
		if(getVerticalPath() == "none") {
			System.out.println("Theres a " + verticalPathType + " that leads " + verticalPath + "\n");
		}
		
		
	}
	
	
	//--------------------------------------------------------------------------------------
	//Getter und Setter Methoden
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
	
	

	
	
	
	
	
	//--------------------------------------------------------------------------------------
	
	
	
	
}
