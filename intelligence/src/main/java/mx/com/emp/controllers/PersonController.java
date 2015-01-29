package mx.com.emp.controllers;

import java.util.Date;
import java.util.List;

import mx.com.emp.dto.CatalogueDTO;
import mx.com.emp.dto.PersonDTO;
import mx.com.emp.dto.RejectionReasonsDTO;
import mx.com.emp.service.PersonService;
import mx.com.emp.service.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonController {

	private Logger LOGGER = Logger.getLogger(PersonController.class);

	private PersonService personService;

	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public static final String CREATE_PERSON = "/create-person";

	public static final String UPDATE_PERSON = "/update-person";

	public static final String GET_PERSON_ID = "/get-person-by-id";

	public static final String GET_ALL_PERSON = "/get-all-person";

	public static final String DELETE_PERSON = "/delete-person";

	public static final String RELATE_VEHICLE_PERSON = "/relate-vehicle-person";

	@RequestMapping(value = CREATE_PERSON, method = RequestMethod.GET)
	public String createPerson(PersonDTO person, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" -- Creando Persona --");
		}

		model.addAttribute("message", "Listo");

		person.setPersonAdditionalData("Informacion Adicional");
		person.setPersonAddress("Direccion");
		person.setPersonCompany("Empresa");
		person.setPersonDegree("Teniente");
		person.setPersonDocument("IFE");
		person.setPersonDocumentNumber("MNI5656");
		person.setPersonMobile(55215836);
		person.setPersonName("Juan");
		person.setPersonPhone(554345);
		person.setPersonSurname("Perez");
		person.setPersonTypeId(1);
		person.setPersonWorkingSection("CC");
		person.setStatusId(1);

		personService.createOrUpdatePerson(person);

		return "createVehicle";
	}

	@RequestMapping(value = UPDATE_PERSON, method = RequestMethod.GET)
	public String updatePerson(PersonDTO person, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" -- Actualizando Persona --");
		}

		model.addAttribute("message", "Actualizado");

		person.setPersonAdditionalData("Informacion Adicional Upd");
		person.setPersonAddress("Direccion Upd");
		person.setPersonCompany("Empresa Upd");
		person.setPersonDegree("Teniente Upd");
		person.setPersonDocument("IFE Upd");
		person.setPersonDocumentNumber("MNI809UH");
		person.setPersonMobile(55215836);
		person.setPersonName("Juan Upd");
		person.setPersonPhone(554345);
		person.setPersonSurname("Perez Upd");
		person.setPersonTypeId(1);
		person.setPersonWorkingSection("CC Upd");
		person.setStatusId(2);
		person.setPersonId(2);

		personService.createOrUpdatePerson(person);

		return "createVehicle";
	}

	@RequestMapping(value = GET_PERSON_ID, method = RequestMethod.GET)
	public String getPersonById(Integer personId, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Obteniendo Persona por Id --");

		}

		model.addAttribute("message", "By Id");

		personId = 2;

		PersonDTO person = personService.getById(personId);

		System.out.println(person.getPersonAdditionalData());
		System.out.println(person.getPersonAddress());
		System.out.println(person.getPersonCompany());
		System.out.println(person.getPersonDegree());
		System.out.println(person.getPersonDocumentNumber());
		System.out.println(person.getPersonName());
		System.out.println(person.getPersonSurname());
		System.out.println(person.getPersonId());

		return "createVehicle";
	}

	@RequestMapping(value = GET_ALL_PERSON, method = RequestMethod.GET)
	public String getVehicleById(ModelMap model) throws ServiceException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" -- Obtener todos los Personas --");
		}

		model.addAttribute("message", "ALL");

		List<PersonDTO> personList = personService.getAll();

		System.out.println(personList.size());

		return "createVehicle";
	}

	@RequestMapping(value = DELETE_PERSON, method = RequestMethod.GET)
	public String deletePerson(Integer personId,
			RejectionReasonsDTO rejectionReason, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" -- Eliminando Persona --");
		}

		model.addAttribute("message", "Eliminado");

		CatalogueDTO catalogue = new CatalogueDTO();
		catalogue.setCatalogueId(2);

		rejectionReason.setRejectionDate(new Date());
		rejectionReason.setRejectionDescription("Otro");
		rejectionReason.setReasonType(catalogue);

		personId = 2;

		personService.deletePerson(personId, rejectionReason);

		return "createVehicle";
	}

	@RequestMapping(value = RELATE_VEHICLE_PERSON, method = RequestMethod.GET)
	public String relateVehiclePerson(Integer personId, Integer vehicleId,
			ModelMap model) throws ServiceException {

		model.addAttribute("message", "Relacionando vehiculo con persona");

		vehicleId = 1;

		personId = 7;

		personService.relateVehiclePerson(vehicleId, personId);

		return "createVehicle";
	}

}
