package com.horn.blue.serviceimplements;

import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.VehicleTrip;
import com.horn.blue.repositories.VehicleDriversRepository;
import com.horn.blue.repositories.VehicleTripRepository;
import com.horn.blue.serviceinterfaces.VehicleTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleTripServiceImpl implements VehicleTripService {

    @Autowired
    private VehicleTripRepository tripRepository;
    @Autowired
    private VehicleDriversRepository driversRepository;

    @Override
    public VehicleTrip startTrip(VehicleTrip trip, int driverID) {
        VehicleDrivers driver = driversRepository.findById(driverID).orElse(null);
        if (driver != null) {
            trip.setVehicleDrivers(driver);
            trip.setTripStartTime(LocalDateTime.now());
            trip.setDrivingActive(true);
            return tripRepository.save(trip);
        } else {
            // Manejar el caso cuando no se encuentra el conductor
            return null;
        }
    }

    @Override
    public void stopTrip(int tripID) {
        Optional<VehicleTrip> opTrip= tripRepository.findById(tripID);
        if(opTrip.isPresent()) {
            VehicleTrip tripUpdate = opTrip.get();
       tripUpdate.setTripStopTime(LocalDateTime.now());

        tripRepository.save(tripUpdate);

            //return tripUpdate;
        }

        //return null;
    }

    @Override
    public List<VehicleTrip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public VehicleTrip getTripById(int tripID) {
        return tripRepository.findById(tripID).orElse(null);
    }

    @Override
    public List<VehicleTrip> getTripsByDriverId(int driverID) {
        return null;
    }


}