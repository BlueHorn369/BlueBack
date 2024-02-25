package com.horn.blue.serviceimplements;

import com.horn.blue.entities.PhotoVehicle;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.repositories.PhotoVehicleRepository;
import com.horn.blue.repositories.VehicleRepository;
import com.horn.blue.serviceinterfaces.PhotoVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoVehicleServiceImpl implements PhotoVehicleService {
    @Autowired
    private PhotoVehicleRepository photoVehicleRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void uploadVehicleImage(int carID, MultipartFile file) {
        try {
            // Obtén el vehículo
            Vehicles vehicle = vehicleRepository.findById(carID)
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

            // Crea una entidad PhotoVehicle
            PhotoVehicle photoVehicle = new PhotoVehicle();
            photoVehicle.setPhotoVehicleID(vehicle);
            photoVehicle.setPhotoName(file.getOriginalFilename());

            // Lógica para cargar la imagen en la nube y obtener la URL
            String imageUrl = uploadImageToCloud(file);
            photoVehicle.setPhotoUrl(imageUrl);

            // Guarda la entidad PhotoVehicle en la base de datos
            photoVehicleRepository.save(photoVehicle);

        } catch (Exception e) {
            // Manejo de excepciones si es necesario
            throw new RuntimeException("Error al cargar la foto del vehículo", e);
        }
    }
    @Override
    public void updateVehicleImage(int photoID, MultipartFile file) {
        try {
            // Obtén la entidad PhotoVehicle existente
            PhotoVehicle photoVehicle = photoVehicleRepository.findById(photoID)
                    .orElseThrow(() -> new RuntimeException("Foto del vehículo no encontrada"));

            // Lógica para cargar la nueva imagen en la nube y obtener la URL
            String imageUrl = uploadImageToCloud(file);

            // Actualiza la información de la foto del vehículo
            photoVehicle.setPhotoName(file.getOriginalFilename());
            photoVehicle.setPhotoUrl(imageUrl);

            // Guarda la entidad actualizada en la base de datos
            photoVehicleRepository.save(photoVehicle);

        } catch (Exception e) {
            // Manejo de excepciones si es necesario
            throw new RuntimeException("Error al actualizar la foto del vehículo", e);
        }
    }

    // Método simulado para cargar la imagen en la nube y obtener la URL
    private String uploadImageToCloud(MultipartFile file) {
        // Lógica para cargar la imagen en la nube (usar SDK del servicio de almacenamiento en la nube)
        // Devolver la URL de la imagen en la nube
        return "https://example.com/cloud-storage/" + file.getOriginalFilename();
    }
}

