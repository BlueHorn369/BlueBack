package com.horn.blue.repositories;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDriversRepository extends JpaRepository<VehicleDrivers, Integer> {
    List<VehicleDrivers> findByUserDriverID(Users user);

    List<VehicleDrivers> findByCarID(Vehicles car);

    List<VehicleDrivers> findByUserDriverIDAndCarID(Users user, Vehicles car);
}
