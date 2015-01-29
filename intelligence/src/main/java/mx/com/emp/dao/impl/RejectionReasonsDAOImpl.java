package mx.com.emp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.RejectionReasonsDAO;
import mx.com.emp.dao.jdbc.support.IntelligenceJdbcDaoSupport;
import mx.com.emp.dto.RejectionReasonsDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("rejectionReasonsDAO")
@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class RejectionReasonsDAOImpl extends IntelligenceJdbcDaoSupport
		implements RejectionReasonsDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421716344854137772L;

	private Logger LOGGER = Logger.getLogger(RejectionReasonsDAOImpl.class);

	@Value("${database.generic}")
	private String genericError;

	@Override
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public RejectionReasonsDTO saveRejectionReasons(
			final RejectionReasonsDTO rejectionReason) throws DaoException {

		try {

			final StringBuilder sQuery = new StringBuilder(
					"INSERT INTO REJECTION_REASONS (");
			sQuery.append(" reason_id,");
			sQuery.append(" rejection_reason_date,");
			sQuery.append(" rejection_reason_desc");
			sQuery.append(" ) VALUES (?,?,?)");

			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							sQuery.toString(),
							new String[] { "rejection_reason_id" });
					ps.setInt(1, rejectionReason.getReasonType()
							.getCatalogueId());
					ps.setDate(2, new java.sql.Date(rejectionReason
							.getRejectionDate().getTime()));
					ps.setString(3, rejectionReason.getRejectionDescription());
					return ps;
				}
			}, keyHolder);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" -- Rejection Reason Creada : "
						+ keyHolder.getKey().intValue() + " : "
						+ rejectionReason.getReasonType().getCatalogueId()
						+ " : " + rejectionReason.getRejectionDate() + " : "
						+ rejectionReason.getRejectionDescription());
			}

			rejectionReason.setRejectionReasonId(keyHolder.getKey().intValue());

			return rejectionReason;

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}

	}

	@Override
	public List<RejectionReasonsDTO> getAllRejectionReasonByPersonId(
			Integer personId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public void saveRejectionReasonPerson(Integer rejectionReasonId,
			Integer personId) throws DaoException {

		try {

			StringBuilder sQuery = new StringBuilder(
					"INSERT INTO REJECTION_REASONS_PERSON (");
			sQuery.append(" person_id,");
			sQuery.append(" rejection_reason_id");
			sQuery.append(" ) VALUES (?,?)");

			Object[] values = { personId, rejectionReasonId };

			int[] types = { Types.INTEGER, Types.INTEGER };

			jdbcTemplate.update(sQuery.toString(), values, types);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" -- Relacion Persona - Rejection Reason Creada : ");
			}

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}

	}

}
