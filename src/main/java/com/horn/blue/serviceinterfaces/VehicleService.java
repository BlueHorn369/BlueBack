package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.Vehicles;

import java.util.List;

public interface VehicleService {
    void registerVehicleForUser(int userID, Vehicles vehicle);
    void updateVehicle(int userID, int vehicleID, String currentPassword, Vehicles updatedVehicle);
    List<Vehicles> getVehiclesByUserId(int userID);
    void deleteVehicle(int userID, int carID, String currentPassword);
}
