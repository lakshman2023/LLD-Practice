public class ParkingSpot {
    Vehicle vehicle;
    Vehicle.VehicleType vehicleType;
    int cost;

    ParkingState parkingState;

    public ParkingSpot(Vehicle.VehicleType vehicleType, int cost) {
        this.vehicleType = vehicleType;
        this.cost = cost;
        this.parkingState = ParkingState.AVAILABLE;
    }

    enum ParkingState {
        OCCUPIED,
        AVAILABLE
    }

}
