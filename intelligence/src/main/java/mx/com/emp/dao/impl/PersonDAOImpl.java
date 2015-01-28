package mx.com.emp.dao.impl;

import java.sql.Types;
import java.util.List;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.PersonDAO;
import mx.com.emp.dao.jdbc.support.IntelligenceJdbcDaoSupport;
import mx.com.emp.dto.PersonDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("personDAO")
@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class PersonDAOImpl extends IntelligenceJdbcDaoSupport implements
		PersonDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6119669411572101704L;

	private Logger LOGGER = Logger.getLogger(PersonDAOImpl.class);

	@Value("${database.generic}")
	private String genericError;

	@Override
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public PersonDTO savePerson(PersonDTO person) throws DaoException {

		try {

			StringBuilder sQuery = new StringBuilder("INSERT INTO PERSON (");
			sQuery.append(" person_id,");
			sQuery.append(" status_id,");
			sQuery.append(" person_relative_id,");
			sQuery.append(" person_type_id,");
			sQuery.append(" person_name,");
			sQuery.append(" person_surname");
			sQuery.append(" person_address,");
			sQuery.append(" person_phone,");
			sQuery.append(" person_mobile,");
			sQuery.append(" person_document,");
			sQuery.append(" person_document_number,");
			sQuery.append(" person_additional_data");
			sQuery.append(" person_company,");
			sQuery.append(" person_degree,");
			sQuery.append(" person_working_section");
			sQuery.append(" ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			Object[] values = { person.getPersonId(), person.getStatusId(),
					person.getPersonRelativeId(), person.getPersonTypeId(),
					person.getPersonName(), person.getPersonSurname(),
					person.getPersonAddress(), person.getPersonPhone(),
					person.getPersonMobile(), person.getPersonDocument(),
					person.getPersonDocumentNumber(),
					person.getPersonAdditionalData(),
					person.getPersonCompany(), person.getPersonDegree(),
					person.getPersonWorkingSection() };

			int[] types = { Types.INTEGER, Types.INTEGER, Types.INTEGER,
					Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

			jdbcTemplate.update(sQuery.toString(), values, types);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" -- Persona Creada : "
						+ person.getPersonAdditionalData() + " : "
						+ person.getPersonAddress() + " : "
						+ person.getPersonCompany() + " : "
						+ person.getPersonDegree() + " : "
						+ person.getPersonDocument() + " : "
						+ person.getPersonDocumentNumber() + " : "
						+ person.getPersonName() + " : "
						+ person.getPersonSurname() + " : "
						+ person.getPersonWorkingSection() + " : "
						+ person.getPersonId() + " : "
						+ person.getPersonMobile() + " : "
						+ person.getPersonPhone() + " : "
						+ person.getPersonRelativeId());
			}

			return person;

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO getById(Integer person) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deletePerson(Integer person) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PersonDTO> getAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
