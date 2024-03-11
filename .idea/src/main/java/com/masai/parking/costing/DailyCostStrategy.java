package com.masai.parking.costing;

import com.masai.parking.enu.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class DailyCostStrategy implements ParkingFeeCalculator {
    private Map<VehicleType, Integer> dailyRates;

    public DailyCostStrategy() {
        dailyRates = new HashMap<>();
        // Initialize daily rates for different vehicle types
        dailyRates.put(VehicleType.CAR, 300);
        dailyRates.put(VehicleType.BIKE, 200);
        dailyRates.put(VehicleType.TRUCK, 500);
        // we can add more vehicle types and their corresponding rates as needed
    }

    public int calculateParkingFee(VehicleType type, int days) {
        // Default rate of 0 if type not found

        int dailyRate = dailyRates.getOrDefault(type, 0);
        // Calculate the parking fee based on the daily rate and duration
        return dailyRate * days ;
    }
}

