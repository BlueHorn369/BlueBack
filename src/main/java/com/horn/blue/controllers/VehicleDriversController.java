package com.horn.blue.controllers;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.serviceinterfaces.VehicleDriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-drivers")
public class VehicleDriversController {

    @Autowired
    private VehicleDriversService vehicleDriversService;

    @GetMapping("/vehicles-by-user/{userID}")
    public ResponseEntity<List<VehicleDrivers>> getVehiclesByUser(@PathVariable int userID) {
        Users user = new Users();
        user.setUserID(userID);
        List<VehicleDrivers> vehicles = vehicleDriversService.getVehiclesByUser(user);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
    @GetMapping("/users/{carID}")
    public List<VehicleDrivers> getUsersByCarID(@PathVariable int carID) {
        // Aquí debes obtener el objeto Vehicles correspondiente al carID
        Vehicles car = new Vehicles();
        car.setCarID(carID);
        List<VehicleDrivers> vehicles = vehicleDriversService.getUsersByCarID(car);
        return vehicleDriversService.getUsersByCarID(car);
    }
    @DeleteMapping("/unassign/{userID}/{carID}")
    public ResponseEntity<String> unassignVehicleFromUser(@PathVariable int userID, @PathVariable int carID) {
        try {
            vehicleDriversService.unassignVehicleFromUser(userID, carID);
            return new ResponseEntity<>("Asignación eliminada correctamente.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
