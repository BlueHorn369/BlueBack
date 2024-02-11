package com.horn.blue.entities;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table (name = "PhotoProfile")
public class PhotoProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fotoID;

    @Column(name = "photourl", nullable = true)
    private String photourl;

    @OneToOne
    @JoinColumn(name = "userID",nullable = false)
    private Users userPhotoID;
}

