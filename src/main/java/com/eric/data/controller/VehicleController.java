package com.eric.data.controller;

import com.eric.data.mapper.MapperDtoEntity;
import com.eric.data.model.Vehicle;
import com.eric.data.openapi.api.VehiclesApi;
import com.eric.data.openapi.model.*;
import com.eric.data.service.IVehicleService;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.eric.data.util.Constants.TRACE_ID;
import static com.eric.data.util.Constants.X_TRACE_ID;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {


    private final IVehicleService service;
    private final MapperDtoEntity mapper;

    @Override
    public ResponseEntity<CreateVehicleResponseDto> createVehicle(CreateVehicleRequestDto createVehicleRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle vehicle = mapper.mapDtoToCreatetEntity(createVehicleRequestDto);

        vehicle = service.save(vehicle);
        return new ResponseEntity<>(mapper.mapEntityToPostResponseDto(vehicle), headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteVehicle(String vehicleId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle vehicle = service.findById(UUID.fromString(vehicleId))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Vehicle not found.", vehicleId)));

        service.delete(vehicle);
        return ResponseEntity.noContent().headers(headers).build();
    }

    @Override
    public ResponseEntity<GetVehicleResponseDto> getVehicle(String vehicleId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle persistedEntity = service.findById(UUID.fromString(vehicleId))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Vehicle not found.", vehicleId)));

        return ResponseEntity.ok().headers(headers).body(mapper.mapEntityToDto(persistedEntity));
    }

    @Override
    public ResponseEntity<ListVehiclesResponseDto> listVehicles() {

        HttpHeaders headers = new HttpHeaders();

        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        ListVehiclesResponseDto responseDto = mapper.convertVehicleCollectionToListDTO(service.findAll());

        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @Override
    public ResponseEntity<Void> updateVehicle(String vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle persistedVehicle = service.findById(UUID.fromString(vehicleId))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Vehicle not found.", vehicleId)));

        Vehicle vehicle = mapper.mapDtoToUpdateEntity(updateVehicleRequestDto);

        persistedVehicle.setCustomerId(vehicle.getCustomerId());
        persistedVehicle.setTelemetryProfileId(vehicle.getTelemetryProfileId());
        persistedVehicle.setDriverId(vehicle.getDriverId());
        persistedVehicle.setNumberPlate(vehicle.getNumberPlate());
        persistedVehicle.setColor(vehicle.getColor());

        service.update(persistedVehicle);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
