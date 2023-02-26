package com.eric.data.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "vechicle")
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle implements IEntity<UUID> {

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "telemetryProfile_id")
    private UUID telemetryProfileId;

    @Column(name = "driverId")
    private UUID driverId;

    @Column(name = "numberPlate")
    private String numberPlate;
}
