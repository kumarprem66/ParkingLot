package com.masai.parking;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CostStrategy {
    private Map<VehicleType, Integer> perHourRates;

    public CostStrategy(Map<VehicleType, Integer> perHourRates) {
        this.perHourRates = perHourRates;
    }

    public int getCost(VehicleType vehicleType, int durationInHours) {
        // Calculate the parking fee based on vehicle type and duration
        int rate = perHourRates.getOrDefault(vehicleType, 0);
        return rate * durationInHours;
    }
}

