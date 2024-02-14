package com.horn.blue.serviceimplements;

import com.horn.blue.entities.Users;
import com.horn.blue.repositories.UserRepository;
import com.horn.blue.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users registerUser(Users user) {
        Users objUsers = user;
        objUsers.setRegisterDate(LocalDateTime.now());
        objUsers.setUserActive(true);
        userRepository.save(objUsers);

        return objUsers;
    }


    @Override
    public List<Users> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> searchUsersByNameAndLastName(String userName, String userLastName) {
        return userRepository.findByUserNameAndUserLastName(userName, userLastName);
    }

    @Override
    public void updateUserData(int userId, Users updatedUser) {
        Optional<Users> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            Users userToUpdate = existingUser.get();
            userToUpdate.setUserName(updatedUser.getUserName());
            userToUpdate.setUserLastName(updatedUser.getUserLastName());
            userToUpdate.setRegisterDate(updatedUser.getRegisterDate());
            userToUpdate.setUserActive(updatedUser.getUserActive());
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateEmail(int userId, String newEmail, String currentPassword) {
        try {

            Users existingUser = getUserById(userId);

            if (currentPassword.equals(existingUser.getUserPassword())) {
                existingUser.setUserEmail(newEmail);
                saveUser(existingUser);
            } else {
                throw new RuntimeException("La contraseña actual no es válida");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el correo electrónico", e);
        }
    }

    @Override
    public void changePassword(int userId, String currentPassword, String newPassword) {
        try {
            Users existingUser = getUserById(userId);

            if (currentPassword.equals(existingUser.getUserPassword())) {

                existingUser.setUserPassword(newPassword);
                saveUser(existingUser);
            } else {
                throw new RuntimeException("La contraseña actual no es válida");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar la contraseña", e);
        }
    }

    @Override
    public Users getUserById(int userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
    }

    @Override
    public void saveUser(Users user) {
        userRepository.save(user);
    }
}