package com.horn.blue.entities;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "PhotoVehicle")
public class PhotoVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoID;

    @Column(name = "photoName", nullable = false)
    private String photoName;

    @Column(name = "photoUrl", nullable = true)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "carID", nullable = false)
    private Vehicles photoVehicleID;
}
