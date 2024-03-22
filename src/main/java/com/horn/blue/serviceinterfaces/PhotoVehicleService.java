package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.PhotoProfile;
import com.horn.blue.entities.PhotoVehicle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoVehicleService {
    void uploadVehicleImage(int carID, MultipartFile file);

    void updateVehicleImage(int photoID, MultipartFile file);

    List<PhotoVehicle> getPhotoVehiclesByCarId(int carId);
}
