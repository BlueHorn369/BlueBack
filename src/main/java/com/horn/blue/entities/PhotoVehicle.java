package com.horn.blue.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vehicles photoVehicleID;
}
