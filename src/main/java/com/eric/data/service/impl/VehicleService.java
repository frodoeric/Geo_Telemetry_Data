package com.eric.data.service.impl;

import com.eric.data.model.Vehicle;
import com.eric.data.repository.VehicleRepository;
import com.eric.data.service.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class VehicleService extends BaseEntityService<UUID, Vehicle, VehicleRepository> implements IVehicleService {

	public VehicleService(VehicleRepository repository) {
		super(repository);
	}
	
	public static Set<Vehicle> convertSetIdsToListVehicles(Collection<String> listIds) {

		Set<Vehicle> vehicles = new HashSet<>();

		listIds.forEach(id->{
			Vehicle vehicle = new Vehicle();
			vehicle.setId(UUID.fromString(id));
			vehicles.add(vehicle);
		});

	    return vehicles;
	  }

}
