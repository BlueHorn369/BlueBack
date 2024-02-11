package com.horn.blue.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.horn.blue.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    List<Users> findByUserNameAndUserLastName(String userName, String userLastName);

    Optional<Users> findByUserName(String userName);
}
