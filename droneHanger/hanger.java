package droneHanger;
import java.util.ArrayList;

public class hanger
{
    private ArrayList<drone> inventory;
    //constructor initializes the array list
    public hanger()
    {
        inventory = new ArrayList<drone>();
    }

    public void loadDroneFromCSV(String fileName)
    {
        

    }

    public void searchDroneInventory(String manufacturer, String type)
    {
        boolean found = false;
        for(drone d : inventory)
        {
            if(d.getManufactor().equalsIgnoreCase(manufacturer) &&
                    ((type.equalsIgnoreCase("Standard") && d.getType() == 's') ||
                            type.equalsIgnoreCase("Piority") && d.getType() == 'P'))
            {
                System.out.println(d);
                found = true;
            }
            if(!found)
            {
                System.out.println("no drone available at the time");
            }
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
}
