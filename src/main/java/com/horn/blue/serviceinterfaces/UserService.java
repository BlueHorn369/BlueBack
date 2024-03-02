package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    Users registerUser(Users user);
    List<Users> listUsers();
   //List<Users> searchUsersByNameAndLastName(String userName, String userLastName);
    void updateUserData(int userId, Users updatedUser);
    void deleteUser(int userId);
    //nuevo
    void updateEmail(int userId, String newEmail, String currentPassword);
    void changePassword(int userId, String currentPassword, String newPassword);
    Users getUserById(int userId);
    void saveUser(Users user);
    //List<Users> findByUserNameOrUserLastName(String query);
    List<Users> findByFullName(String query);

    boolean assignVehicleToUser(int userID, int carID);

}