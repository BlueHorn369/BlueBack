package com.horn.blue.controllers;

import com.horn.blue.serviceinterfaces.PhotoProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo-profiles")
public class PhotoProfileController {
    @Autowired
    private PhotoProfileService photoProfileService;

    @PostMapping("/upload/{userID}")
    public ResponseEntity<String> uploadPhotoProfile(@PathVariable int userID, @RequestParam("file") MultipartFile file) {
        try {
            photoProfileService.uploadPhotoProfile(userID, file);
            return ResponseEntity.ok("Foto cargada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar la foto de perfil: " + e.getMessage());
        }
    }
}
