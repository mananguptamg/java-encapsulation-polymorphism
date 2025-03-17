import java.util.*;

abstract class VehicleR{
    private String vehicleId;
    private String driverName;
    private double ratePerKm;

    public VehicleR(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void getVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId + ", Driver: " + driverName + ", Rate per km: " + ratePerKm);
    }

    public abstract double calculateFare(double distance);
}

interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

class CarR extends VehicleR{
    public CarR(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance;
    }
}

class BikeR extends VehicleR {
    public BikeR(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance * 0.9; // Discount for bikes
    }
}

class AutoR extends VehicleR {
    public AutoR(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance * 1.1; // Slight surcharge for autos
    }
}

public class RideHailingSystem {
    public static void main(String[] args) {
        List<VehicleR> vehicles = new ArrayList<>();
        vehicles.add(new CarR("CAR123", "Arun Kumar", 2.0));
        vehicles.add(new BikeR("BIKE456", "Arjun Singh", 5.0));
        vehicles.add(new AutoR("AUTO789", "Bheem Sharma", 4.0));

        double distance = 15.0; // Distance in km

        for (VehicleR v : vehicles) {
            v.getVehicleDetails();
            System.out.println("Fare for " + distance + " km: " + v.calculateFare(distance));
            System.out.println();
        }
    }
}

//SampleOutput
//Vehicle ID: CAR123, Driver: Arun Kumar, Rate per km: 2.0
//Fare for 15.0 km: 30.0
//
//Vehicle ID: BIKE456, Driver: Arjun Singh, Rate per km: 5.0
//Fare for 15.0 km: 67.5
//
//Vehicle ID: AUTO789, Driver: Bheem Sharma, Rate per km: 4.0
//Fare for 15.0 km: 66.0