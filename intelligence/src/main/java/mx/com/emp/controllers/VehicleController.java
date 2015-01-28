package mx.com.emp.controllers;

import java.util.List;

import mx.com.emp.dto.VehicleDTO;
import mx.com.emp.service.ServiceException;
import mx.com.emp.service.VehicleService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VehicleController {

	private Logger LOGGER = Logger.getLogger(VehicleController.class);

	private VehicleService vehicleService;

	@Autowired
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public static final String CREATE_VEHICLE = "/create-vehicle";

	public static final String UPDATE_VEHICLE = "/update-vehicle";

	public static final String GET_VEHICLE_ID = "/get-vehicle-by-id";

	public static final String DELETE_VEHICLE = "/delete-vehicle";

	public static final String GET_ALL_VEHICLE = "/get-all-vehicle";

	@RequestMapping(value = CREATE_VEHICLE, method = RequestMethod.GET)
	public String createVehicle(VehicleDTO vehicle, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Creando Vehiculo --");

		}

		model.addAttribute("message", "Listo");

		vehicle.setVehicleBrand("VW");
		vehicle.setVehicleColor("WHITE");
		vehicle.setVehicleLicensePlate("MNJ8723");
		vehicle.setVehicleModel("POLO");
		vehicle.setVehicleYear("2013");

		vehicleService.createOrUpdateVehicle(vehicle);

		return "createVehicle";
	}

	@RequestMapping(value = UPDATE_VEHICLE, method = RequestMethod.GET)
	public String updateVehicle(VehicleDTO vehicle, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Actualizando Vehiculo --");

		}

		model.addAttribute("message", "Actualizado");

		vehicle.setVehicleId(1);
		vehicle.setVehicleBrand("VW");
		vehicle.setVehicleColor("BLACK");
		vehicle.setVehicleLicensePlate("MNJ87");
		vehicle.setVehicleModel("POLO");
		vehicle.setVehicleYear("2014");

		vehicleService.createOrUpdateVehicle(vehicle);

		return "createVehicle";
	}

	@RequestMapping(value = GET_VEHICLE_ID, method = RequestMethod.GET)
	public String getVehicleById(Integer vehicleId, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Obteniendo Vehiculo por Id --");

		}

		model.addAttribute("message", "By Id");

		vehicleId = 1;

		VehicleDTO vehicle = vehicleService.getById(vehicleId);

		System.out.println(vehicle.getVehicleBrand());
		System.out.println(vehicle.getVehicleColor());
		System.out.println(vehicle.getVehicleLicensePlate());
		System.out.println(vehicle.getVehicleModel());
		System.out.println(vehicle.getVehicleYear());
		System.out.println(vehicle.getVehicleId());

		return "createVehicle";
	}

	@RequestMapping(value = DELETE_VEHICLE, method = RequestMethod.GET)
	public String deleteVehicle(Integer vehicleId, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Borrando Vehiculo --");

		}

		model.addAttribute("message", "Delete By Id");

		vehicleId = 1;

		int numVehicle = vehicleService.deleteVehicle(vehicleId);

		System.out.println(numVehicle);

		return "createVehicle";
	}

	@RequestMapping(value = GET_ALL_VEHICLE, method = RequestMethod.GET)
	public String getVehicleById(ModelMap model) throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Obtener todos los Vehiculos --");

		}

		model.addAttribute("message", "ALL");

		List<VehicleDTO> vehicleList = vehicleService.getAll();

		System.out.println(vehicleList.size());

		return "createVehicle";
	}

}
