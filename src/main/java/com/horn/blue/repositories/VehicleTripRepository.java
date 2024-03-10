package com.horn.blue.repositories;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleTrip;
import com.horn.blue.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTripRepository extends JpaRepository<VehicleTrip, Integer> {

    List<VehicleTrip> findByVehicleDrivers_UserDriverID(Users user);
    List<VehicleTrip> findByVehicleDrivers_CarID(Vehicles vehicle);

}
