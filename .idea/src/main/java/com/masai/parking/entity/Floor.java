package com.masai.parking.entity;

import com.masai.parking.enu.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Floor {


    private int floorNumber;
    private List<VehicleSpace> vehicleSpaces;

    public Floor(int floorNumber, List<VehicleSpace> vehicleSpaces) {
        this.floorNumber = floorNumber;
        this.vehicleSpaces = vehicleSpaces;
    }
    public boolean parkVehicle(Vehicle vehicle) {
        for (VehicleSpace space : vehicleSpaces) {
            if (space.isAvailable() && space.getType() == vehicle.getType()) {
                vehicle.setTimestamp(LocalDateTime.now());

                space.setAvailable(false);
                space.setVehicle(vehicle);
                return true; // Vehicle parked successfully
            }
        }
        return false; // No available space for the vehicle type
    }

    public boolean hasAvailableSpace(VehicleType type) {
        for (VehicleSpace space : vehicleSpaces) {
            if (space.isAvailable() && space.getType() == type) {
                return true;
            }
        }
        return false;
    }



    public boolean removeVehicle(String registrationNumber) {
        for (VehicleSpace space : vehicleSpaces) {
            if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                space.setAvailable(true);
                space.setVehicle(null); // Remove the vehicle from the space
                return true; // Vehicle removed successfully
            }
        }
        return false; // Vehicle not found with the specified registration number
    }

    public boolean removeVehicleByToken(String tokenId) {
        for (VehicleSpace space : vehicleSpaces) {
            if (!space.isAvailable() && Objects.equals(space.getVehicle().getTokenId(), tokenId)) {
                space.setAvailable(true);
                space.setVehicle(null); // Remove the vehicle from the space
                return true; // Vehicle removed successfully
            }
        }
        return false; // Vehicle not found with the specified token ID
    }


}
