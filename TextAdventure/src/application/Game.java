import java.util.Scanner;

public class Game {

	public void GenerateWorld(Room[][][] a, int i, int j, int k, int counter) {

		// a[i][j][k] = new Room(counter, "up", "staircase", false, true, true, false);

		a[0][0][0] = new Room(1, "up", "staircase", false, true, true, false);
		a[0][2][0] = new Room(34, null, null, true, false, false, true);
		a[0][0][1] = new Room(2, "down", "staircase", false, true, true, false);
		a[3][1][1] = new Room(32, "up", "ladder", false, false, false, true);
		a[0][1][2] = new Room(6, null, null, false, true, false, false);
		a[1][1][2] = new Room(15, null, null, false, true, false, true);
		a[2][1][2] = new Room(24, null, null, false, true, false, true);
		a[3][1][2] = new Room(33, "down", "ladder", false, false, false, true);

	}

	public void Run() {
		// Scanner erstellen um Userinput zuregistrieren
		Scanner sc = new Scanner(System.in);
		Room[][][] house = new Room[4][3][3];

		int counter = 1;
		int x = 0;
		int y = 0;
		int z = 0;
		
		while (z < 3) {
			while (y < 3) {
				while (x < 4) {
					house[x][y][z] = new Room(counter);
					/*
					if (z == 0) {
						System.out.println("Untergeschoss Raum X: " + x + " Y: " + y + " wurde befüllt. Raum ID: " + house[x][y][z].getRoomID());
					} else if (z == 1) {

						System.out.println("Erdgeschoss Raum X: " + x + " Y: " + y + " wurde befüllt. Raum ID: " + house[x][y][z].getRoomID());
					} else {
						System.out.println("Dachboden Raum X: " + x + " Y: " + y + " wurde befüllt. Raum ID: " + house[x][y][z].getRoomID());
					}
					*/
					x++;
					counter++;
				}
				x = 0;
				y++;
				
			}
			y = 0;
			z++;
		}

		System.out.println("You wake up in a dark, moist room. \n'What was my name?' you ask yourself:");
		Player player = new Player(sc.nextLine());
		Room playerlocation = house[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)];

		int loop = 0;

		System.out.println("type 'help' to show commands");
		
		while (loop < 10) {
			if(player.getHealth() < 1) {
				break;
			}
			System.out.println("\nwhat do you want to do?");
			String input = sc.nextLine();

			switch (input) {

			case "help":
				System.out.println("\nType the following commands to interact with the game:");
				System.out.println("w / walk");
				System.out.println("la / look around");
				System.out.println("i / show inventory");
				System.out.println("ch / check health");
				System.out.println("heal / heal yourself");
				System.out.println("kill self / kys");
				break;

			case "walk":
			case "w":
				playerlocation = house[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)];
				player.move(sc, playerlocation);
				break;

			case "look around":
			case "la":
				playerlocation = house[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)];
				playerlocation.lookAround(playerlocation, player);
				break;

			case "show inventory":
			case "i":
				player.printPlayerInventory();
				break;
				
			case "ch":
			case "check health":
				System.out.println("Health: " + player.getHealth());
				break;
				
			case "heal":
			case "heal yourself":
				player.useHealthItem();
				break;
				
			case "kill self":
			case "kys":
				player.setHealth(0);
				break;

			default:
				System.out.println("ungültige Eingabe!");

			}

		}
		
		if(player.getHealth() < 1) {
			System.out.println("game over");
		}

		sc.close();

	}

}
