package com.horn.blue.serviceimplements;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.repositories.UserRepository;
import com.horn.blue.repositories.VehicleRepository;
import com.horn.blue.serviceinterfaces.UserService;
import com.horn.blue.serviceinterfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
        @Autowired
        private VehicleRepository vehicleRepository;

        @Autowired
        private UserService userService;

        @Override
        public void registerVehicleForUser(int userID, Vehicles vehicle) {
        try {
            Users owner = userService.getUserById(userID);
            vehicle.setUserOwnerID(owner);
            vehicle.setCarActive(true);
            vehicleRepository.save(vehicle);

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el vehículo", e);
        }
    }

    @Override
    public void updateVehicle(int userID, int carID, Vehicles updatedVehicle) {
            
        vehicleRepository.save(updatedVehicle);

    }

    @Override
    public Vehicles getVehicleById(int vehicleId) {
        // Implementa la lógica para obtener un vehículo por su ID desde el repositorio
        return vehicleRepository.findById(vehicleId).orElse(null);
    }
    @Override
    public void saveVehicle(Vehicles vehicle) {
        // Implementa la lógica para guardar un vehículo en el repositorio
        vehicleRepository.save(vehicle);
    }
    @Override
    public List<Vehicles> getVehiclesByUserId(int userID) {
        try {

            Users owner = userService.getUserById(userID);
            return vehicleRepository.findByUserOwnerID(owner);

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de vehículos", e);
        }
    }
    @Override
    public void deleteVehicle(int userID, int carID, String currentPassword) {
        try {
            Users owner = userService.getUserById(userID);
            if (currentPassword.equals(owner.getUserPassword())) {
                Vehicles existingVehicle = vehicleRepository.findById(carID)
                        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
                vehicleRepository.delete(existingVehicle);
            } else {
                throw new RuntimeException("La contraseña actual no es válida");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el vehículo", e);
        }
    }



}