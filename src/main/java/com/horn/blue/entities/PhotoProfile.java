package com.horn.blue.entities;

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

    @OneToOne
    @JoinColumn(name = "userID",nullable = false)
    private Users userPhotoID;
}

