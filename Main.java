
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
		System.out.println("7. ID Search");
		System.out.println("8. maintenance area");
		System.out.println("0. Exit");
		System.out.println("Please make a selection. You can enter 1-8 to proceed:");
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
			Hangar.countByManufacturer(manufacture);
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
		} else if (menuChoice == 7)
		{
			System.out.println("place type the ID your are looking for(example: D1010)");
			String search = scanner.nextLine();
			Hangar.searchDroneID(search);

		} else if (menuChoice == 8)
		{
			System.out.println("welcome mechanic, type 1 to add a drone to fix, or type 2 to remove fixed drone");
			String choice = scanner.nextLine();
			do
			{
				if (choice.equals("1")||choice.equalsIgnoreCase("one"))
				{
					Hangar.addMaintenanceDrone();
				}else  if (choice.equals("2")||choice.equalsIgnoreCase("two"))
				{
					Hangar.removeMaintenanceDrone();
				}
				System.out.println("are you done?");
				choice = scanner.nextLine();
			}while (choice.equalsIgnoreCase("yes"));
		}
		if(menuChoice == 0) {
			System.out.println("Exiting program, safe skies!");
		}
		}
	while (menuChoice != 0);
}
}
