package com.masai.parking.entity;

import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private Map<String, Vehicle> tokenVehicleMap;

    public TokenManager() {
        tokenVehicleMap = new HashMap<>();
    }

    public void addToken(String tokenId, Vehicle vehicle) {
        tokenVehicleMap.put(tokenId, vehicle);
    }

    public Vehicle getVehicleByToken(String tokenId) {
        return tokenVehicleMap.get(tokenId);
    }

    // Other methods as needed
}

