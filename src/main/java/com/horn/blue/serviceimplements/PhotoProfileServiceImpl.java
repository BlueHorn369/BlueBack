package com.horn.blue.serviceimplements;

import com.horn.blue.entities.PhotoProfile;
import com.horn.blue.entities.PhotoVehicle;
import com.horn.blue.entities.Users;
import com.horn.blue.repositories.PhotoProfileRepository;
import com.horn.blue.serviceinterfaces.PhotoProfileService;
import com.horn.blue.serviceinterfaces.UserService;
import com.microsoft.azure.storage.blob.BlobProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import javax.transaction.Transactional;

@Service
public class PhotoProfileServiceImpl implements PhotoProfileService {

    @Autowired
    private PhotoProfileRepository photoProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void uploadPhotoProfile(int userID, MultipartFile file) {

            Users user = userService.getUserById(userID);

            if (photoProfileRepository.existsByUserPhotoID(user)) {
                throw new IllegalArgumentException("Ya existe una foto de perfil para el usuario con ID: " + userID);
            }
            PhotoProfile photoProfile = new PhotoProfile();
            photoProfile.setUserPhotoID(user);
            photoProfile.setPhotoName(file.getOriginalFilename());

            String imageUrl = uploadImageToCloud(file);
            photoProfile.setPhotoUrl(imageUrl);

            photoProfileRepository.save(photoProfile);
    }

    private String uploadImageToCloud(MultipartFile file) {
        try {
            // Recupera las credenciales de Azure Storage desde la configuración
            String accountName = "bluehornrepositoryv2";
            String accountKey = "P/GTAixsGUzjfSoqqmdCEfXyusXF8UResxCrR5qvmFONCfyY4j039a5MXw5wUBZSoS+053/CdbXu+AStqiBrmA==";
            String containerName = "imagesprofile";

            String storageConnectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net", accountName, accountKey);

            // Crea la instancia del cliente de almacenamiento de blobs de Azure
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            // Obtiene la referencia al contenedor
            CloudBlobContainer container = blobClient.getContainerReference(containerName);

            // Crea un nombre único para el blob usando el nombre original del archivo
            String blobName = java.util.UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Obtiene la referencia al blob en el contenedor
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);

            // Sube el archivo al blob
            blob.upload(file.getInputStream(), file.getSize());

            BlobProperties properties = blob.getProperties();
            properties.setContentDisposition("inline");
            blob.uploadProperties();

            // Devuelve la URL del blob recién cargado
            return blob.getUri().toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la imagen en Azure Storage", e);
        }

    }

    @Override
    public void updatePhotoProfile(int photoID, MultipartFile file) {
        try {
            // Obtén la entidad PhotoVehicle existente
            PhotoProfile photoProfile = photoProfileRepository.findById(photoID)
                    .orElseThrow(() -> new RuntimeException("Foto del vehículo no encontrada"));

            // Lógica para cargar la nueva imagen en la nube y obtener la URL
            String imageUrl = uploadImageToCloud(file);

            // Actualiza la información de la foto del vehículo
            photoProfile.setPhotoName(file.getOriginalFilename());
            photoProfile.setPhotoUrl(imageUrl);

            // Guarda la entidad actualizada en la base de datos
            photoProfileRepository.save(photoProfile);

        } catch (Exception e) {
            // Manejo de excepciones si es necesario
            throw new RuntimeException("Error al actualizar la foto del vehículo", e);
        }
    }

}


