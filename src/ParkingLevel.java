import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLevel {
    int levelNum;
    Map<Vehicle.VehicleType, Integer> vehicleTypeSpots;

    public ParkingLevel(int levelNum, Map<Vehicle.VehicleType, Integer> vehicleTypeSpots, Map<Vehicle.VehicleType, Integer> vehicleTypeRates) {
        this.levelNum = levelNum;
        this.vehicleTypeSpots = vehicleTypeSpots;
        for (var entry : vehicleTypeSpots.entrySet() ) {
            for(int i=0;i< entry.getValue();i++) {
                ParkingSpot parkingSpot = new ParkingSpot(entry.getKey(), vehicleTypeRates.get(entry.getKey()));
                this.availableSpots.add(parkingSpot);
            }
        }
    }

    //ParkingSpot[] parkingSpots;
    List<ParkingSpot> availableSpots = new ArrayList<>();
    //ParkingSpot[] occupiedSpots;
}
