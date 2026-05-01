abstract class drone
{
    private String manufactor;
    private char type;
    private int year;
    private double cargoCapacity;
    private String id;

    public drone(String id, char type, String manufactor, int year,double cargoCapacity)
    {
        this.id = id;
        this.manufactor = manufactor;
        this.type = type;
        this.year = year;
        this.cargoCapacity = cargoCapacity;
    }

    public char getType() {
        return type;
    }
    public String getManufactor(){
        return manufactor;
    }
    public int getYear()
    {
        return year;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }
    public String getID() { return id; }

    private static int count = 1000;
    public static String addID()
    {
        count++;
        return "D" + count;
    }

}
class priorityDrone extends drone {

    public priorityDrone(String id,String manufactor, char type, int year, double cargoCapacity) {
        super(id,type, manufactor, year, cargoCapacity);
    }
}
class normalDrone extends drone
{
    public normalDrone(String id,String manufactor, char type, int year, double cargoCapacity)
    {
        super(id,type, manufactor, year, cargoCapacity);
    }
}
