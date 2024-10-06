import java.util.*;

public class ParkingLot {
    int id;
    String name;
    List<Gate> gates = new ArrayList<>();
    Map<Vehicle.VehicleType, Integer> vehicleTypeRates = new HashMap<>();

    public ParkingLot(String name, int totalLevels, List<Map<Vehicle.VehicleType, Integer>> vehicleTypeSpotsPerLevel, Map<Vehicle.VehicleType, Integer> vehicleTypeRates, int entryGates, int exitGates) {
        this.name = name;
        this.totalLevels = totalLevels;
        this.vehicleTypeSpotsPerLevel = vehicleTypeSpotsPerLevel;
        this.vehicleTypeRates = vehicleTypeRates;
        for(int i = 0; i < totalLevels; i++) {
            ParkingLevel parkingLevel = new ParkingLevel(i, vehicleTypeSpotsPerLevel.get(i), vehicleTypeRates);
            this.parkingLevels.add(parkingLevel);
        }
        for(int i = 0; i < entryGates; i++) {
            this.addGate(i, Gate.GateType.ENTRY);
        }
        for(int i = 0; i < exitGates; i++) {
            this.addGate(i, Gate.GateType.EXIT);
        }
    }

    int totalLevels;
    List<Map<Vehicle.VehicleType, Integer>> vehicleTypeSpotsPerLevel;
    List<ParkingLevel> parkingLevels = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSpotAvailable(Vehicle.VehicleType vehicleType) {
        for(ParkingLevel parkingLevel : parkingLevels) {
            for(var parkingSpot : parkingLevel.availableSpots){
                if(parkingSpot.vehicleType == vehicleType) {
                    return true;
                }
            }
        }
        return false;
    }

    public Gate getGate(int gateNum, Gate.GateType gateType) throws NoSuchElementException {
        Gate gate = null;
        for(int i=0;i<gates.size();i++){
            if(gates.get(i).gateNum == gateNum && gates.get(i).gateType == gateType){
                gate = gates.get(i);
                break;
            }
        }
        if(gate == null) {
            throw new NoSuchElementException(gateType + " Gate " + gateNum + " not found");
        }
        return gate;
    }

    public ParkingTicket park(Vehicle vehicle, int gateNum, int durationInHrs) {
        Gate gate = getGate(gateNum, Gate.GateType.ENTRY);
        for(ParkingLevel parkingLevel : parkingLevels) {
            for(var parkingSpot : parkingLevel.availableSpots){
                if(parkingSpot.vehicleType == vehicle.vehicleType && parkingSpot.parkingState == ParkingSpot.ParkingState.AVAILABLE) {
                    return gate.park(vehicle, parkingSpot, durationInHrs, vehicleTypeRates.get(vehicle.vehicleType));
                }
            }
        }
        return null;
    }

    public double unPark(Vehicle vehicle, int gateNum, ParkingTicket parkingTicket) {
        Gate gate = getGate(gateNum, Gate.GateType.EXIT);
        for(ParkingLevel parkingLevel : parkingLevels) {
            for (var parkingSpot : parkingLevel.availableSpots) {
                if (parkingSpot.vehicle != null && parkingSpot.vehicle.vehicleId == vehicle.vehicleId) {
                    return gate.unPark(vehicle, parkingTicket, vehicleTypeRates.get(vehicle.vehicleType));
                }
            }
        }
        return 0;
    }

    public void printParkingSpots() {
        for(ParkingLevel parkingLevel : parkingLevels) {
            for(var parkingSpot : parkingLevel.availableSpots) {
                if(parkingSpot.parkingState == ParkingSpot.ParkingState.OCCUPIED){
                    System.out.print("+ ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void addGate(int gateNum, Gate.GateType gateType) {
        gates.add(new Gate(gateNum, gateType));
    }

    public void removeGate(Gate gate) {
        gates.remove(gate);
    }
}
