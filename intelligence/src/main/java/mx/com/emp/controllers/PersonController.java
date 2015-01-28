package mx.com.emp.controllers;

import mx.com.emp.dto.PersonDTO;
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
		person.setPersonDocumentNumber("MNI874FU8");
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
		person.setPersonDocumentNumber("MNI874FU8");
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

}
