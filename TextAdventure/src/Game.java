import java.util.Scanner;

public class Game {

	public void Run() {
		//Scanner erstellen um Userinput zuregistrieren
		Scanner sc = new Scanner(System.in);
		Room[][][] house = new Room[4][3][3];
		
		
		System.out.println("You wake up in a dark, moist room. \n'What was my name?' you ask yourself:");
		Player player = new Player(sc.nextLine());
		
		int counter = 1;
		
		
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 3 ; j++) {
					for(int k = 0; k < 3; k++) {
						house[i][j][k] = new Room();
						System.out.println("Raum X: " + i + " Y: " + j + " Z: " + k + " wurde befüllt " + counter);
						counter++;
					}
				}
				
			}
		
		
		sc.close();
		
	}
	
	
}
