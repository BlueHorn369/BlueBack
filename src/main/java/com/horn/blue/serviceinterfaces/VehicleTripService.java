package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.VehicleTrip;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.repositories.VehicleTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VehicleTripService {
    VehicleTrip startTrip(int driverID);

    void stopTrip(int tripID);

    List<VehicleTrip> getAllTrips();

    VehicleTrip getTripById(int tripID);

    List<VehicleTrip> getTripsByDriverId(int driverID);

    List<VehicleTrip> getTripsByUserID(int userID);
    List<VehicleTrip> getTripsByCarID(int carID);

    List<Vehicles> getVehiclesWithDrivingActive();

}
