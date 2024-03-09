package com.masai.parking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleSpace {
    private int spaceNumber;
    private boolean availability;
    private VehicleType vehicleType;
    private Vehicle vehicle;
    private Integer floorNumber;

    public VehicleSpace(int spaceNumber, boolean availability, VehicleType vehicleType,Integer floorNumber) {
        this.spaceNumber = spaceNumber;
        this.availability = availability;
        this.vehicleType = vehicleType;
        this.floorNumber = floorNumber;
        this.vehicle = null;
    }

    // Getters and setters

    public boolean isAvailable(){

        return this.availability;
    }



}

