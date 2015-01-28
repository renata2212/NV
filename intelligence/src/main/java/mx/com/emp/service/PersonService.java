package mx.com.emp.service;

import java.io.Serializable;

import mx.com.emp.dto.PersonDTO;

public interface PersonService extends Serializable {

	PersonDTO createOrUpdatePerson(PersonDTO vehicle) throws ServiceException;
}
