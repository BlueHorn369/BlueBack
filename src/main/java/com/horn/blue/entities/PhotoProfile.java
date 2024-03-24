package com.horn.blue.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users userPhotoID;
}

