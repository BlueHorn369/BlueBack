package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.PhotoProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PhotoProfileService {

    void uploadPhotoProfile(int userID, MultipartFile file);
    void updatePhotoProfile(int photoID, MultipartFile file);

    List<PhotoProfile> getPhotoProfilesByUserId(int userId);

    // Puedes agregar otros métodos según sea necesario
}