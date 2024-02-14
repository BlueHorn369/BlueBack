package com.horn.blue.controllers;

import com.horn.blue.entities.Users;
import com.horn.blue.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity <Users> registerUser(@RequestBody Users user) {
        Users littleuser = userService.registerUser(user);
        return new ResponseEntity<>(littleuser, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity < List<Users> > listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Users> searchUsers(@RequestParam String userName, @RequestParam String userLastName) {
        return userService.searchUsersByNameAndLastName(userName, userLastName);
    }

    @PutMapping("/update/{userId}")
    public void updateUserData(@PathVariable int userId, @RequestBody Users updatedUser) {
        userService.updateUserData(userId, updatedUser);
        //response entity pa todos los controllers
        // no se usa void dice

    }
//    @PutMapping("/update/{userName}/email-password")
//    public void updateEmailAndPassword(
//            @PathVariable String userName,
//            @RequestParam String newEmail,
//            @RequestParam String newPassword
//    ) {
//        userService.updateEmailAndPassword(userName, newEmail, newPassword);
//    }

    @PutMapping("/update/{userId}/updateEmailAndPassword")
    public ResponseEntity<String> updateEmailAndPassword(
            @PathVariable int userId,
            @RequestParam String newEmail,
            @RequestParam String newPassword) {

        try {
            // Obtén el usuario existente
            Users existingUser = userService.getUserById(userId);

            // Actualiza el correo electrónico y la contraseña
            existingUser.setUserEmail(newEmail);
            existingUser.setUserPassword(newPassword);

            // Guarda los cambios en la base de datos
            userService.saveUser(existingUser);

            return new ResponseEntity<>("Usuario actualizado correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}