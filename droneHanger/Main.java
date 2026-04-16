package droneHanger;
import java.util.Scanner; 

public class Main {
	public static void main(String[] args) {
	hanger Hangar = new hanger();
	Scanner scanner = new Scanner(System.in);
	int menuChoice = 0; 
	
	do {
		System.out.println("===== Drone Hangar Menu ====="); 
		System.out.println("1. Load drones From CSV file");
		System.out.println("2. Display hangar inventory.");
		System.out.println("3. Display drone count by manufactuer.");
		System.out.println("4. View inventory sorted by pay load capacity.");
		System.out.println("5. View inventory sorted by manufacture year.");
		System.out.println("6. Drone Search");
		System.out.println("7. Exit");
		System.out.println("Please make a selection. You can enter 1-7 to progress... "); 
		try {
			menuChoice = Integer.parseInt(scanner.nextLine());
		}
		catch (NumberFormatException e){
			System.out.println("Please select a valid input...");
			continue; 
		}
		if (menuChoice == 1) {
			System.out.println("Please enter a valid CSV file name...");
			String filename = scanner.nextLine();
			Hangar.loadDroneFromCSV(filename);
		}
		else if(menuChoice == 2) {
				Hangar.displayInventory();
			}
		else if(menuChoice == 3) {
			System.out.println("Enter a manufacture name...");
			String manufacture = scanner.nextLine();
			Hangar.countByManufacturer();
		}
		else if(menuChoice == 4) {
			Hangar.displayInventory();
		}
		else if(menuChoice == 5) {
			Hangar.sortByYear();
		}
		else if(menuChoice == 6) {
			System.out.println("Enter drone manufacturer... ");
			String manufacturer = scanner.nextLine(); 
			System.out.println("Enter drone type, Standard or Priority...");
			String type = scanner.nextLine(); 
			Hangar.searchDroneInventory(manufacturer, type);
		}
		else if(menuChoice == 7) {
			System.out.println("Exiting program, safe skies!");
		}
		}
	while (menuChoice != 7); 
}
}
