package com.masai.parking.entity;

import com.masai.parking.enu.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloorConfiguration {
    private int numSpaces;
    private VehicleType spaceType;

    public FloorConfiguration(int numSpaces, VehicleType spaceType) {
        this.numSpaces = numSpaces;
        this.spaceType = spaceType;
    }

    // Getters for numSpaces and spaceType
}

