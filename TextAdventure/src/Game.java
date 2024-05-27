import java.util.Scanner;

public class Game {

	public void Run() {
		//Scanner erstellen um Userinput zuregistrieren
		Scanner sc = new Scanner(System.in);
		Room[][][] house = new Room[4][3][3];
				
		
		int counter = 1;
		
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3 ; j++) {
				for(int k = 0; k < 3; k++) {
					house[i][j][k] = new Room(counter, "up" , "ladder");
					
					if(k == 0) {
						
						System.out.println("Untergeschoss Raum X: " + i + " Y: " + j + " wurde befüllt. Raum ID:  " + house[i][j][k].getRoomID());
					}
					else if(k == 1) {
						
						System.out.println("Erdgeschoss Raum X: " + i + " Y: " + j + " wurde befüllt. Raum ID:  " + house[i][j][k].getRoomID());
					}
					else {
						System.out.println("Dachboden Raum X: " + i + " Y: " + j + " wurde befüllt. Raum ID:  " + house[i][j][k].getRoomID());
					}
					
					counter++;
				}
			}
			
		}
			 
			System.out.println("You wake up in a dark, moist room. \n'What was my name?' you ask yourself:");
			Player player = new Player(sc.nextLine());
			
			Room playerlocation = house[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)];
			
			System.out.println(playerlocation.getRoomID());
			
		
		
		sc.close();
		
	}
	
	
	
	
}
