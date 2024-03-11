package com.masai.parking;

import com.masai.parking.costing.HourlyCostStrategy;
import com.masai.parking.costing.ParkingFeeCalculator;
import com.masai.parking.entity.FloorConfiguration;
import com.masai.parking.entity.Vehicle;
import com.masai.parking.enu.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize the parking lot
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Initialize the parking lot with some floors
        List<FloorConfiguration> configurations = new ArrayList<>();
        configurations.add(new FloorConfiguration(10,VehicleType.CAR));
        configurations.add(new FloorConfiguration(20,VehicleType.BIKE));
        configurations.add(new FloorConfiguration(5,VehicleType.TRUCK));
        parkingLot.initialize(configurations);

        parkingLot.displayParkingLotStatus();
        // Add some vehicles to the parking lot
        Vehicle car1 = new Vehicle( VehicleType.CAR,"CAR-001","BLACK");
        Vehicle car2 = new Vehicle( VehicleType.CAR,"CAR-002","RED");

        // Park some vehicles in the parking lot
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);


        // Verify token ID and timestamp for car 1
        String tokenId1 = car1.getTokenId();

        LocalDateTime exitTimestamp1 = LocalDateTime.now().plusHours(5); // Example exit timestamp for car 1
        int durationInHours1 = (int) Duration.between(car1.getTimestamp(), exitTimestamp1).toHours();
        boolean isTokenValid1 = parkingLot.verifyTokenAndTimestamp(tokenId1, exitTimestamp1);

        if (isTokenValid1) {
            System.out.println("Car 1 details: Registration number: " + car1.getRegistrationNumber() + ", Color: " + car1.getColor());

            ParkingFeeCalculator parkingFeeCalculator = new HourlyCostStrategy();
            int parkingFee1 = parkingLot.calculateParkingFee(parkingFeeCalculator,tokenId1, durationInHours1);
            System.out.println("Parking fee for Car 1: ₹" + parkingFee1);
        } else {
            System.out.println("Invalid token ID or timestamp for Car 1.");
        }

        // Display parking lot status
        parkingLot.displayParkingLotStatus();
    }
}

