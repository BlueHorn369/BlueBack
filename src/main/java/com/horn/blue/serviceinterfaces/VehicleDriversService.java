package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.Vehicles;

import java.util.List;

public interface VehicleDriversService {
    List<VehicleDrivers> getVehiclesByUser(Users user);
    List<VehicleDrivers> getUsersByCarID(Vehicles car);
    void unassignVehicleFromUser(int userID, int carID);
    void assignVehicleToUser(int userID, int carID);
    VehicleDrivers getDriverById(int findByDriverID);

}
