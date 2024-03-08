package com.horn.blue.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
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

    @ManyToOne
    @JoinColumn(name = "userID",nullable = false)
    private Users userDriverID;

    private Boolean driverActive;



}
