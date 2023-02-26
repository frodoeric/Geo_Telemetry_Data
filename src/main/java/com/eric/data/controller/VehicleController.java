package com.eric.data.controller;

import com.eric.data.openapi.api.VehiclesApi;
import com.eric.data.openapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {

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

        return VehiclesApi.super.listVehicles();
    }

    @Override
    public ResponseEntity<Void> updateVehicle(String vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto) {
        return VehiclesApi.super.updateVehicle(vehicleId, updateVehicleRequestDto);
    }
}
