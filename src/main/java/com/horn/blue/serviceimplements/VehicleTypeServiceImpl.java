package com.horn.blue.serviceimplements;

import com.horn.blue.entities.VehicleType;
import com.horn.blue.repositories.VehicleTypeRepository;
import com.horn.blue.serviceinterfaces.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }
}
