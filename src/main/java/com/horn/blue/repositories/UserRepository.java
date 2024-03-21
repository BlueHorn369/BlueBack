package com.horn.blue.repositories;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.horn.blue.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserName(String userName);

    @Query("SELECT u FROM Users u WHERE LOWER(CONCAT(u.userName, ' ', u.userLastName)) LIKE LOWER(concat('%', :query, '%'))")
    List<Users> findByFullName(@Param("query") String query);

//    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.photoProfile WHERE u.userID = :userId")
//    Optional<Users> findByIdWithPhotoProfile(@Param("userId") int userId);
}
