package com.horn.blue.serviceinterfaces;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoVehicleService {
    void uploadVehicleImage(int carID, MultipartFile file);

    void updateVehicleImage(int photoID, MultipartFile file);
}
