import java.sql.Timestamp;
import java.util.UUID;

public class ParkingTicket {
    String ticketId;
    Vehicle vehicle;
    ParkingSpot parkingSpot;

    public ParkingTicket(int durationInHrs, int costPerHr, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString();
        this.startTime = new Timestamp(System.currentTimeMillis());
        this.endTime = new Timestamp(startTime.getTime() + durationInHrs * 60 * 1000);
        this.totalCharge = costPerHr * durationInHrs;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    Timestamp startTime;
    Timestamp endTime;
    int totalCharge;
}
