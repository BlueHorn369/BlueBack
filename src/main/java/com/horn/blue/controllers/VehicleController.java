package com.horn.blue.controllers;

//import org.springframework.web.bind.annotation.RequestMapping;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.serviceinterfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register/{userId}")
    public ResponseEntity<String> registerVehicleForUser(
            @PathVariable int userId,
            @RequestBody Vehicles vehicle) {

        try {
            vehicleService.registerVehicleForUser(userId, vehicle);
            return new ResponseEntity<>("Vehículo registrado correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{userId}/{vehicleId}")
    public ResponseEntity<String> updateVehicle(
            @PathVariable int userId,
            @PathVariable int vehicleId,
            @RequestParam String currentPassword,
            @RequestBody Vehicles updatedVehicle) {

        try {
            vehicleService.updateVehicle(userId, vehicleId, currentPassword, updatedVehicle);
            return new ResponseEntity<>("Datos del vehículo actualizados correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar los datos del vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Vehicles>> getVehiclesByUserId(@PathVariable int userId) {
        try {
            List<Vehicles> vehicles = vehicleService.getVehiclesByUserId(userId);
            return new ResponseEntity<>(vehicles, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{userId}/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable int userID,
            @PathVariable int carID,
            @RequestParam String currentPassword) {

        try {
            vehicleService.deleteVehicle(userID, carID, currentPassword);
            return new ResponseEntity<>("Vehículo eliminado correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}