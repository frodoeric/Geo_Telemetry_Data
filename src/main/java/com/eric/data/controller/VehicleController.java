package com.eric.data.controller;

import com.eric.data.mapper.MapperDtoEntity;
import com.eric.data.openapi.api.VehiclesApi;
import com.eric.data.openapi.model.*;
import com.eric.data.service.IVehicleService;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.eric.data.util.Constants.TRACE_ID;
import static com.eric.data.util.Constants.X_TRACE_ID;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {


    private final IVehicleService service;
    private final MapperDtoEntity mapper;

    @Override
    public ResponseEntity<CreateVehicleResponseDto> createVehicle(CreateVehicleRequestDto createVehicleRequestDto) {
        return VehiclesApi.super.createVehicle(createVehicleRequestDto);
    }

    @Override
    public ResponseEntity<Void> deleteVehicle(String vehicleId) {
        return VehiclesApi.super.deleteVehicle(vehicleId);
    }

    @Override
    public ResponseEntity<GetVehicleResponseDto> getVehicle(String vehicleId) {
        return VehiclesApi.super.getVehicle(vehicleId);
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
        return VehiclesApi.super.updateVehicle(vehicleId, updateVehicleRequestDto);
    }
}
