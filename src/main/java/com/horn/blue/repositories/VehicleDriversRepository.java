package com.horn.blue.repositories;

import com.horn.blue.entities.VehicleDrivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDriversRepository extends JpaRepository<VehicleDrivers, Integer> {
}
