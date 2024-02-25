package com.horn.blue.repositories;

import com.horn.blue.entities.PhotoVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoVehicleRepository extends JpaRepository<PhotoVehicle, Integer> {
    // Puedes agregar métodos específicos si es necesario
}