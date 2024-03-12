package com.horn.blue.controllers;

import com.horn.blue.serviceinterfaces.PhotoVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo-vehicles")
public class PhotoVehicleController {

    @Autowired
    private PhotoVehicleService photoVehicleService;


    @PostMapping("/upload/{carID}")
    public ResponseEntity<String> uploadVehicleImage(
            @PathVariable int carID,
            @RequestParam("file") MultipartFile file) {

        try {
            // Lógica para cargar la foto del vehículo y actualizar la entidad PhotoVehicle
            photoVehicleService.uploadVehicleImage(carID, file);
            return new ResponseEntity<>("Foto del vehículo cargada correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar la foto del vehiculo: " + e.getMessage());
        }
    }
    @PutMapping("/update-image/{photoID}")
    public ResponseEntity<String> updateVehicleImage(
            @PathVariable int photoID,
            @RequestParam("file") MultipartFile file) {

        try {
            // Lógica para actualizar la foto del vehículo
            photoVehicleService.updateVehicleImage(photoID, file);
            return new ResponseEntity<>("Foto del vehículo actualizada correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la foto del vehículo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
