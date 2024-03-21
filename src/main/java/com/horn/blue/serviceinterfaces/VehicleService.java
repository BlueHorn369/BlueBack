package com.horn.blue.serviceinterfaces;

import com.horn.blue.entities.Vehicles;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    void registerVehicleForUser(int userID, Vehicles vehicle, MultipartFile imageFile);
    void updateVehicle(int userID, int carID, Vehicles updatedVehicle);
    List<Vehicles> getVehiclesByUserId(int userID);
    void deleteVehicle(int userID, int carID, String currentPassword);
    Vehicles getVehicleById(int carID);
    void saveVehicle(Vehicles vehicle);
    //Optional<Vehicles> getVehicleById(int carID);
}
