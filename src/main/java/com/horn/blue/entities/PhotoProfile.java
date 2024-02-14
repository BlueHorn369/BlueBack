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

    @Column(name = "photoName", nullable = true)
    private String photoName;

    @OneToOne
    @JoinColumn(name = "userID",nullable = false)
    private Users userPhotoID;
}

