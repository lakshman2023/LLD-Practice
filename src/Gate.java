import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

public class Gate {
    int gateNum;

    public Gate(int gateNum, GateType gateType) {
        this.gateNum = gateNum;
        this.gateType = gateType;
    }

    GateType gateType;
    enum GateType{
        ENTRY,
        EXIT
    }
    public synchronized ParkingTicket park(Vehicle vehicle, ParkingSpot parkingSpot, int durationInHrs, int costPerHr) {
        if(parkingSpot.vehicleType == vehicle.vehicleType && parkingSpot.parkingState == ParkingSpot.ParkingState.AVAILABLE) {
            parkingSpot.vehicle = vehicle;
            parkingSpot.parkingState = ParkingSpot.ParkingState.OCCUPIED;
            ParkingTicket parkingTicket = new ParkingTicket(durationInHrs, costPerHr, vehicle, parkingSpot);
            return parkingTicket;
        }
        return null;
    }

    public synchronized double unPark(Vehicle vehicle, ParkingTicket parkingTicket, int costPerHr) {
        ParkingSpot parkingSpot = parkingTicket.parkingSpot;
        if (parkingSpot.vehicle != null && parkingSpot.vehicle.vehicleId == vehicle.vehicleId) {
            parkingSpot.parkingState = ParkingSpot.ParkingState.AVAILABLE;
            parkingSpot.vehicle = null;
            Timestamp currTime = new Timestamp(System.currentTimeMillis());
            double lateFee = 0;
            if(currTime.after(parkingTicket.endTime)){
                lateFee = ((currTime.getTime() - parkingTicket.endTime.getTime()) * 1.0/(1000*60*60)) * costPerHr;
            }
            return parkingTicket.totalCharge + lateFee;
        }
        return 0;
    }
}
