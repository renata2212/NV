package mx.com.emp.service;

import java.io.Serializable;
import java.util.List;

import mx.com.emp.dto.PersonDTO;
import mx.com.emp.dto.RejectionReasonsDTO;

public interface PersonService extends Serializable {

	PersonDTO createOrUpdatePerson(PersonDTO person) throws ServiceException;

	PersonDTO getById(Integer personId) throws ServiceException;

	List<PersonDTO> getAll() throws ServiceException;

	int deletePerson(Integer personId, RejectionReasonsDTO rejectionReason)
			throws ServiceException;

	int relateVehiclePerson(Integer vehicleId, Integer personId)
			throws ServiceException;

}
