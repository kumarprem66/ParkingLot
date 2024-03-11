package com.masai.parking;

import com.masai.parking.costing.ParkingFeeCalculator;
import com.masai.parking.entity.*;
import com.masai.parking.enu.VehicleType;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {

    private static ParkingLot instance;
    private List<Floor> floors;

    private FloorManager floorManager;

    private Map<String, Vehicle> tokenVehicleMap; // Map to store token IDs and corresponding vehicles


    private ParkingLot(){

        this.floors = new ArrayList<>();
        this.floorManager = new FloorManager();
        this.tokenVehicleMap = new HashMap<>();
    }

    public void initialize(List<FloorConfiguration> configurations) {
        // Initialize the parking lot with the given floor configurations
        for (FloorConfiguration configuration : configurations) {
            floors.add(floorManager.createFloor(configuration));
        }
        System.out.println("Parking lot initialized with " + floors.size() + " floors.");
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();

        }
        return instance;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            if (floor.hasAvailableSpace(vehicle.getType())) {
                vehicle.setTokenId(UUID.randomUUID().toString());
                tokenVehicleMap.put(vehicle.getTokenId(),vehicle);
                return floor.parkVehicle(vehicle);
            }
        }
        // No available space in any floor
        return false;
    }

    public boolean removeVehicle(String registrationNumber) {
        for (Floor floor : floors) {
            if (floor.removeVehicle(registrationNumber)) {
                return true; // Vehicle removed successfully
            }
        }
        return false; // Vehicle not found in any floor
    }

    public boolean removeVehicleByToken(String tokenId) {
        for (Floor floor : floors) {
            if (floor.removeVehicleByToken(tokenId)) {
                return true; // Vehicle removed successfully
            }
        }
        return false; // Vehicle not found in any floor
    }

//    public int calculateParkingFee(ParkingFeeCalculator feeCalculator,String token,int duration) {
//        return feeCalculator.calculateParkingFee(token, duration);
//    }

    public int calculateParkingFee(ParkingFeeCalculator feeCalculator,String tokenId, int duration) {
        // Calculate parking fee and verify token ID

        Vehicle vehicle = tokenVehicleMap.get(tokenId); // Retrieve vehicle using token ID
        if (vehicle != null) {
            return feeCalculator.calculateParkingFee(vehicle.getType(), duration);
        }
        // No vehicle found with matching token ID
        return -1;

    }

    public void displayParkingLotStatus() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> Parking Lot Status: <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
//        && vehicleSpace.getFloorNumber() == floor.getFloorNumber()).toList().size()
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + " => space available : "+floor.getVehicleSpaces().stream().filter(vehicleSpace -> vehicleSpace.isAvailable()).toList().size());
            System.out.println();
            for (VehicleSpace space : floor.getVehicleSpaces()) {
                if (space.isAvailable()) {
                    System.out.println("Space " + space.getSpaceNumber() + ": Available");
                } else {
                    System.out.println("Space " + space.getSpaceNumber() + ": Occupied by " + space.getVehicle().getRegistrationNumber());
                }
            }
            System.out.println("==============================================================================");
        }
    }

    public boolean checkAvailability(int floorNumber, VehicleType vehicleType) {
        // Check the availability of vehicle spaces on a specific floor for a given vehicle type
        if (floorNumber <= 0 || floorNumber > floors.size()) {
            System.out.println("Invalid floor number.");
            return false;
        }

        Floor floor = floors.get(floorNumber - 1);
        return floor.hasAvailableSpace(vehicleType);
    }

    public boolean verifyTokenAndTimestamp(String tokenId, LocalDateTime exitTimestamp) {

        Vehicle vehicle = tokenVehicleMap.get(tokenId);
        if (vehicle != null) {
            // Get the entry timestamp of the vehicle
//            System.out.println(vehicle.getTokenId()+" ================== ");
            LocalDateTime entryTimestamp = vehicle.getTimestamp();
            // Check if the exit timestamp is after the entry timestamp
            if (exitTimestamp.isAfter(entryTimestamp)) {
                return true; // Token and timestamp are valid
            }
        }
        return false; // Token or timestamp is invalid
    }
}
