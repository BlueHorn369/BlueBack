package com.horn.blue.serviceimplements;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.repositories.UserRepository;
import com.horn.blue.repositories.VehicleDriversRepository;
import com.horn.blue.repositories.VehicleRepository;
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
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleDriversRepository vehicleDriversRepository;

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
    public List<Users> findByFullName(String query) {
        return userRepository.findByFullName(query);
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
        Users user = optionalUser.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        return user;
    }

    @Override
    public void saveUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public boolean assignVehicleToUser(int userID, int carID) {
        Optional<Users> optionalUser = userRepository.findById(userID);
        Optional<Vehicles> optionalVehicle = vehicleRepository.findById(carID);

        if (optionalUser.isPresent() && optionalVehicle.isPresent()) {
            Users user = optionalUser.get();
            Vehicles vehicle = optionalVehicle.get();

            // Crear una nueva entrada en la tabla VehicleDrivers
            VehicleDrivers vehicleDriver = new VehicleDrivers();
            vehicleDriver.setUserDriverID(user);
            vehicleDriver.setCarID(vehicle);
            vehicleDriver.setDriverActive(true);

            // Guardar la relación en la tabla VehicleDrivers
            vehicleDriversRepository.save(vehicleDriver);

            return true;
        }
        return false;
    }
}