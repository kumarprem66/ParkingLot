package com.masai.parking;


import java.util.*;
import java.util.function.Predicate;

public class ParkingLot {
    private List<Floor> floors;
    private Map<String, Vehicle> tokenVehicleMap; // Map to store token IDs and corresponding vehicles

    private static ParkingLot instance;

    private ParkingLot() {


    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();

        }
        return instance;
    }

//    public void initialize(int numFloors, int numSpacesPerFloor) {

            // Initialize the parking lot with the given number of floors and spaces per floor
//            for (int i = 0; i < numFloors; i++) {
//                List<VehicleSpace> vehicleSpaces = new ArrayList<>();
//                for (int j = 0; j < numSpacesPerFloor; j++) {
//                    vehicleSpaces.add(new VehicleSpace(j + 1, true,VehicleType.CAR));
//                }
//                floors.add(new Floor(i + 1, numSpacesPerFloor, vehicleSpaces));
//            }
//            System.out.println("Parking lot initialized with " + numFloors + " floors and " + numSpacesPerFloor + " spaces per floor.");



//    }

    public void initialize(List<Floor> floors) {
        this.floors = floors;
        tokenVehicleMap = new HashMap<>();
        System.out.println("Parking lot initialized with " + floors.size() + " floors.");
    }


    public void addVehicle(Vehicle vehicle) {
        // Add a vehicle to the parking lot
        boolean added = false;
        for (Floor floor : floors) {
            if (floor.checkAvailability(vehicle.getType())) {
                for (VehicleSpace space : floor.getVehicleSpaces()) {
                    if (space.isAvailable() && vehicle.getType() == space.getVehicleType()) {
                        space.setAvailability(false);
                        space.setVehicle(vehicle);
                        String tokenId = UUID.randomUUID().toString(); // Generate a unique token ID
                        vehicle.setTokenId(tokenId);
                        tokenVehicleMap.put(tokenId, vehicle); // Store token ID and corresponding vehicle
                        added = true;
                        System.out.println("Vehicle with registration number " + vehicle.getRegistrationNumber() + " parked on floor " + floor.getFloorNumber() + ", space " + space.getSpaceNumber() + " with token ID " + vehicle.getTokenId());
                        break;
                    }
                }


            }
            if (added) {
                break;
            }
        }
        if (!added) {
            System.out.println("No available space to park the vehicle.");
        }
    }


    public void removeVehicle(String registrationNumber) {
        // Remove a vehicle from the parking lot based on registration number
        boolean removed = false;
        for (Floor floor : floors) {
            for (VehicleSpace space : floor.getVehicleSpaces()) {
                if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    space.setAvailability(true);
//                    space.setVehicleType(null);
                    System.out.println("Vehicle with registration number " + registrationNumber + " removed from floor " + floor.getFloorNumber() + ", space " + space.getSpaceNumber());
                    removed = true;
                    break;
                }
            }
            if (removed) {
                break;
            }
        }
        if (!removed) {
            System.out.println("Vehicle with registration number " + registrationNumber + " not found in the parking lot.");
        }
    }

    public boolean checkAvailability(int floorNumber, VehicleType vehicleType) {
        // Check the availability of vehicle spaces on a specific floor for a given vehicle type
        if (floorNumber <= 0 || floorNumber > floors.size()) {
            System.out.println("Invalid floor number.");
            return false;
        }

        Floor floor = floors.get(floorNumber - 1);
        return floor.checkAvailability(vehicleType);
    }

//    This method iterates through each floor of the parking lot and displays the availability
//    status of each space. It prints "Available" if the space is available and "Occupied by
//    [registration number]" if the space is occupied.
    public void displayParkingLotStatus() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> Parking Lot Status: <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + " => space available : "+floor.getVehicleSpaces().stream().filter(vehicleSpace -> vehicleSpace.isAvailability() && vehicleSpace.getFloorNumber() == floor.getFloorNumber()).toList().size());
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

    public int calculateParkingFee(String tokenId, int durationInHours) {
        // Calculate parking fee and verify token ID

        Vehicle vehicle = tokenVehicleMap.get(tokenId); // Retrieve vehicle using token ID
        if (vehicle != null) {
            CostStrategy costStrategy = new CostStrategy(getPerHourRates());
            return costStrategy.getCost(vehicle.getType(), durationInHours);
        }
        // No vehicle found with matching token ID
        return -1;

    }

    private Map<VehicleType, Integer> getPerHourRates() {

        Map<VehicleType,Integer> rateOfVehicle = new HashMap<>();
        rateOfVehicle.put(VehicleType.BUS,30);
        rateOfVehicle.put(VehicleType.TRUCK,30);
        rateOfVehicle.put(VehicleType.BIKE,10);
        rateOfVehicle.put(VehicleType.CAR,20);
        rateOfVehicle.put(VehicleType.ZEEP,20);
        return rateOfVehicle;
    }

    public String getTokenIdForVehicle(String registrationNumber) {
        // Retrieve the token ID for a given vehicle registration number
        for (Vehicle vehicle : tokenVehicleMap.values()) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                return vehicle.getTokenId();
            }
        }
        return null; // Vehicle not found
    }



}
