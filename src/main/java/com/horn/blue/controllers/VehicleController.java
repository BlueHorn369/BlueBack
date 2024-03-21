package com.horn.blue.controllers;

//import org.springframework.web.bind.annotation.RequestMapping;
import com.horn.blue.entities.Users;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.serviceinterfaces.VehicleDriversService;
import com.horn.blue.serviceinterfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register/{userID}")
    public ResponseEntity<String> registerVehicleForUser(
            @PathVariable int userID,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestBody Vehicles vehicle) {

        try {
            vehicleService.registerVehicleForUser(userID, vehicle, imageFile);
            return new ResponseEntity<>("Vehículo registrado correctamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/update/{userID}/{carID}")
    public ResponseEntity<String> updateVehicle(
            @PathVariable int userID,
            @PathVariable int carID,
            @RequestBody Vehicles updatedVehicle) {

        try {
            Vehicles existingVehicle = vehicleService.getVehicleById(carID);

            if (existingVehicle.getUserOwnerID().getUserID() != userID) {
                return new ResponseEntity<>("No tienes permisos para actualizar este vehículo", HttpStatus.UNAUTHORIZED);
            }
            existingVehicle.setCarBrand(updatedVehicle.getCarBrand());
            existingVehicle.setCarPlate(updatedVehicle.getCarPlate());
            existingVehicle.setCarActive(updatedVehicle.getCarActive());

            vehicleService.saveVehicle(existingVehicle);

            return new ResponseEntity<>("Vehículo actualizado correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/list/{userID}")
    public ResponseEntity<List<Vehicles>> getVehiclesByUserId(@PathVariable int userID) {
        try {
            List<Vehicles> vehicles = vehicleService.getVehiclesByUserId(userID);
            return new ResponseEntity<>(vehicles, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listall/{carID}")
    public Vehicles getVehicleById(@PathVariable int carID) {
        return vehicleService.getVehicleById(carID);
    }

    @DeleteMapping("/delete/{userID}/{carID}")
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