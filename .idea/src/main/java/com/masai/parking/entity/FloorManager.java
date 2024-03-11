package com.masai.parking.entity;

import java.util.ArrayList;
import java.util.List;

public class FloorManager {
    public Floor createFloor(FloorConfiguration configuration) {
        List<VehicleSpace> vehicleSpaces = new ArrayList<>();
        for (int i = 0; i < configuration.getNumSpaces(); i++) {
            vehicleSpaces.add(new VehicleSpace(i + 1, true, configuration.getSpaceType()));
        }
        return new Floor(vehicleSpaces.size(), vehicleSpaces);
    }
}

