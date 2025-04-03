package VehicleFactory;

import VehicleFactory.Vehicles.Car;
import VehicleFactory.Vehicles.Motorcycle;
import VehicleFactory.Vehicles.Truck;
import VehicleFactory.Vehicles.Vehicle;

public class VehicleFactory {
    public VehicleFactory() {
    }

    public Vehicle createVehicle(String type, String model, int year) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car(model, year);
            case "motorcycle":
                return new Motorcycle(model, year);
            case "truck":
                return new Truck(model, year);
            default:
                throw new IllegalArgumentException("No existe un tipo de vehiculo");
        }
    }
}
