package com.masai.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Initialize the parking lot for 2 cars
        ParkingLot parkingLot = ParkingLot.getInstance();
        List<Floor> floors = new ArrayList<>();

        Floor floor1 = new Floor(1,1);
        floor1.getVehicleSpaces().add(new VehicleSpace(1,true,VehicleType.CAR,1));

        Floor floor2 = new Floor(2,1);
        floor2.getVehicleSpaces().add(new VehicleSpace(1,true,VehicleType.CAR,2));

       floors.add(floor1);
       floors.add(floor2);
       parkingLot.initialize(floors);

        // Display parking lot status
        parkingLot.displayParkingLotStatus();
        // Enter car details with timestamp values
        LocalDateTime timestamp1 = LocalDateTime.now().minusHours(2); // Example timestamp for car 1
        LocalDateTime timestamp2 = LocalDateTime.now().minusHours(1); // Example timestamp for car 2

        Vehicle car1 = new Vehicle(VehicleType.CAR, "ABC123", "Red");
        car1.setTimestamp(timestamp1);
        parkingLot.addVehicle(car1);

        Vehicle car2 = new Vehicle(VehicleType.CAR, "XYZ456", "Blue");
        car2.setTimestamp(timestamp2);
        parkingLot.addVehicle(car2);

        // Verify token ID and timestamp for car 1
        String tokenId1 = car1.getTokenId();
        LocalDateTime exitTimestamp1 = LocalDateTime.now(); // Example exit timestamp for car 1
        int durationInHours1 = (int) Duration.between(car1.getTimestamp(), exitTimestamp1).toHours();
        boolean isTokenValid1 = parkingLot.verifyTokenAndTimestamp(tokenId1, exitTimestamp1);

        if (isTokenValid1) {
            System.out.println("Car 1 details: Registration number: " + car1.getRegistrationNumber() + ", Color: " + car1.getColor());
            int parkingFee1 = parkingLot.calculateParkingFee(tokenId1, durationInHours1);
            System.out.println("Parking fee for Car 1: ₹" + parkingFee1);
        } else {
            System.out.println("Invalid token ID or timestamp for Car 1.");
        }

        // Display parking lot status
        parkingLot.displayParkingLotStatus();
    }

}
