package com.masai.parking;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {
    private int floorNumber;
    private int capacity;
    private List<VehicleSpace> vehicleSpaces;

    public Floor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        this.capacity = capacity;
        this.vehicleSpaces = new ArrayList<>(capacity);
    }

    public void addVehicleSpace(VehicleSpace space) {
        // Add a vehicle space to the floor
        vehicleSpaces.add(space);
    }

    public void removeVehicleSpace(int spaceNumber) {
        // Remove a vehicle space from the floor based on space number
        for (VehicleSpace space : vehicleSpaces) {
            if (space.getSpaceNumber() == spaceNumber) {
                vehicleSpaces.remove(space);
                break;
            }
        }
    }

    public boolean checkAvailability(VehicleType vehicleType) {
        // Check the availability of vehicle spaces for a given vehicle type
        for (VehicleSpace space : vehicleSpaces) {

            if (space.isAvailable() && space.getVehicleType() == vehicleType) {
                return true;
            }
        }
        return false;
    }

    // Getters
    public int getFloorNumber() {
        return floorNumber;
    }

    public List<VehicleSpace> getVehicleSpaces() {
        return vehicleSpaces;
    }
}

