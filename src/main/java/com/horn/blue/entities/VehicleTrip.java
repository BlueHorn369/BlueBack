package com.horn.blue.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "VehicleTrip")
public class VehicleTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int tripID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiclexdriverID",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VehicleDrivers vehicleDrivers;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tripStartTime",nullable = true)
    private LocalDateTime tripStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tripStopTime", nullable = true)
    private LocalDateTime tripStopTime;

    private Boolean drivingActive;

    public Duration calculateTripDuration() {
        if (tripStartTime != null && tripStopTime != null) {
            return Duration.between(tripStartTime, tripStopTime);
        } else {
            return null;
        }
    }
}
