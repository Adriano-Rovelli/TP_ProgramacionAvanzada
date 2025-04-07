package VehicleFactory.Vehicles;

public class Vehicle implements VehicleInterface {
    private String type, model;
    private int year;

    public Vehicle(String type, String model, int year) {
        this.type = type;
        this.model = model;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String showVehicle() {
        return "Type: " + type + ", Model: " + model + ", Year: " + year;

    }
}
