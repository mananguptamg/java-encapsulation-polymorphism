import java.util.*;

abstract class Vehicle{
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public abstract double calculateRentalCost(int days);

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + type);
        System.out.println("Rental Rate: " + rentalRate);
    }
}

interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

class Car extends Vehicle implements Insurable {
    private double insuranceRate = 0.05; // 5% insurance rate

    public Car(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Car", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * insuranceRate;
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance Rate: " + (insuranceRate * 100) + "%";
    }
}

class Bike extends Vehicle {
    public Bike(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Bike", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }
}

class Truck extends Vehicle implements Insurable {
    private double insuranceRate = 0.10; // 10% insurance rate

    public Truck(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Truck", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days * 1.2; // Trucks have an extra surcharge
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * insuranceRate;
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance Rate: " + (insuranceRate * 100) + "%";
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();

        Car sedan = new Car("1234", 50);
        Bike sportsBike = new Bike("4567", 20);
        Truck freightTruck = new Truck("7890", 100);

        vehicles.add(sedan);
        vehicles.add(sportsBike);
        vehicles.add(freightTruck);

        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
            int rentalDays = 5;
            double rentalCost = vehicle.calculateRentalCost(rentalDays);
            double insuranceCost = (vehicle instanceof Insurable) ? ((Insurable) vehicle).calculateInsurance() : 0;
            System.out.println("Rental Cost for " + rentalDays + " days: " + rentalCost);
            System.out.println("Insurance Cost: " + insuranceCost);
            if (vehicle instanceof Insurable) {
                System.out.println(((Insurable) vehicle).getInsuranceDetails());
            }
            System.out.println();
        }
    }
}

//SampleOutput
//Vehicle Number: 1234
//Type: Car
//Rental Rate: 50.0
//Rental Cost for 5 days: 250.0
//Insurance Cost: 2.5
//Car Insurance Rate: 5.0%
//
//Vehicle Number: 4567
//Type: Bike
//Rental Rate: 20.0
//Rental Cost for 5 days: 100.0
//Insurance Cost: 0.0
//
//Vehicle Number: 7890
//Type: Truck
//Rental Rate: 100.0
//Rental Cost for 5 days: 600.0
//Insurance Cost: 10.0
//Truck Insurance Rate: 10.0%
