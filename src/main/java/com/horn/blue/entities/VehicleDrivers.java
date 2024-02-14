package com.horn.blue.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
//@IdClass(VehicleDriversId.class)

@Table (name = "VehicleDrivers")
public class VehicleDrivers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int vehiclexdriverID;

    @ManyToOne
    @JoinColumn(name = "carID",nullable = false)
    private Vehicles carID;

    @OneToOne
    @JoinColumn(name = "userID",nullable = false, unique = true)
    private Users userDriverID;

    private Boolean driverActive;

}
