package mx.com.emp.service.impl;

import javax.annotation.Resource;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.PersonDAO;
import mx.com.emp.dto.PersonDTO;
import mx.com.emp.service.PersonService;
import mx.com.emp.service.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
@Transactional(value = "txManager", rollbackFor = ServiceException.class, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class PersonServiceImpl implements PersonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger LOGGER = Logger.getLogger(PersonServiceImpl.class);

	@Resource
	private PersonDAO personDAO;

	@Override
	@Transactional(value = "txManager", rollbackFor = { ServiceException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public PersonDTO createOrUpdatePerson(PersonDTO person)
			throws ServiceException {

		try {

			if (person.getPersonId() != null) {
				return personDAO.updatePerson(person);
			} else {
				return personDAO.savePerson(person);
			}

		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

}
