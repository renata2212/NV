package mx.com.emp.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.emp.dto.PersonDTO;

public interface PersonDAO extends Serializable {

	PersonDTO savePerson(PersonDTO person) throws DaoException;

	PersonDTO updatePerson(PersonDTO person) throws DaoException;

	PersonDTO getById(Integer person) throws DaoException;

	int deletePerson(Integer person) throws DaoException;

	List<PersonDTO> getAll() throws DaoException;
}
