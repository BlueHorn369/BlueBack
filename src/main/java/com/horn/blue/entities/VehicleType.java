package com.horn.blue.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "VehicleType")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int vehicleTypeID;

    @Column(name = "carType", nullable = false, length = 100)
    private String carType;

}
