package com.eric.data.mapper;

import static java.util.Objects.nonNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.eric.data.model.Vehicle;
import com.eric.data.openapi.model.*;
import com.eric.data.service.impl.VehicleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


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
//	@Mapping(target = "id", ignore = true)
//	@Mapping(source = "vehicles", target = "vehicles", qualifiedByName = "convertDTOArrayToCollection")
//	Vehicle mapDtoToEntity(CreateVehicleRequestDto dto);
//	@Mapping(target = "id", ignore = true)
//	@Mapping(source = "vehicles", target = "vehicles", qualifiedByName = "convertDTOArrayToCollection")
//	Vehicle mapDtoToEntity(UpdateVehicleRequestDto dto);


	// Entity to DTO
	@Mapping(source = "id", target = "vehicleId", qualifiedByName = "ConvertUUIDToString")
	GetVehicleResponseDto mapEntityToDto(Vehicle entity);
//	@Mapping(source = "id", target = "vehicleId", qualifiedByName = "ConvertUUIDToString")
//	CreateVehicleResponseDto mapEntityToCreateResponseDto(Vehicle entity);

}