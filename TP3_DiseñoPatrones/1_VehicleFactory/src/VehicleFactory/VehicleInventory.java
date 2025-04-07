package VehicleFactory;

import VehicleFactory.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventory {
    private static VehicleInventory instance;
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public static VehicleInventory getInstance() {
        if (instance == null) {
            instance = new VehicleInventory();
        }
        return instance;
    }

    public void showVehicles() {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.showVehicle());
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}
