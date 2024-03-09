package com.masai.parking;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Vehicle {
    private VehicleType type;
    private String registrationNumber;
    private String color;
    private String tokenId;

    public Vehicle(VehicleType type, String registrationNumber, String color) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    // Getters and setters
}

