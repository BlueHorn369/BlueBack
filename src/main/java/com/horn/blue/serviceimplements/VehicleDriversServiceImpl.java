package com.horn.blue.serviceimplements;

import com.horn.blue.entities.Users;
import com.horn.blue.entities.VehicleDrivers;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.repositories.VehicleDriversRepository;
import com.horn.blue.serviceinterfaces.UserService;
import com.horn.blue.serviceinterfaces.VehicleDriversService;
import com.horn.blue.serviceinterfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDriversServiceImpl implements VehicleDriversService {

    @Autowired
    private VehicleDriversRepository vehicleDriversRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public List<VehicleDrivers> getVehiclesByUser(Users user) {
        return vehicleDriversRepository.findByUserDriverID(user);
    }

    @Override
    public List<VehicleDrivers> getUsersByCarID(Vehicles car) {
        return vehicleDriversRepository.findByCarID(car);
    }

    @Override
    public void assignVehicleToUser(int userID, int carID) {
        Users user = userService.getUserById(userID);
        Vehicles car = vehicleService.getVehicleById(carID);

        // Validar si el usuario ya tiene asignado el vehículo en la tabla VehicleDrivers
        List<VehicleDrivers> existingAssignmentsForUserAndCar = vehicleDriversRepository.findByUserDriverIDAndCarID(user, car);
        if (!existingAssignmentsForUserAndCar.isEmpty()) {
            throw new IllegalArgumentException("Este usuario ya tiene asignado este vehículo.");
        }

        // Validar si el vehículo ya está asignado a otro usuario en la tabla VehicleDrivers
        //List<VehicleDrivers> assignmentsForCar = vehicleDriversRepository.findByCarID(car);
        if (car.getUserOwnerID() != null && car.getUserOwnerID().getUserID() == (userID)) {
            throw new IllegalArgumentException("Este vehículo ya está asignado a este usuario.");
        }

        // Proceder con la asignación
        VehicleDrivers assignment = new VehicleDrivers();
        assignment.setUserDriverID(user);
        assignment.setCarID(car);
        assignment.setDriverActive(true);
        vehicleDriversRepository.save(assignment);
    }

    @Override
    public void unassignVehicleFromUser(int userID, int carID) {
        Users user = userService.getUserById(userID);
        Vehicles car = vehicleService.getVehicleById(carID);

        // Buscar la asignación existente para el usuario y el vehículo
        List<VehicleDrivers> existingAssignmentsForUserAndCar = vehicleDriversRepository.findByUserDriverIDAndCarID(user, car);

        // Verificar si existe la asignación
        if (!existingAssignmentsForUserAndCar.isEmpty()) {
            // Eliminar la asignación
            VehicleDrivers assignment = existingAssignmentsForUserAndCar.get(0); // Suponiendo que solo hay una asignación posible
            vehicleDriversRepository.delete(assignment);
        } else {
            throw new IllegalArgumentException("No se encontró ninguna asignación para el usuario y el vehículo especificados.");
        }
    }

    public VehicleDrivers getDriverById(int driverID) {
        return vehicleDriversRepository.findByVehiclexdriverID(driverID);
    }
}
