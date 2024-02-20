package com.horn.blue.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.horn.blue.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer> {
    //List<Users> findByUserNameAndUserLastName(String userName, String userLastName);
    //List<Users> findByUserNameStartingWithOrUserLastNameStartingWith(String userName, String userLastName);
    Optional<Users> findByUserName(String userName);

//    @Query("SELECT u FROM Users u WHERE u.userName = :query OR u.userLastName = :query")
//    List<Users> findByUserNameOrUserLastName(@Param("query") String query);

    @Query("SELECT u FROM Users u WHERE CONCAT(u.userName, ' ', u.userLastName) LIKE %:query%")
    List<Users> findByFullName(@Param("query") String query);
}
