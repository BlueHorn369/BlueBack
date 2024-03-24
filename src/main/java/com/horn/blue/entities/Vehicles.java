package com.horn.blue.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Data
@Entity
@Table (name = "Vehicles")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int carID;

    @ManyToOne
    @JoinColumn(name = "userID",nullable = false)
    private Users userOwnerID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicleTypeID",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VehicleType vehicleTypeID;

    @Column(name = "carName", nullable = false, length = 100)
    private String carName;

    @Column(name = "carPlate", nullable = false, length = 10)
    private String carPlate;

    @Column(name = "carBrand", nullable = false, length = 100)
    private String carBrand;

    private Boolean carActive;

}
