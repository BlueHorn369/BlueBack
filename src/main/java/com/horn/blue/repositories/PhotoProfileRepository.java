package com.horn.blue.repositories;

import com.horn.blue.entities.PhotoProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoProfileRepository extends JpaRepository<PhotoProfile, Integer> {
    // Puedes agregar métodos específicos si es necesario
}
