package com.masai.parking;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class Vehicle {
    private VehicleType type;
    private String registrationNumber;
    private String color;
    private String tokenId;
    private LocalDateTime timestamp;

    public Vehicle(VehicleType type, String registrationNumber, String color) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    // Getters and setters


    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + type +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

