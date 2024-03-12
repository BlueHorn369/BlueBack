package com.horn.blue.serviceinterfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface PhotoProfileService {

    void uploadPhotoProfile(int userID, MultipartFile file);
    void updatePhotoProfile(int photoID, MultipartFile file);


    // Puedes agregar otros métodos según sea necesario
}