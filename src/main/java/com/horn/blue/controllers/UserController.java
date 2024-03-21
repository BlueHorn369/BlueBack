package com.horn.blue.controllers;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.serviceinterfaces.UserService;
import com.horn.blue.serviceinterfaces.VehicleDriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VehicleDriversService vehicleDriversService;


    @PostMapping("/register")
    public ResponseEntity <Users> registerUser(@RequestBody Users user) {
        Users littleuser = userService.registerUser(user);
        return new ResponseEntity<>(littleuser, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity < List<Users> > listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    //Rencientemente agregado
    @GetMapping("/list/{userId}")
    public Users getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/search")
    public List<Users> searchUsers(@RequestParam String query) {
    return userService.findByFullName(query);
    }

    @PutMapping("/update/{userId}")
    public void updateUserData(@PathVariable int userId, @RequestBody Users updatedUser) {
        userService.updateUserData(userId, updatedUser);
        //response entity pa todos los controllers
        // no se usa void dice
    }

    @PutMapping("/update/{userId}/updateEmail")
    public ResponseEntity<String> updateEmail(
            @PathVariable int userId,
            @RequestParam String newEmail,
            @RequestParam String currentPassword) {

        try {
            // Obtén el usuario existente
            Users existingUser = userService.getUserById(userId);

            // Verifica que la contraseña proporcionada coincida con la contraseña actual
            if (currentPassword.equals(existingUser.getUserPassword())) {
                // Actualiza el correo electrónico
                existingUser.setUserEmail(newEmail);

                // Guarda los cambios en la base de datos
                userService.saveUser(existingUser);

                return new ResponseEntity<>("Correo electrónico actualizado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La contraseña actual no es válida", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el correo electrónico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{userId}/changePassword")
    public ResponseEntity<String> changePassword(
            @PathVariable int userId,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        try {
            Users existingUser = userService.getUserById(userId);

            if (currentPassword.equals(existingUser.getUserPassword())) {
                existingUser.setUserPassword(newPassword);
                userService.saveUser(existingUser);

                return new ResponseEntity<>("Contraseña cambiada correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La contraseña actual no es válida", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Error al cambiar la contraseña", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userID}/assign-vehicle/{carID}")
    public ResponseEntity<String> assignVehicleToUser(@PathVariable Integer userID, @PathVariable Integer carID) {
        try {
            vehicleDriversService.assignVehicleToUser(userID, carID);
            return ResponseEntity.ok("Asignación exitosa");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}