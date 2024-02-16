package com.horn.blue.serviceinterfaces;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoProfileService {

    void uploadPhotoProfile(int userID, MultipartFile file);

    // Puedes agregar otros métodos según sea necesario
}