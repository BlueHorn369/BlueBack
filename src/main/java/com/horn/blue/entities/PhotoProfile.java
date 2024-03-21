package com.horn.blue.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table (name = "PhotoProfile")
public class PhotoProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoID;

    @Column(name = "photoName", nullable = false)
    private String photoName;

    @Column(name = "photoUrl", nullable = false)
    private String photoUrl; // Almacena la URL de la imagen en la nube

    @ManyToOne
    @JoinColumn(name = "userID",nullable = false)
   // @JsonManagedReference
    private Users userPhotoID;
}

