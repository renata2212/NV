package mx.com.emp.service;

import java.io.Serializable;
import java.util.List;

import mx.com.emp.dto.VehicleDTO;

public interface VehicleService extends Serializable {

	VehicleDTO createOrUpdateVehicle(VehicleDTO vehicle)
			throws ServiceException;

	VehicleDTO getById(Integer vehicleId) throws ServiceException;

	int deleteVehicle(Integer vehicleId) throws ServiceException;

	List<VehicleDTO> getAll() throws ServiceException;

}
