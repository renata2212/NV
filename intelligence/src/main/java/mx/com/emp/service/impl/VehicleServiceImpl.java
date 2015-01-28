package mx.com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.VehicleDAO;
import mx.com.emp.dto.VehicleDTO;
import mx.com.emp.service.ServiceException;
import mx.com.emp.service.VehicleService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("vehicleService")
@Transactional(value = "txManager", rollbackFor = ServiceException.class, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class VehicleServiceImpl implements VehicleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3364503487565321493L;

	private Logger LOGGER = Logger.getLogger(VehicleServiceImpl.class);

	@Resource
	private VehicleDAO vehicleDAO;

	@Override
	@Transactional(value = "txManager", rollbackFor = { ServiceException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public VehicleDTO createOrUpdateVehicle(VehicleDTO vehicle)
			throws ServiceException {

		try {

			if (vehicle.getVehicleId() != null) {
				return vehicleDAO.updateVehicle(vehicle);
			} else {
				return vehicleDAO.saveVehicle(vehicle);
			}

		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public VehicleDTO getById(Integer vehicleId) throws ServiceException {

		try {
			return vehicleDAO.getById(vehicleId);
		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	@Transactional(value = "txManager", rollbackFor = { ServiceException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public int deleteVehicle(Integer vehicleId) throws ServiceException {

		try {
			return vehicleDAO.deleteVehicle(vehicleId);
		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<VehicleDTO> getAll() throws ServiceException {

		try {
			return vehicleDAO.getAll();
		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

}
