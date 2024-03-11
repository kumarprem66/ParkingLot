package com.masai.parking.costing;

import com.masai.parking.enu.VehicleType;

public interface ParkingFeeCalculator  {

     int calculateParkingFee(VehicleType type, int duration);

}
