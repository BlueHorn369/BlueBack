package com.horn.blue.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carID",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vehicles carID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users userDriverID;

    private Boolean driverActive;



}
