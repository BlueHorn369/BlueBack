package com.horn.blue.entities;

import lombok.Data;
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

    @ManyToOne
    @JoinColumn(name = "vehicleTypeID",nullable = false)
    private VehicleType vehicleTypeID;

    @Column(name = "carName", nullable = false, length = 100)
    private String carName;

    @Column(name = "carPlate", nullable = false, length = 10)
    private String carPlate;

    @Column(name = "carBrand", nullable = false, length = 100)
    private String carBrand;

    private Boolean carActive;

}
