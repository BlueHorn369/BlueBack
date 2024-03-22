package com.horn.blue.repositories;

import com.horn.blue.entities.PhotoProfile;
import com.horn.blue.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoProfileRepository extends JpaRepository<PhotoProfile, Integer> {
    // Puedes agregar métodos específicos si es necesario
    boolean existsByUserPhotoID(Users user);

    List<PhotoProfile> findByUserPhotoIDUserID(int userId);
}
