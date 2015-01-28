package mx.com.emp.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.emp.dto.PersonDTO;

public interface PersonDAO extends Serializable {

	PersonDTO savePerson(PersonDTO person) throws DaoException;

	PersonDTO updatePerson(PersonDTO person) throws DaoException;

	PersonDTO getById(Integer personId) throws DaoException;

	int deletePerson(Integer personId) throws DaoException;

	List<PersonDTO> getAll() throws DaoException;
}
