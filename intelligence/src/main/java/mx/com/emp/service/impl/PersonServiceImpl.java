package mx.com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.PersonDAO;
import mx.com.emp.dao.RejectionReasonsDAO;
import mx.com.emp.dto.PersonDTO;
import mx.com.emp.dto.RejectionReasonsDTO;
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

	@Resource
	private RejectionReasonsDAO rejectionReasonsDAO;

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

	@Override
	public PersonDTO getById(Integer personId) throws ServiceException {

		try {
			return personDAO.getById(personId);
		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<PersonDTO> getAll() throws ServiceException {

		try {
			return personDAO.getAll();
		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	@Transactional(value = "txManager", rollbackFor = { ServiceException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public int deletePerson(Integer personId,
			RejectionReasonsDTO rejectionReason) throws ServiceException {

		int numDelete = 0;
		try {
			numDelete = personDAO.deletePerson(2, personId); // Validar estatus

			if (numDelete == 1) {
				RejectionReasonsDTO reason = rejectionReasonsDAO
						.saveRejectionReasons(rejectionReason);

				rejectionReasonsDAO.saveRejectionReasonPerson(
						reason.getRejectionReasonId(), personId);
			}
		} catch (DaoException e) {
			LOGGER.error("Mensaje de error: " + e);
			throw new ServiceException(e.getMessage(), e);
		}

		return numDelete;
	}

	@Override
	@Transactional(value = "txManager", rollbackFor = { ServiceException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public int relateVehiclePerson(Integer vehicleId, Integer personId)
			throws ServiceException {

		int numUpdate = 0;

		try {
			numUpdate = personDAO.saveVehiclePerson(vehicleId, personId);

		} catch (DaoException e) {
			LOGGER.error("Mensaje de error: " + e);
			throw new ServiceException(e.getMessage(), e);
		}

		return numUpdate;
	}

}
