package com.horn.blue.repositories;

import com.horn.blue.entities.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer>{
    List<VehicleType> findAll();

}
