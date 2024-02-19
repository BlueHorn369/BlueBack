package com.horn.blue.serviceimplements;

import com.horn.blue.entities.PhotoProfile;
import com.horn.blue.entities.Users;
import com.horn.blue.repositories.PhotoProfileRepository;
import com.horn.blue.serviceinterfaces.PhotoProfileService;
import com.horn.blue.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoProfileServiceImpl implements PhotoProfileService {

    @Autowired
    private PhotoProfileRepository photoProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    public void uploadPhotoProfile(int userID, MultipartFile file) {
        try {
            // Obtén el usuario
            Users user = userService.getUserById(userID);

            // Crea una entidad PhotoProfile
            PhotoProfile photoProfile = new PhotoProfile();
            photoProfile.setUserPhotoID(user);
            photoProfile.setPhotoName(file.getOriginalFilename());

            // Lógica para cargar la imagen en la nube y obtener la URL
            String imageUrl = uploadImageToCloud(file);
            photoProfile.setPhotoUrl(imageUrl);

            // Guarda la entidad PhotoProfile en la base de datos
            photoProfileRepository.save(photoProfile);

        } catch (Exception e) {
            // Manejo de excepciones si es necesario
            throw new RuntimeException("Error al cargar la foto de perfil", e);
        }
    }

    // Método simulado para cargar la imagen en la nube y obtener la URL

    private String uploadImageToCloud(MultipartFile file) {
        return "https://example.com/cloud-storage/" + file.getOriginalFilename();
    }
}



