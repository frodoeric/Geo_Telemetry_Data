package com.eric.data.mapper;

import com.eric.data.model.Vehicle;
import com.eric.data.openapi.model.*;
import com.eric.data.service.impl.VehicleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;

import static java.util.Objects.nonNull;

@Mapper(componentModel = "spring")
public interface MapperDtoEntity {
	
	@Named("ConvertUUIDToString")
	default String convertUUIDToString(UUID id) {
		return id.toString();
	}
	
	@Named("convertDTOArrayToCollection")
	default Set<Vehicle> convertDTOArrayToCollection(List<String> ids) {
		return nonNull(ids)?VehicleService.convertSetIdsToListVehicles(ids):new HashSet<>();
	}
	
	default ListVehiclesResponseDto convertVehicleCollectionToListDTO(Collection<Vehicle> entities) {
		ListVehiclesResponseDto responseDto = new ListVehiclesResponseDto();
		entities.forEach(vehicle -> responseDto.addContentItem(mapEntityToDto(vehicle)));
		responseDto.setTotalResults((long)entities.size());
		return responseDto;
	}
	
	
	// DTO to Entity
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "sensorType", target = "type", qualifiedByName = "ConvertEnumSensorTypeDtoToSensorType")
	Vehicle mapDtoToEntity(CreateVehicleRequestDto dto);
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "sensorType", target = "type", qualifiedByName = "ConvertEnumSensorTypeDtoToSensorType")
	Vehicle mapDtoToEntity(UpdateVehicleRequestDto dto);


	// Entity to DTO
	@Mapping(source = "type", target = "sensorType", qualifiedByName = "ConvertSensorTypeToSensorEnumSensorTypeDto")
	@Mapping(source = "id", target = "sensorId", qualifiedByName = "ConvertUUIDToString")
	GetVehicleResponseDto mapEntityToDto(Vehicle entity);
	@Mapping(source = "id", target = "sensorId", qualifiedByName = "ConvertUUIDToString")
	CreateVehicleResponseDto mapEntityToCreateResponseDto(Vehicle entity);

}