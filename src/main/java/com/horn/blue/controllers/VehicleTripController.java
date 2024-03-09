package com.horn.blue.controllers;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.VehicleTrip;
import com.horn.blue.serviceinterfaces.VehicleDriversService;
import com.horn.blue.serviceinterfaces.VehicleTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-trips")
public class VehicleTripController {

    @Autowired
    private VehicleTripService tripService;
    @Autowired
    private VehicleDriversService driversService;

    @PostMapping("/start/{driverID}")
    public ResponseEntity<VehicleTrip> startTrip(@RequestBody VehicleTrip trip, @PathVariable int driverID) {
        VehicleDrivers driver = driversService.getDriverById(driverID);
        if (driver != null) {
            trip.setVehicleDrivers(driver);
            VehicleTrip startedTrip = tripService.startTrip(trip, driverID);
            return new ResponseEntity<>(startedTrip, HttpStatus.CREATED);
        } else {
            // Manejar el caso cuando no se encuentra el conductor
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/stop/{driverID}")
//    public ResponseEntity<VehicleTrip> stopTrip(@RequestBody VehicleTrip trip, @PathVariable int driverID) {
//        VehicleDrivers driver = driversService.getDriverById(driverID);
//        if (driver != null) {
//            trip.setVehicleDrivers(driver);
//            VehicleTrip stoppedTrip = tripService.stopTrip(trip, driverID);
//            return new ResponseEntity<>(stoppedTrip, HttpStatus.OK);
//        } else {
//            // Manejar el caso cuando no se encuentra el conductor
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @PutMapping("/stop/{tripID}")
    public ResponseEntity<String> stopTripData(@PathVariable int tripID) {
        tripService.stopTrip(tripID);
        return new ResponseEntity<>("Stopped", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleTrip>> getAllTrips() {
        List<VehicleTrip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }
//find by carID->list->listarzzz
    @GetMapping("/{tripID}")
    public ResponseEntity<VehicleTrip> getTripById(@PathVariable int tripID) {
        VehicleTrip trip = tripService.getTripById(tripID);
        if (trip != null) {
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-user/{userID}")
    public ResponseEntity<List<VehicleTrip>> getTripsByUserID(@PathVariable int userID) {
        List<VehicleTrip> trips = tripService.getTripsByUserID(userID);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }


}
