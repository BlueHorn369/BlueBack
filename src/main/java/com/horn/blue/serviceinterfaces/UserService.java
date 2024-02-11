package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.Users;
import java.util.Optional;
import java.util.List;

public interface UserService {
    void registerUser(Users user);
    List<Users> listUsers();
    List<Users> searchUsersByNameAndLastName(String userName, String userLastName);
    void updateUserData(int userId, Users updatedUser);
    void updateEmailAndPassword(String userName, String newEmail, String newPassword);
    void deleteUser(int userId);
}