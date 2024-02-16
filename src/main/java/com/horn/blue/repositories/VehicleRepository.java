package com.horn.blue.repositories;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
    // Otros métodos existentes...
    List<Vehicles> findByUserOwnerID(Users user);
}
