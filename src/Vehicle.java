public class Vehicle {
    public Vehicle(int vehicleId, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    int vehicleId;
    VehicleType vehicleType;
    enum VehicleType{
        CAR,
        MOTORCYCLE,
        TRUCK
    }
}
