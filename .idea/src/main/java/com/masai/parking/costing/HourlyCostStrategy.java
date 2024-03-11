package com.masai.parking.costing;

import com.masai.parking.enu.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class HourlyCostStrategy implements ParkingFeeCalculator{

    private Map<VehicleType, Integer> hourlyRates;
    public HourlyCostStrategy() {
        hourlyRates = new HashMap<>();
        // Initialize hourly rates for different vehicle types
        hourlyRates.put(VehicleType.CAR, 30);
        hourlyRates.put(VehicleType.BIKE, 20);
        hourlyRates.put(VehicleType.TRUCK, 50);
        // we can add more vehicle types and their corresponding rates as needed
    }

    public int calculateParkingFee(VehicleType type, int hours) {
        // Default rate of 0 if type not found
        int hourlyRate = hourlyRates.getOrDefault(type, 0);
        // Calculate the parking fee based on the hourly rate and duration
        return hourlyRate * hours ;
    }
}
