package com.masai.parking.entity;

import com.masai.parking.enu.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleSpace {
    private int spaceNumber;
    private boolean available;
    private VehicleType type;
    private Vehicle vehicle;

    public VehicleSpace(int spaceNumber, boolean available, VehicleType type) {
        this.spaceNumber = spaceNumber;
        this.available = available;
        this.type = type;
    }


}

