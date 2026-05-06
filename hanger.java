
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class hanger
{
    Scanner scanner = new Scanner(System.in);
    private ArrayList<drone> inventory;
    ArrayList<drone> droneQueue = new ArrayList<>();
    //constructor initializes the array list
    public hanger()
    {
        inventory = new ArrayList<drone>();
    }

    public void loadDroneFromCSV(String fileName)
    {
        try {                                          //try catch will hand errors gracefully, in this case incorrect files/ files not found won't crash the system
            Scanner fileScanner = new Scanner(new File(fileName));
            int lineNumber = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineNumber++;
                String[] fields = line.split(","); // splits the drone info into fields to ensure good data is being parsed through (all 4 fields are there)
                if (fields.length != 4) {         // check 1, checks field count
                    System.out.println("ERROR: Incorrect drone information. Expected 4 fields at " + lineNumber + ": " + line);
                    continue; //skips invalid line and will restart loop moving onto next line to check validity
                }

                char type = fields[0].replace("\uFEFF"," ").trim().toUpperCase().charAt(0);//in case of \uFEFF from showing up: I added the .replace to remove the u\uFEFF and replace it with a blank space so the trim can remove it and replace with something P or S

                if (type != 'S'&& type != 'P') { // check 2: check if the type is S
                    System.out.println("ERROR: Invalid drone type at " + lineNumber + ": " + line + ". Please enter Standard(S) or Priority(P)");
                    continue;
                }
                int year;  //check 3, checks valid year and payload capacity
                double payload;
                try {
                    year = Integer.parseInt(fields[2].trim()); //trim ignores spacing around the entered value
                    payload = Double.parseDouble(fields[3].trim());  //handles 10 and 10.00
                }
                catch(NumberFormatException e) {
                    System.out.println("ERROR: Invalid number at line " + lineNumber + " : " + line + ". Please enter valid year and/or pay load capacity. ");
                    continue;
                }
                String id = drone.addID();
                //creates new drone object from the parsed fields to check for duplicates
                drone newDrone = (type == 'P')
                        ? new priorityDrone(id,fields[1].trim(),type,year,payload)
                        : new normalDrone(id,fields[1].trim(),type,year,payload);

                //this will check parsed drones and compare to known drones for duplicates
                boolean isDuplicate = false;
                for (drone drone : inventory) {
                    if (drone.toString().equals(newDrone.toString())) {
                        isDuplicate = true;
                        break; //no need to continue checking if valid
                    }
                }
                if (isDuplicate) {
                    System.out.println("ERROR: Duplicate drone: " + newDrone + " not added. Please enter a valid drone.");
                }
                else {
                    inventory.add(newDrone);
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: File " + fileName + " not found. Please enter a valid file name. ");
        }
        

    }

    public void searchDroneInventory(String manufacturer, String type)
    {
        boolean found = false;
        for(drone d : inventory)
        {
            if(d.getManufactor().equalsIgnoreCase(manufacturer) &&
                    ((type.equalsIgnoreCase("Standard") && d.getType() == 'S') ||
                            type.equalsIgnoreCase("Priority") && d.getType() == 'P'))
            {
                System.out.println(d);
                found = true;
            }
        }
        if(!found)
        {
            System.out.println("no drone available at the time");
        }
    }
    public void sortByPayLoad()
    {
        ArrayList<drone> sorted = new ArrayList<drone>();
        for(int i = 0; i < sorted.size()-1; i++)
        {
            int minIndex = i;
            for(int j = i+1; j < sorted.size(); j++)
            {
                minIndex = j;
            }
            drone d1 = sorted.get(minIndex);
            sorted.set(i, sorted.get(minIndex));
            sorted.set(minIndex, d1);
        }
        System.out.println("Inventory has been sorted by payload capacity: ");
        for (drone drone: inventory)
        {
            System.out.println(drone);
        }
    }
    public void sortByYear()
    {
        ArrayList<drone> sorted = new ArrayList<drone>(inventory);
        for (int i = 0; i < sorted.size()-1; i++)
        {
            int minIndex = i;
            for (int j = i+1; j < sorted.size(); j++)
            {
                minIndex = j;
            }
            drone d1 = sorted.get(minIndex);
            sorted.set(minIndex, sorted.get(minIndex));
            sorted.set(minIndex, d1);
        }
        System.out.println("Inventory has been sorted by year: ");
        for (drone drone: inventory)
        {
            System.out.println(drone);
        }
    }
    public void countByManufacturer(String manufacturer) {
        int droneCounter = 0;
        for (drone drone : inventory) {
            if (drone.getManufactor().equalsIgnoreCase(manufacturer)){
                droneCounter++;
            }
        }
        System.out.println("Total drone(s) from manufacturer " + manufacturer + " : " + droneCounter);
    }
    public void displayInventory() {
        if (inventory.isEmpty()) {   //checks to see if the array list 'hangar' holds any information
            System.out.println("The hangar is empty.");
        }
        else {
            for (drone drone : inventory) {
                System.out.println(drone.displayDrone());
            }
        }
    }
    public void searchDroneID(String search)
    {
        HashMap<String,drone> droneSearch = new HashMap<String, drone>();
        boolean found = false;
        for (drone d : inventory)
        {
            droneSearch.put(d.getID(), d);
        }
        for (drone d : droneSearch.values()) {
            if (d.getID().equals(search)) {
                System.out.println(d.displayDrone());
                found = true;
            }
        }
        if(!found)
        {
            System.out.println("ID not found with "+search+".");
        }
    }
    public void addMaintenanceDrone()
    {
        while(true)
        {
            System.out.println("Please enter a drone ID to fix:");
            String droneID = scanner.nextLine();
            Boolean found = false;
            for (drone d : inventory)
            {
                if (d.getID().equals(droneID))
                {
                    droneQueue.add(d);
                    found = true;
                }
            }
            if(!found)
            {
                System.out.println("ID not found with "+droneID+".");
            }
            System.out.println("are you done with adding the drone that need to fix(y/n).");
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("y")||choice.equalsIgnoreCase("yes"))
            {
                break;
            }
        }
    }

    public void removeMaintenanceDrone()
    {
        while(true)
        {
            boolean found = false;
            System.out.println("Please enter a drone ID to remove that you have fixed:");
            String droneID = scanner.nextLine();
            for(drone d : inventory)
            {
                if (d.getID().equals(droneID))
                {
                    droneQueue.remove(d);
                    found = true;
                }
            }
            if(!found)
            {
                System.out.println("ID not found with "+droneID+".");
            }
            System.out.print("are done removing drone that is fixed(y/n)?");
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("y")||choice.equalsIgnoreCase("yes"))
            {
                break;
            }
        }
    }
    public void displayMaintenanceDrone()
    {
        for(drone d : droneQueue)
        {
            System.out.println(d.displayDrone());
        }
    }
}
