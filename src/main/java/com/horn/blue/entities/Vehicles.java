package com.horn.blue.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Data
@AllArgsConstructor
@Entity

@Table (name = "Vehicles")
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int carID;

    @ManyToOne
    @JoinColumn(name = "userID",nullable = false)
    private Users userOwnerID;

    @Column(name = "carPlate", nullable = false, length = 10)
    private String carPlate;

    @Column(name = "carBrand", nullable = false, length = 100)
    private String carBrand;

    private Boolean carActive;
}
