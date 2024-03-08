package com.horn.blue.repositories;

import com.horn.blue.entities.VehicleTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTripRepository extends JpaRepository<VehicleTrip, Integer> {



}
