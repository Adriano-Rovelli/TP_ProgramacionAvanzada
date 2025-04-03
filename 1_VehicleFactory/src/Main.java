import VehicleFactory.VehicleFactory;
import VehicleFactory.VehicleInventory;
import VehicleFactory.Vehicles.Vehicle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Singleton Method
        VehicleInventory vehicleInventory = new VehicleInventory();

        //Factory Method
        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle car = vehicleFactory.createVehicle("Car","x2010", 2010);
        Vehicle motorcycle = vehicleFactory.createVehicle("Motorcycle","x2010", 2010);
        Vehicle truck = vehicleFactory.createVehicle("Truck","x2010", 2010);

        vehicleInventory.addVehicle(car);
        vehicleInventory.addVehicle(motorcycle);
        vehicleInventory.addVehicle(truck);

        vehicleInventory.showVehicles();
    }
}