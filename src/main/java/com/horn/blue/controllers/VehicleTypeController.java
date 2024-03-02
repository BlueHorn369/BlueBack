package com.horn.blue.controllers;

import com.horn.blue.entities.VehicleType;
import com.horn.blue.serviceinterfaces.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-types")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleType>> getAllVehicleTypes() {
        try {
            List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
            return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
