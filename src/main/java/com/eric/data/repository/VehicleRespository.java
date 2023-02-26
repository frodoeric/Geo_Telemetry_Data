package com.eric.data.repository;

import com.eric.data.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRespository extends IBaseRepository<Vehicle, UUID> {
}
