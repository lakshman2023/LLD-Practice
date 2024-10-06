import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int totalLevels = 3;
        Map<Vehicle.VehicleType, Integer> vehicleTypeRates = new HashMap<>();
        vehicleTypeRates.put(Vehicle.VehicleType.CAR, 250);
        vehicleTypeRates.put(Vehicle.VehicleType.TRUCK, 500);
        vehicleTypeRates.put(Vehicle.VehicleType.MOTORCYCLE, 100);
        List<Map<Vehicle.VehicleType, Integer>> vehicleTypeSpotsPerLevel = new ArrayList<>();
        for(int i = 0; i < totalLevels; i++) {
            vehicleTypeSpotsPerLevel.add(new HashMap<>());
            for(Vehicle.VehicleType vehicleType : Vehicle.VehicleType.values()) {
                vehicleTypeSpotsPerLevel.get(i).put(vehicleType, 3);
            }
        }
        ParkingLot parkingLot = new ParkingLot("AMB",totalLevels, vehicleTypeSpotsPerLevel, vehicleTypeRates, 2, 2);

        Vehicle car1 = new Vehicle(1, Vehicle.VehicleType.CAR);
        Vehicle car2 = new Vehicle(2, Vehicle.VehicleType.CAR);
        ParkingTicket car1parkingTicket = parkingLot.park(car1, 0, 1);
        parkingLot.printParkingSpots();
        System.out.println("Car1 Parked");
        ParkingTicket car2parkingTicket = parkingLot.park(car2, 1, 2);
        parkingLot.printParkingSpots();
        System.out.println("Car2 Parked");
        double parkingCharge = parkingLot.unPark(car1, 1, car1parkingTicket);
        parkingLot.printParkingSpots();
        System.out.println("Car1 UnParked by paying " + parkingCharge);
        //parkingLot.setParkingLevels(List.of(new ParkingLevel(1, 100)));
    }
}