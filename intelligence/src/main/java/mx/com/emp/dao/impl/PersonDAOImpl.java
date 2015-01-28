package mx.com.emp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.PersonDAO;
import mx.com.emp.dao.jdbc.support.IntelligenceJdbcDaoSupport;
import mx.com.emp.dto.PersonDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
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
			sQuery.append(" person_surname,");
			sQuery.append(" person_address,");
			sQuery.append(" person_phone,");
			sQuery.append(" person_mobile,");
			sQuery.append(" person_document,");
			sQuery.append(" person_document_number,");
			sQuery.append(" person_additional_data,");
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
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public PersonDTO updatePerson(PersonDTO person) throws DaoException {

		try {
			StringBuilder sQuery = new StringBuilder("UPDATE PERSON SET ");
			sQuery.append(" person_relative_id = ?,");
			sQuery.append(" person_type_id = ?,");
			sQuery.append(" person_name = ?,");
			sQuery.append(" person_surname = ?,");
			sQuery.append(" person_address = ?,");
			sQuery.append(" person_phone = ?,");
			sQuery.append(" person_mobile = ?,");
			sQuery.append(" person_document = ?,");
			sQuery.append(" person_document_number = ?,");
			sQuery.append(" person_additional_data = ?,");
			sQuery.append(" person_company = ?,");
			sQuery.append(" person_degree = ?,");
			sQuery.append(" person_working_section = ?");
			sQuery.append(" WHERE person_id = ?");

			Object[] values = { person.getPersonRelativeId(),
					person.getPersonTypeId(), person.getPersonName(),
					person.getPersonSurname(), person.getPersonAddress(),
					person.getPersonPhone(), person.getPersonMobile(),
					person.getPersonDocument(),
					person.getPersonDocumentNumber(),
					person.getPersonAdditionalData(),
					person.getPersonCompany(), person.getPersonDegree(),
					person.getPersonWorkingSection(), person.getPersonId() };

			int[] types = { Types.INTEGER, Types.INTEGER, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER,
					Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

			jdbcTemplate.update(sQuery.toString(), values, types);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" -- Persona Actualizada : "
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
						+ person.getPersonId() + " : "
						+ person.getPersonRelativeId());
			}

			return person;

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}
	}

	@Override
	public PersonDTO getById(Integer personId) throws DaoException {

		try {
			StringBuilder sQuery = new StringBuilder(
					"SELECT person_id, status_id, person_relative_id, person_type_id, person_name, person_surname,"
							+ " person_address, person_phone, person_mobile, person_document, person_document_number, person_additional_data,"
							+ " person_company, person_degree, person_working_section FROM PERSON WHERE person_id = ?");

			return jdbcTemplate.queryForObject(sQuery.toString(),
					new Object[] { personId }, new int[] { Types.INTEGER },
					new PersonMapper());

		} catch (Exception ex) {
			LOGGER.error("Mensaje de error" + ex);
			throw new DaoException(genericError, ex);
		}
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

	/**
	 * Clase para mapeo de personas
	 *
	 **/
	private static final class PersonMapper implements RowMapper<PersonDTO> {

		private Map<String, PersonDTO> personMap;

		public PersonMapper() {
			personMap = new HashMap<String, PersonDTO>();
		}

		@Override
		public PersonDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

			PersonDTO person = null;

			if (personMap.containsKey(rs.getString("person_id")))
				person = personMap.get(rs.getString("person_id"));
			else
				person = new PersonDTO();

			person.setPersonId(new Integer(rs.getString("person_id")));
			person.setStatusId(new Integer(rs.getString("status_id")));
			person.setPersonRelativeId(new Integer(rs
					.getString("person_relative_id")));
			person.setPersonTypeId(new Integer(rs.getString("person_type_id")));
			person.setPersonName(rs.getString("person_name"));
			person.setPersonSurname(rs.getString("person_surname"));
			person.setPersonAddress(rs.getString("person_address"));
			person.setPersonPhone(new Integer(rs.getString("person_phone")));
			person.setPersonMobile(new Integer(rs.getString("person_mobile")));
			person.setPersonDocument(rs.getString("person_document"));
			person.setPersonDocumentNumber(rs
					.getString("person_document_number"));
			person.setPersonAdditionalData(rs
					.getString("person_additional_data"));
			person.setPersonCompany(rs.getString("person_company"));
			person.setPersonDegree(rs.getString("person_degree"));
			person.setPersonWorkingSection(rs
					.getString("person_working_section"));

			personMap.put(person.getPersonId().toString(), person);

			return person;
		}
	}

}
