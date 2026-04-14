package droneHanger;

public abstract class drone
{
    private String manufactor;
    private char type;
    private int year;
    private double cargoCapacity;

    public drone(String manufactor, char type, int year,double cargoCapacity)
    {
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
}
class priorityDrone extends drone {

    public priorityDrone(String manufactor, char type, int year, double cargoCapacity) {
        super(manufactor, type, year, cargoCapacity);
    }
}
class normalDrone extends drone
{
    public normalDrone(String manufactor, char type, int year, double cargoCapacity)
    {
        super(manufactor, type, year, cargoCapacity);
    }
}
