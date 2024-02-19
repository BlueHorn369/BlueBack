package com.horn.blue.repositories;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
    // Otros métodos existentes...
    List<Vehicles> findByUserOwnerID(Users user);
    Optional<Vehicles> findByCarID(int carID);
}
