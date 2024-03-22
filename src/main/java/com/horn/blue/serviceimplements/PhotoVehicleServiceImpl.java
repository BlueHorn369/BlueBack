package com.horn.blue.serviceimplements;

import com.horn.blue.entities.PhotoVehicle;
import com.horn.blue.entities.Vehicles;
import com.horn.blue.repositories.PhotoVehicleRepository;
import com.horn.blue.repositories.VehicleRepository;
import com.horn.blue.serviceinterfaces.PhotoVehicleService;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobProperties;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class PhotoVehicleServiceImpl implements PhotoVehicleService {
    @Autowired
    private PhotoVehicleRepository photoVehicleRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void uploadVehicleImage(int carID, MultipartFile file) {

            Vehicles vehicle = vehicleRepository.findById(carID)
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        if (photoVehicleRepository.existsByPhotoVehicleID(vehicle)) {
            throw new IllegalArgumentException("Ya existe una foto para el vehiculo con ID: " + carID);
        }

            PhotoVehicle photoVehicle = new PhotoVehicle();
            photoVehicle.setPhotoVehicleID(vehicle);
            photoVehicle.setPhotoName(file.getOriginalFilename());

            // Lógica para cargar la imagen en la nube y obtener la URL
            String imageUrl = uploadImageToCloud(file);
            photoVehicle.setPhotoUrl(imageUrl);

            // Guarda la entidad PhotoVehicle en la base de datos
            photoVehicleRepository.save(photoVehicle);

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

    @Override
    public List<PhotoVehicle> getPhotoVehiclesByCarId(int carId) {

        return photoVehicleRepository.findByPhotoVehicleIDCarID(carId);
    }

    // Método simulado para cargar la imagen en la nube y obtener la URL
    private String uploadImageToCloud(MultipartFile file) {

        try {
            String accountName = "bluehornrepositoryv2";
            String accountKey = "P/GTAixsGUzjfSoqqmdCEfXyusXF8UResxCrR5qvmFONCfyY4j039a5MXw5wUBZSoS+053/CdbXu+AStqiBrmA==";
            String containerName2 = "imagesvehicle";

            String storageConnectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net", accountName, accountKey);

            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference(containerName2);

            String blobName = java.util.UUID.randomUUID() + "_" + file.getOriginalFilename();

            CloudBlockBlob blob = container.getBlockBlobReference(blobName);

            blob.upload(file.getInputStream(), file.getSize());

            BlobProperties properties = blob.getProperties();
            properties.setContentDisposition("inline");
            blob.uploadProperties();

            return blob.getUri().toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la imagen en Azure Storage", e);
        }
    }
}

