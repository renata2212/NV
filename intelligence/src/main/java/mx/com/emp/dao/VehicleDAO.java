package mx.com.emp.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.emp.dto.VehicleDTO;

public interface VehicleDAO extends Serializable {

	VehicleDTO saveVehicle(VehicleDTO vehicle) throws DaoException;

	VehicleDTO updateVehicle(VehicleDTO vehicle) throws DaoException;

	VehicleDTO getById(Integer vehicleId) throws DaoException;

	int deleteVehicle(Integer vehicleId) throws DaoException;

	List<VehicleDTO> getAll() throws DaoException;
}
