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

//    @Override
//    public void updateEmailAndPassword(String userName, String newEmail, String newPassword) {
//        Optional<Users> existingUser = userRepository.findByUserName(userName);
//        existingUser.ifPresent(user -> {
//            user.setUserName(newEmail);
//            user.setUserPassword(newPassword);
//            userRepository.save(user);
//        });
//    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateEmailAndPassword(int userId, String newEmail, String newPassword) {
        try {
            Users existingUser = getUserById(userId);
            existingUser.setUserEmail(newEmail);
            existingUser.setUserPassword(newPassword);
            saveUser(existingUser);
        } catch (Exception e) {
            // Manejo de excepciones si es necesario
            throw new RuntimeException("Error al actualizar el usuario", e);
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