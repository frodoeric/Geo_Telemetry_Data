package com.eric.data.model;

import jakarta.persistence.*;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "data")
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TGData implements IEntity<UUID> {

    @Id
    @Column(name = "data_id")
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
}
